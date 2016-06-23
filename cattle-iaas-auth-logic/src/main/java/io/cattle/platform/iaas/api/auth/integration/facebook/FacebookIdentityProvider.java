package io.cattle.platform.iaas.api.auth.integration.facebook;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;

import io.cattle.platform.api.auth.Identity;
import io.cattle.platform.core.constants.IdentityConstants;
import io.cattle.platform.core.constants.ProjectConstants;
import io.cattle.platform.core.model.Account;
import io.cattle.platform.core.model.AuthToken;
import io.cattle.platform.iaas.api.auth.SecurityConstants;
import io.cattle.platform.iaas.api.auth.dao.AuthTokenDao;
import io.cattle.platform.iaas.api.auth.integration.facebook.resource.FacebookAccountInfo;
import io.cattle.platform.iaas.api.auth.integration.facebook.resource.FacebookClient;
import io.cattle.platform.iaas.api.auth.integration.facebook.resource.FacebookClientEndpoints;
import io.cattle.platform.iaas.api.auth.integration.interfaces.IdentityProvider;
import io.cattle.platform.json.JsonMapper;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.util.type.CollectionUtils;
import io.github.ibuildthecloud.gdapi.context.ApiContext;
import io.github.ibuildthecloud.gdapi.exception.ClientVisibleException;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;
import io.github.ibuildthecloud.gdapi.util.ResponseCodes;

public class FacebookIdentityProvider extends FacebookConfigurable implements IdentityProvider {

    @Inject
    FacebookClient facebookClient;

    @Inject
    private JsonMapper jsonMapper;
    @Inject
    private FacebookTokenUtil facebookTokenUtils;
    @Inject
    private AuthTokenDao authTokenDao;
    @Inject
    FacebookTokenCreator facebookTokenCreator;

    private static final Log logger = LogFactory.getLog(FacebookIdentityProvider.class);


    @Override
    public List<Identity> searchIdentities(String name, boolean exactMatch) {
        if (!isConfigured()){
            notConfigured();
        }
        List<Identity> identities = new ArrayList<>();
        for (String scope : scopes()) {
            identities.addAll(searchIdentities(name, scope, exactMatch));
        }
        return identities;
    }

