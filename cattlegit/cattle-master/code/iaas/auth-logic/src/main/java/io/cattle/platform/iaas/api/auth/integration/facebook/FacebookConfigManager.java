package io.cattle.platform.iaas.api.auth.integration.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.netflix.config.DynamicStringProperty;

import io.cattle.platform.api.auth.Identity;
import io.cattle.platform.api.auth.Policy;
import io.cattle.platform.archaius.util.ArchaiusUtil;
import io.cattle.platform.core.constants.IdentityConstants;
import io.cattle.platform.core.model.Account;
import io.cattle.platform.core.util.SettingsUtils;
import io.cattle.platform.iaas.api.auth.AbstractTokenUtil;
import io.cattle.platform.iaas.api.auth.SecurityConstants;
import io.cattle.platform.iaas.api.auth.dao.AuthDao;
import io.cattle.platform.iaas.api.auth.integration.facebook.resource.FacebookClient;
import io.cattle.platform.iaas.api.auth.integration.facebook.resource.FacebookConfig;
import io.cattle.platform.json.JsonMapper;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.util.type.CollectionUtils;
import io.github.ibuildthecloud.gdapi.context.ApiContext;
import io.github.ibuildthecloud.gdapi.exception.ClientVisibleException;
import io.github.ibuildthecloud.gdapi.factory.SchemaFactory;
import io.github.ibuildthecloud.gdapi.model.ListOptions;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;
import io.github.ibuildthecloud.gdapi.request.resource.impl.AbstractNoOpResourceManager;
import io.github.ibuildthecloud.gdapi.util.ResponseCodes;

public class FacebookConfigManager extends AbstractNoOpResourceManager {

    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_SECRET = "clientSecret";
    private static final String ALLOWED_IDENTITIES = "allowedIdentities";
    private static final String SCHEME = "scheme";
    private static final DynamicStringProperty FACEBOOK_CLIENT_ID = ArchaiusUtil.getString(FacebookConstants.CLIENT_ID_SETTING);

    @Inject
    JsonMapper jsonMapper;

    @Inject
    ObjectManager objectManager;

    @Inject
    FacebookClient client;

    @Inject
    FacebookIdentityProvider facebookIdentitySearchProvider;

    @Inject
    SettingsUtils settingsUtils;

    @Inject
    AuthDao authDao;

    @Override
    public Class<?>[] getTypeClasses() {
        return new Class<?>[]{FacebookConfig.class};
    }

    @Override
    protected Object createInternal(String type, ApiRequest request) {
        if (!StringUtils.equalsIgnoreCase(FacebookConstants.CONFIG, request.getType())) {
            return null;
        }
        Map<String, Object> config = CollectionUtils.toMap(request.getRequestObject());

        return updateCurrentConfig(config);
    }

