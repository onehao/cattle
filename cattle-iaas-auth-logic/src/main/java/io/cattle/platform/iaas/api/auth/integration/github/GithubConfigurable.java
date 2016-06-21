package io.cattle.platform.iaas.api.auth.integration.github;

import io.cattle.platform.iaas.api.auth.SecurityConstants;
import io.cattle.platform.iaas.api.auth.integration.interfaces.Configurable;
import io.cattle.platform.iaas.api.auth.integration.interfaces.Provider;


import org.apache.commons.lang3.StringUtils;

public abstract class GithubConfigurable implements Configurable, Provider{

    @Override
    public boolean isConfigured() {
        return StringUtils.equalsIgnoreCase(SecurityConstants.AUTH_PROVIDER.get(), GithubConstants.CONFIG) &&
                StringUtils.isNotBlank(GithubConstants.GITHUB_CLIENT_ID.get()) &&
                StringUtils.isNotBlank(GithubConstants.GITHUB_CLIENT_SECRET.get());
    }

    @Override
    public String providerType() {
        return GithubConstants.CONFIG;
    }
}