    @Override
    public List<Identity> searchIdentities(String name, String scope, boolean exactMatch) {
        //TODO:Implement exact match.
        if (!isConfigured()){
            notConfigured();
        }
        switch (scope){
            case FacebookConstants.USER_SCOPE:
                return searchUsers(name, exactMatch);
            case FacebookConstants.ORG_SCOPE:
                return searchGroups(name, exactMatch);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public Set<Identity> getIdentities(Account account) {
        if (!isConfigured() || !FacebookConstants.CONFIG.equalsIgnoreCase(SecurityConstants.AUTH_PROVIDER.get()) ||
                !FacebookConstants.USER_SCOPE.equalsIgnoreCase(account.getExternalIdType())) {
            return new HashSet<>();
        }
        String accessToken = (String) DataAccessor.fields(account).withKey(FacebookConstants.FACEBOOK_ACCESS_TOKEN).get();
        if (facebookTokenUtils.findAndSetJWT()){
            ApiRequest request = ApiContext.getContext().getApiRequest();
            request.setAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN, accessToken);
            return facebookTokenUtils.getIdentities();
        }
        String jwt = null;
        if (!StringUtils.isBlank(accessToken) && SecurityConstants.SECURITY.get()) {
            AuthToken authToken = authTokenDao.getTokenByAccountId(account.getId());
            if (authToken == null) {
                try {
                    jwt = ProjectConstants.AUTH_TYPE + facebookTokenCreator.getFacebookToken(accessToken).getJwt();
                    authToken = authTokenDao.createToken(jwt, FacebookConstants.CONFIG, account.getId());
                    jwt = authToken.getKey();
                } catch (ClientVisibleException e) {
                    if (e.getCode().equalsIgnoreCase(FacebookConstants.FACEBOOK_ERROR) &&
                            !e.getDetail().contains("401")) {
                        throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR,
                                FacebookConstants.JWT_CREATION_FAILED, "", null);
                    }
                }
            } else {
                jwt = authToken.getKey();
            }

        }
        if (StringUtils.isBlank(jwt)){
            return Collections.emptySet();
        }
        ApiRequest request = ApiContext.getContext().getApiRequest();
        request.setAttribute(FacebookConstants.FACEBOOK_JWT, jwt);
        request.setAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN, accessToken);
        return facebookTokenUtils.getIdentities();
    }

    private List<Identity> searchGroups(String groupName, boolean exactMatch) {
        List<Identity> identities = new ArrayList<>();
        if (exactMatch) {
            FacebookAccountInfo group;
            try {
                group =  facebookClient.getFacebookOrgByName(groupName);
                if (group == null){
                    return identities;
                }
            } catch (ClientVisibleException e) {
                return identities;
            }
            Identity identity = group.toIdentity(FacebookConstants.ORG_SCOPE);
            identities.add(identity);
            return identities;
        }
        String url;
        try {
            url = facebookClient.getURL(FacebookClientEndpoints.USER_SEARCH) + URLEncoder.encode(groupName, "UTF-8") +
                        "+type:org";
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List<Map<String, Object>> results = facebookClient.searchFacebook(url);
        for (Map<String, Object> user: results){
            identities.add(facebookClient.jsonToFacebookAccountInfo(user).toIdentity(FacebookConstants.ORG_SCOPE));
        }
        return identities;
    }

    private List<Identity> searchUsers(String userName, boolean exactMatch) {
        List<Identity> identities = new ArrayList<>();
        if (exactMatch) {
            FacebookAccountInfo user;
            try {
                user =  facebookClient.getFacebookUserByName(userName);
            } catch (ClientVisibleException e) {
                return identities;
            }
            if (user == null) {
                return identities;
            }
            Identity identity = user.toIdentity(FacebookConstants.USER_SCOPE);
            identities.add(identity);
            return identities;
        }
        String url = null;
        try {
            url = facebookClient.getURL(FacebookClientEndpoints.USER_SEARCH) + URLEncoder.encode(userName, "UTF-8") +
                        "+type:user";
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        List<Map<String, Object>> results = facebookClient.searchFacebook(url);
        for (Map<String, Object> user: results){
            identities.add(facebookClient.jsonToFacebookAccountInfo(user).toIdentity(FacebookConstants.USER_SCOPE));
        }
        return identities;

    }

    @Override
    public Identity getIdentity(String id, String scope) {
        if (!isConfigured()){
            notConfigured();
        }
        switch (scope) {
            case FacebookConstants.USER_SCOPE:
                FacebookAccountInfo user = facebookClient.getUserOrgById(id);
                return user.toIdentity(FacebookConstants.USER_SCOPE);
            case FacebookConstants.ORG_SCOPE:
                FacebookAccountInfo org = facebookClient.getUserOrgById(id);
                return org.toIdentity(FacebookConstants.ORG_SCOPE);
            case FacebookConstants.TEAM_SCOPE:
                return getTeamById(id);
            default:
                throw new ClientVisibleException(ResponseCodes.BAD_REQUEST,
                        IdentityConstants.INVALID_TYPE, "Invalid scope for FacebookSearchProvider", null);
        }
    }

    private void notConfigured() {
        throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE,
                "NotConfigured", "Facebook is not configured", null);
    }

    private Identity getTeamById(String id) {
        if (!isConfigured()) {
            notConfigured();
        }
        String facebookAccessToken = (String) ApiContext.getContext().getApiRequest().getAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN);
        try {
            if (StringUtils.isEmpty(id)) {
                return null;
            }
            HttpResponse response = facebookClient.getFromFacebook(facebookAccessToken, facebookClient.getURL(FacebookClientEndpoints.TEAM) + id);

            Map<String, Object> teamData = CollectionUtils.toMap(jsonMapper.readValue(response.getEntity().getContent
                    (), Map.class));

            return facebookClient.getTeam(teamData);
        } catch (IOException e) {
            throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, "FacebookUnavailable", "Could not retrieve orgId from Facebook", null);
        }
    }

    @Override
    public Set<String> scopes() {
        return FacebookConstants.SCOPES;
    }

    @Override
    public String getName() {
        return FacebookConstants.SEARCH_PROVIDER;
    }

    public List<Identity> savedIdentities() {
        List<String> ids = facebookTokenUtils.fromCommaSeparatedString(FacebookConstants.FACEBOOK_ALLOWED_IDENTITIES.get());
        List<Identity> identities = new ArrayList<>();
        if (ids.isEmpty() || !isConfigured()) {
            return identities;
        }
        for(String id: ids){
            String[] split = id.split(":", 2);
            identities.add(getIdentity(split[1], split[0]));
        }
        return identities;
    }

    @Override
    public Identity transform(Identity identity) {
        if (scopes().contains(identity.getExternalIdType())) {
            return getIdentity(identity.getExternalId(), identity.getExternalIdType());
        }
        throw new ClientVisibleException(ResponseCodes.BAD_REQUEST, IdentityConstants.INVALID_TYPE,
            "Facebook does not provide: " + identity.getExternalIdType(), null );
    }

    @Override
    public Identity untransform(Identity identity) {
        if (scopes().contains(identity.getExternalIdType())) {
            return identity;
        }
        throw new ClientVisibleException(ResponseCodes.BAD_REQUEST, IdentityConstants.INVALID_TYPE,
            "Facebook does not provide: " + identity.getExternalIdType(), null );
    }
}
