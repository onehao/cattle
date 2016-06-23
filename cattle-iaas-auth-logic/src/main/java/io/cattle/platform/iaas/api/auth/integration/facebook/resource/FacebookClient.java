package io.cattle.platform.iaas.api.auth.integration.facebook.resource;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import io.cattle.platform.api.auth.Identity;
import io.cattle.platform.iaas.api.auth.integration.facebook.FacebookConfigurable;
import io.cattle.platform.iaas.api.auth.integration.facebook.FacebookConstants;
import io.cattle.platform.iaas.api.auth.integration.facebook.FacebookTokenUtil;
import io.cattle.platform.json.JsonMapper;
import io.cattle.platform.util.type.CollectionUtils;
import io.github.ibuildthecloud.gdapi.context.ApiContext;
import io.github.ibuildthecloud.gdapi.exception.ClientVisibleException;
import io.github.ibuildthecloud.gdapi.util.ResponseCodes;

public class FacebookClient extends FacebookConfigurable{

    private static final String LINK = "link";
    private static final String RELATION = "rel";
    private static final String NEXT = "next";

    @Inject
    private FacebookTokenUtil facebookTokenUtils;

    @Inject
    private JsonMapper jsonMapper;

    private static final Log logger = LogFactory.getLog(FacebookClient.class);