    @SuppressWarnings("unchecked")
    public FacebookConfig getCurrentConfig(Map<String, Object> config) {
        if (config == null){
            config = new HashMap<>();
        }
        boolean enabled = SecurityConstants.SECURITY.get();
        String clientId = FACEBOOK_CLIENT_ID.get();
        String accessMode = FacebookConstants.ACCESS_MODE.get();
        String hostname = FacebookConstants.FACEBOOK_HOSTNAME.get();
        String scheme = FacebookConstants.SCHEME.get();
        if (config.get(SecurityConstants.ENABLED) != null) {
            enabled = (Boolean) config.get(SecurityConstants.ENABLED);
        }
        if (config.get(AbstractTokenUtil.ACCESSMODE) != null) {
            accessMode = (String) config.get(AbstractTokenUtil.ACCESSMODE);
        }
        if (config.get(FacebookConstants.HOSTNAME) != null) {
            hostname = (String) config.get(FacebookConstants.HOSTNAME);
        }
        if (config.get(SCHEME) != null) {
            scheme = (String) config.get(SCHEME);
        }
        if (config.get(CLIENT_ID) != null) {
            clientId = (String) config.get(CLIENT_ID);
        }
        if (((Policy) ApiContext.getContext().getPolicy()).isOption(Policy.AUTHORIZED_FOR_ALL_ACCOUNTS)) {
            if (StringUtils.isBlank(
                    (String) ApiContext.getContext().getApiRequest().getAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN))) {
                Identity gotIdentity = Identity.fromId(ArchaiusUtil.getString(SecurityConstants.AUTH_ENABLER).get());
                if (gotIdentity != null) {
                    Account account = authDao.getAccountByExternalId(gotIdentity.getExternalId(), gotIdentity.getExternalIdType());
                    if (account != null) {
                        ApiContext.getContext().getApiRequest().setAttribute(FacebookConstants.FACEBOOK_ACCESS_TOKEN,
                                DataAccessor.fields(account).withKey(FacebookConstants.FACEBOOK_ACCESS_TOKEN).get());
                    }
                }
            }
        }
        List<Identity> identities = facebookIdentitySearchProvider.savedIdentities();
        if (config.get(ALLOWED_IDENTITIES) != null){
            identities = getIdentities((List<Map<String, String>>) config.get(ALLOWED_IDENTITIES));
        }
        return new FacebookConfig(enabled, accessMode, clientId, hostname, scheme, identities);
    }

    @Override
    protected Object listInternal(SchemaFactory schemaFactory, String type, Map<Object, Object> criteria, ListOptions options) {
        return getCurrentConfig(new HashMap<String, Object>());
    }

    @SuppressWarnings("unchecked")
    public FacebookConfig updateCurrentConfig(Map<String, Object> config) {
        settingsUtils.changeSetting(FacebookConstants.HOSTNAME_SETTING, config.get(FacebookConstants.HOSTNAME));
        settingsUtils.changeSetting(FacebookConstants.SCHEME_SETTING, config.get(SCHEME));
        settingsUtils.changeSetting(SecurityConstants.SECURITY_SETTING, config.get(SecurityConstants.ENABLED));
        settingsUtils.changeSetting(FacebookConstants.CLIENT_ID_SETTING, config.get(CLIENT_ID));
        settingsUtils.changeSetting(SecurityConstants.AUTH_PROVIDER.get(), config.get(FacebookConstants.CONFIG));
        if (config.get(CLIENT_SECRET) != null) {
            settingsUtils.changeSetting(FacebookConstants.CLIENT_SECRET_SETTING, config.get(CLIENT_SECRET));
        }
        String ids = validateIdentities((List<Map<String, String>>) config.get(ALLOWED_IDENTITIES));
        settingsUtils.changeSetting(FacebookConstants.ALLOWED_IDENTITIES_SETTING, ids);
        settingsUtils.changeSetting(FacebookConstants.ACCESSMODE_SETTING, config.get(AbstractTokenUtil.ACCESSMODE));
        if (config.get(SecurityConstants.ENABLED) != null){
            settingsUtils.changeSetting(SecurityConstants.AUTH_PROVIDER_SETTING, FacebookConstants.CONFIG);
        } else {
            settingsUtils.changeSetting(SecurityConstants.AUTH_PROVIDER_SETTING, SecurityConstants.NO_PROVIDER);
        }
        return getCurrentConfig(config);
    }

    public String getName() {
        return FacebookConstants.MANAGER;
    }

    public String validateIdentities(List<Map<String, String>> identitiesGiven) {
        StringBuilder sb = new StringBuilder();
        List<Identity> identities = getIdentities(identitiesGiven);
        Iterator<Identity> identityIterator = identities.iterator();
        while (identityIterator.hasNext()){
            sb.append(identityIterator.next().getId().trim());
            if (identityIterator.hasNext()) sb.append(',');
        }
        return sb.toString();
    }

    private List<Identity> getIdentities(List<Map<String, String>> identitiesGiven) {
        if (identitiesGiven == null || identitiesGiven.isEmpty()){
            return new ArrayList<>();
        }
        if (!client.isConfigured()) {
            throw new ClientVisibleException(ResponseCodes.SERVICE_UNAVAILABLE, "FacebookNotConfigured",
                    "Facebook client id and secret not set.", null);
        }

        List<Identity> identities = new ArrayList<>();
        for (Map<String, String> identity: identitiesGiven){
            String externalId = identity.get(IdentityConstants.EXTERNAL_ID);
            String externalIdType = identity.get(IdentityConstants.EXTERNAL_ID_TYPE);
            Identity gotIdentity = facebookIdentitySearchProvider.getIdentity(externalId, externalIdType);
            if (gotIdentity == null) {
                throw new ClientVisibleException(ResponseCodes.BAD_REQUEST, "InvalidIdentity", "Invalid Identity", null);
            }
            identities.add(gotIdentity);
        }
        return identities;
    }
}
