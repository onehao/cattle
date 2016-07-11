package io.cattle.platform.iaas.api.auth.integration.facebook.resource;

import java.util.List;

import io.cattle.platform.api.auth.Identity;
import io.cattle.platform.iaas.api.auth.integration.facebook.FacebookConstants;
import io.github.ibuildthecloud.gdapi.annotation.Field;
import io.github.ibuildthecloud.gdapi.annotation.Type;

@Type(name = FacebookConstants.CONFIG)
public class FacebookConfig {

    private String hostname;
    private String scheme;
    private Boolean enabled;
    private String accessMode;
    private String clientId;
    private String clientSecret;
    private List<Identity> allowedIdentities;

    public FacebookConfig(Boolean enabled, String accessMode, String clientId, String hostName,
                        String scheme, List<Identity> allowedIdentities) {
        this.enabled = enabled;
        this.accessMode = accessMode;
        this.clientId = clientId;
        this.hostname = hostName;
        this.scheme = scheme;
        this.allowedIdentities = allowedIdentities;
    }

    @Field(nullable = true)
    public Boolean getEnabled() {
        return enabled;
    }

    @Field(nullable = true)
    public String getClientId() {
        return clientId;
    }

    @Field(nullable = true)
    public String getClientSecret() {
        return clientSecret;
    }

    @Field(nullable = false)
    public String getAccessMode() {
        return accessMode;
    }

    @Field(nullable = true)
    public String getHostname() {
        return hostname;
    }

    @Field(nullable = true)
    public String getScheme() {
        return scheme;
    }

    public String getName() {
        return FacebookConstants.CONFIG;
    }

    @Field(nullable = true)
    public List<Identity> getAllowedIdentities() {
        return allowedIdentities;
    }
}