    private Identity getUserIdentity(String facebookAccessToken) {
        if (StringUtils.isEmpty(facebookAccessToken)) {
            noAccessToken();
        }
        try {
            HttpResponse response = getFromFacebook(facebookAccessToken, getURL(FacebookClientEndpoints.USER_INFO));

            Map<String, Object> jsonData = jsonMapper.readValue(response.getEntity().getContent());
            return jsonToFacebookAccountInfo(jsonData).toIdentity(FacebookConstants.USER_SCOPE);
        } catch (IOException e) {
            logger.error("Failed to get Facebook user account info.", e);
            throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR, FacebookConstants.FACEBOOK_CLIENT,
                    "Failed to get Facebook user account info.", null);
        }
    }

    public String getAccessToken(String code) {
        List<BasicNameValuePair> requestData = new ArrayList<>();

        if (!isConfigured()) {
            throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR, FacebookConstants.CONFIG, "No Facebook Client id and secret found.", null);
        }

        requestData.add(new BasicNameValuePair(FacebookConstants.CLIENT_ID, FacebookConstants.FACEBOOK_CLIENT_ID.get()));
        requestData.add(new BasicNameValuePair(FacebookConstants.CLIENT_SECRET, FacebookConstants.FACEBOOK_CLIENT_SECRET.get()));
        requestData.add(new BasicNameValuePair(FacebookConstants.FACEBOOK_REQUEST_CODE, code));

        Map<String, Object> jsonData;

        HttpResponse response;
        try {
            response = Request.Post(getURL(FacebookClientEndpoints.TOKEN))
                    .addHeader(FacebookConstants.ACCEPT, FacebookConstants.APPLICATION_JSON).bodyForm(requestData)
                    .execute().returnResponse();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                noFacebook(statusCode);
            }
            jsonData = jsonMapper.readValue(response.getEntity().getContent());

            if (jsonData.get("error") != null) {
                throw new ClientVisibleException(ResponseCodes.BAD_REQUEST, (String) jsonData.get("error_description"));
            }

            return (String) jsonData.get(FacebookConstants.ACCESS_TOKEN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FacebookAccountInfo jsonToFacebookAccountInfo(Map<String, Object> jsonData) {
        String accountId = ObjectUtils.toString(jsonData.get("id"));
        String accountName = ObjectUtils.toString(jsonData.get(FacebookConstants.LOGIN));
        String name = ObjectUtils.toString(jsonData.get(FacebookConstants.NAME_FIELD));
        if (StringUtils.isBlank(name)) {
            name = accountName;
        }
        String profilePicture = ObjectUtils.toString(jsonData.get(FacebookConstants.PROFILE_PICTURE));
        String profileUrl = ObjectUtils.toString(jsonData.get(FacebookConstants.PROFILE_URL));
        return new FacebookAccountInfo(accountId, accountName, profilePicture, profileUrl, name);
    }

    public List<Identity> getOrgAccountInfo(String facebookAccessToken) {
        try {
            if (StringUtils.isEmpty(facebookAccessToken)) {
                noAccessToken();
            }
            List<Identity> orgs = new ArrayList<>();
            List<Map<String, Object>> jsonData;

            List<HttpResponse> responses = paginateFacebook(facebookAccessToken, getURL(FacebookClientEndpoints.ORG_INFO));
            for (HttpResponse response :
                    responses) {
                jsonData = jsonMapper.readCollectionValue(response.getEntity().getContent(), List.class, Map.class);

                for (Map<String, Object> orgObject : jsonData) {
                    orgs.add(jsonToFacebookAccountInfo(orgObject).toIdentity(FacebookConstants.ORG_SCOPE));
                }
            }
            return orgs;
        }
        catch (IOException e){
            logger.error("Failed to get org account info.", e);
            throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR, FacebookConstants.FACEBOOK_CLIENT,
                    "Failed to get org account info.", null);
        }
    }

    private List<HttpResponse> paginateFacebook(String facebookAccessToken, String url) throws IOException {
        List<HttpResponse> responses = new ArrayList<>();
        HttpResponse response = getFromFacebook(facebookAccessToken, url);
        responses.add(response);
        String nextUrl = nextFacebookPage(response);
        while (StringUtils.isNotBlank(nextUrl)) {
            response = getFromFacebook(facebookAccessToken, nextUrl);
            responses.add(response);
            nextUrl = nextFacebookPage(response);
        }
        return responses;
    }

    private void noAccessToken() {
        throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR,
                "FacebookAccessToken", "No facebook Access token", null);
    }

    public List<Identity> getTeamsInfo(String facebookAccessToken) {
        try {
            if (StringUtils.isEmpty(facebookAccessToken)) {
                noAccessToken();
            }

            List<HttpResponse> responses = paginateFacebook(facebookAccessToken, getURL(FacebookClientEndpoints.TEAMS));
            ArrayList<Identity> teams = new ArrayList<>();
            for (HttpResponse response :
                    responses) {
                teams.addAll(getTeamInfo(response));
            }
            return teams;
        }
        catch (IOException e) {
            logger.error("Failed to get team account info.", e);
            throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR, FacebookConstants.FACEBOOK_CLIENT,
                    "Failed to get team account info.", null);
        }
    }

    private List<Identity> getTeamInfo(HttpResponse response) throws IOException {
        List<Identity> teams = new ArrayList<>();
        List<Map<String, Object>> jsonData;
        jsonData = jsonMapper.readCollectionValue(response.getEntity().getContent(), List.class, Map.class);
        for (Map<String, Object> teamObject : jsonData) {
            teams.add(getTeam(teamObject));
        }
        return teams;

    }

    public Identity getTeam(Map<String, Object> teamObject) {
        String accountId = ObjectUtils.toString(teamObject.get(FacebookConstants.TEAM_ID));
        String teamName = ObjectUtils.toString(teamObject.get(FacebookConstants.NAME_FIELD));
        String slug = ObjectUtils.toString(teamObject.get("slug"));
        Map<String, Object> org = CollectionUtils.toMap(teamObject.get("organization"));
        String orgLogin = ObjectUtils.toString(org.get(FacebookConstants.LOGIN));
        String orgName = ObjectUtils.toString(org.get(FacebookConstants.LOGIN));
        String profilePicture = ObjectUtils.toString(org.get(FacebookConstants.PROFILE_PICTURE));
        String profileUrl = String.format(getURL(FacebookClientEndpoints.TEAM_PROFILE), orgLogin, slug);
        return new Identity(FacebookConstants.TEAM_SCOPE, accountId,
                StringUtils.isBlank(orgName) ? orgLogin : orgName + " : " + teamName,
                profileUrl, profilePicture, slug);
    }

    private String nextFacebookPage(HttpResponse response) {
        if (response.getFirstHeader(LINK) != null) {
            for(HeaderElement element: response.getFirstHeader(LINK).getElements()) {
                if (element.getParameterByName(RELATION) != null &&
                        NEXT.equalsIgnoreCase(element.getParameterByName(RELATION).getValue())){
                    String next = String.valueOf(element).split(";")[0];
                    return next.substring(1, next.length() - 1);
                }
            }
        }
        return null;
    }

    public HttpResponse getFromFacebook(String facebookAccessToken, String url) throws IOException {

        HttpResponse response = Request.Get(url).addHeader(FacebookConstants.AUTHORIZATION, "token " +
                "" + facebookAccessToken).addHeader(FacebookConstants.ACCEPT, FacebookConstants.APPLICATION_JSON).execute().returnResponse();
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            noFacebook(statusCode);
        }
        return response;
    }

    public FacebookAccountInfo getFacebookUserByName(String username) {
        String facebookAccessToken = (String) ApiContext.getContext().getApiRequest().getAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN);
        if (StringUtils.isEmpty(facebookAccessToken)) {
            noAccessToken();
        }
        try {
            if (StringUtils.isEmpty(username)) {
                throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR,
                        "getFacebookUser", "No facebook username specified.", null);
            }
            username = URLEncoder.encode(username, "UTF-8");
            try {
                if (getFacebookOrgByName(username) != null) {
                    return null;
                }
            } catch (ClientVisibleException ignored) {}
            HttpResponse response = getFromFacebook(facebookAccessToken, getURL(FacebookClientEndpoints.USERS) + username);
            Map<String, Object> jsonData = CollectionUtils.toMap(jsonMapper.readValue(response.getEntity().getContent(), Map.class));
            return jsonToFacebookAccountInfo(jsonData);
        } catch (IOException e) {
            logger.error(e);
            throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, "FacebookUnavailable", "Could not retrieve UserId from Facebook", null);
        }

    }

    public FacebookAccountInfo getFacebookOrgByName(String org) {
        String facebookAccessToken = (String) ApiContext.getContext().getApiRequest().getAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN);
        if (StringUtils.isEmpty(facebookAccessToken)) {
            noAccessToken();
        }
        try {
            if (StringUtils.isEmpty(org)) {
                throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR,
                        "noFacebookOrgName", "No org name specified when retrieving from Facebook.", null);
            }
            org = URLEncoder.encode(org, "UTF-8");
            HttpResponse response = getFromFacebook(facebookAccessToken, getURL(FacebookClientEndpoints.ORGS) + org);

            Map<String, Object> jsonData = CollectionUtils.toMap(jsonMapper.readValue(response.getEntity().getContent
                    (), Map.class));
            return jsonToFacebookAccountInfo(jsonData);
        } catch (IOException e) {
            logger.error(e);
            throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, "FacebookUnavailable", "Could not retrieve orgId from Facebook", null);
        }
    }

    public void noFacebook(Integer statusCode) {
        throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, FacebookConstants.FACEBOOK_ERROR,
                "Non-200 Response from Facebook", "Status code from Facebook: " + Integer.toString(statusCode));
    }

    public String getURL(FacebookClientEndpoints val) {
        String hostName;
        String apiEndpoint;
        if (StringUtils.isBlank(FacebookConstants.FACEBOOK_HOSTNAME.get())) {
            hostName = FacebookConstants.FACEBOOK_DEFAULT_HOSTNAME;
            apiEndpoint = FacebookConstants.FACEBOOK_API;
        } else {
            hostName = FacebookConstants.SCHEME.get() + FacebookConstants.FACEBOOK_HOSTNAME.get();
            apiEndpoint = FacebookConstants.SCHEME.get() + FacebookConstants.FACEBOOK_HOSTNAME.get() + FacebookConstants.GHE_API;
        }
        String toReturn;
        switch (val) {
            case API:
                toReturn = apiEndpoint;
                break;
            case TOKEN:
                toReturn = hostName + "/login/oauth/access_token";
                break;
            case USERS:
                toReturn = apiEndpoint + "/users/";
                break;
            case ORGS:
                toReturn = apiEndpoint + "/orgs/";
                break;
            case USER_INFO:
                toReturn = apiEndpoint + "/user";
                break;
            case ORG_INFO:
                toReturn = apiEndpoint + "/user/orgs?per_page=100";
                break;
            case USER_PICTURE:
                toReturn = "https://avatars.facebookusercontent.com/u/" + val + "?v=3&s=72";
                break;
            case USER_SEARCH:
                toReturn = apiEndpoint + "/search/users?q=";
                break;
            case TEAM:
                toReturn = apiEndpoint + "/teams/";
                break;
            case TEAMS:
                toReturn = apiEndpoint + "/user/teams?per_page=100";
                break;
            case TEAM_PROFILE:
                toReturn = hostName + "/orgs/%s/teams/%s";
                break;
            default:
                throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR,
                        "FacebookClient", "Attempted to get invalid Api endpoint.", null);
        }
        return toReturn;
    }

    public FacebookAccountInfo getUserOrgById(String id) {
        String facebookAccessToken = (String) ApiContext.getContext().getApiRequest().getAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN);
        if (StringUtils.isEmpty(facebookAccessToken)) {
            noAccessToken();
        }
        try {
            HttpResponse response = getFromFacebook(facebookAccessToken, getURL(FacebookClientEndpoints.USER_INFO) + '/' + id);

            Map<String, Object> jsonData = CollectionUtils.toMap(jsonMapper.readValue(response.getEntity().getContent(), Map.class));
            return jsonToFacebookAccountInfo(jsonData);
        } catch (IOException e) {
            throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, "FacebookUnavailable", "Could not retrieve UserId from Facebook", null);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> searchFacebook(String url) {
        try {
            HttpResponse res = getFromFacebook(facebookTokenUtils.getAccessToken(), url);
            //TODO:Finish implementing search.
            Map<String, Object> jsonData = jsonMapper.readValue(res.getEntity().getContent());
            return (List<Map<String, Object>>) jsonData.get("items");
        } catch (IOException e) {
            //TODO: Proper Error Handling.
            return new ArrayList<>();
        }
    }

    @Override
    public String getName() {
        return FacebookConstants.FACEBOOK_CLIENT;
    }

    public Set<Identity> getIdentities(String accessToken) {
        Set<Identity> identities = new HashSet<>();
        identities.add(getUserIdentity(accessToken));
        identities.addAll(getOrgAccountInfo(accessToken));
        identities.addAll(getTeamsInfo(accessToken));
        return identities;
    }
}
