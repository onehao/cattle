package io.cattle.platform.iaas.api.auth.integration.facebook;

import io.cattle.platform.core.model.Account;
import io.cattle.platform.iaas.api.auth.AbstractTokenUtil;
import io.cattle.platform.object.util.DataAccessor;
import io.github.ibuildthecloud.gdapi.context.ApiContext;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FacebookTokenUtil extends AbstractTokenUtil {

    @Override
    protected String accessMode() {
        return FacebookConstants.ACCESS_MODE.get();
    }

    @Override
    protected String accessToken() {
        return FacebookConstants.FACEBOOK_ACCESS_TOKEN;
    }

    @Override
    protected void postAuthModification(Account account) {
        ApiRequest request = ApiContext.getContext().getApiRequest();
        String accessToken = (String) request.getAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN);
        DataAccessor.fields(account).withKey(FacebookConstants.FACEBOOK_ACCESS_TOKEN)
                .set(accessToken);
        getObjectManager().persist(account);
    }

    @Override
    public String userType() {
        return FacebookConstants.USER_SCOPE;
    }

    @Override
    public boolean createAccount() {
        return true;
    }

    @Override
    protected boolean isWhitelisted(List<String> idList) {
        if (idList == null || idList.isEmpty()) {
            return false;
        }
        List<String> whitelistedValues = fromCommaSeparatedString(FacebookConstants.FACEBOOK_ALLOWED_IDENTITIES.get());

        for (String id : idList) {
            for (String whiteId: whitelistedValues){
                if (StringUtils.equals(id, whiteId)){
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> fromCommaSeparatedString(String string) {
        if (StringUtils.isEmpty(string)) {
            return new ArrayList<>();
        }
        List<String> strings = new ArrayList<>();
        String[] splitted = string.split(",");
        for (String aSplitted : splitted) {
            String element = aSplitted.trim();
            strings.add(element);
        }
        return strings;
    }

    @Override
    public String tokenType() {
        return FacebookConstants.FACEBOOK_JWT;
    }

    @Override
    public String getName() {
        return FacebookConstants.CONFIG;
    }
}
