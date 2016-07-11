package io.cattle.platform.iaas.api.auth.integration.facebook;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import io.cattle.platform.iaas.api.auth.SecurityConstants;
import io.cattle.platform.iaas.api.auth.identity.Token;
import io.cattle.platform.iaas.api.auth.integration.facebook.resource.FacebookClient;
import io.cattle.platform.iaas.api.auth.integration.interfaces.TokenCreator;
import io.cattle.platform.util.type.CollectionUtils;
import io.github.ibuildthecloud.gdapi.exception.ClientVisibleException;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;
import io.github.ibuildthecloud.gdapi.util.ResponseCodes;

public class FacebookTokenCreator extends FacebookConfigurable implements TokenCreator {

    @Inject
    FacebookTokenUtil facebookTokenUtils;
    @Inject
    FacebookClient facebookClient;

    public Token getFacebookToken(String accessToken) {
        if (!isConfigured()) {
            throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, FacebookConstants.CONFIG, "No Facebook Client id and secret found.", null);
        }
        return facebookTokenUtils.createToken(facebookClient.getIdentities(accessToken), null);
    }

    @Override
    public Token getToken(ApiRequest request) {
        Map<String, Object> requestBody = CollectionUtils.toMap(request.getRequestObject());
        String code = ObjectUtils.toString(requestBody.get(SecurityConstants.CODE));
        String accessToken = facebookClient.getAccessToken(code);
        if (StringUtils.isBlank(accessToken)){
            throw new ClientVisibleException(ResponseCodes.INTERNAL_SERVER_ERROR, getName(), "Failed to get accessToken.",
                    null);
        }
        request.setAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN, accessToken);
        return getFacebookToken(accessToken);
    }

    @Override
    public void reset() {

    }

    @Override
    public String getName() {
        return FacebookConstants.TOKEN_CREATOR;
    }
}
