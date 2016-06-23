package io.cattle.platform.iaas.api.auth.integration.facebook;

import io.cattle.platform.iaas.api.auth.SecurityConstants;
import io.cattle.platform.iaas.api.auth.integration.interfaces.Configurable;
import io.cattle.platform.iaas.api.auth.integration.interfaces.Provider;


import org.apache.commons.lang3.StringUtils;

public abstract class FacebookConfigurable implements Configurable, Provider{

    @Override
    public boolean isConfigured() {
        return StringUtils.equalsIgnoreCase(SecurityConstants.AUTH_PROVIDER.get(), FacebookConstants.CONFIG) &&
                StringUtils.isNotBlank(FacebookConstants.FACEBOOK_CLIENT_ID.get()) &&
                StringUtils.isNotBlank(FacebookConstants.FACEBOOK_CLIENT_SECRET.get());
    }

    @Override
    public String providerType() {
        return FacebookConstants.CONFIG;
    }
}
