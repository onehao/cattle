package io.cattle.platform.servicediscovery.api.filter;

import io.cattle.platform.core.addon.InServiceUpgradeStrategy;
import io.cattle.platform.core.addon.ServiceUpgrade;
import io.cattle.platform.core.model.Service;
import io.cattle.platform.iaas.api.filter.common.AbstractDefaultResourceManagerFilter;
import io.cattle.platform.json.JsonMapper;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.servicediscovery.api.constants.ServiceDiscoveryConstants;
import io.cattle.platform.servicediscovery.api.util.ServiceDiscoveryUtil;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;
import io.github.ibuildthecloud.gdapi.request.resource.ResourceManager;

import javax.inject.Inject;

public class ServiceRollbackValidationFilter extends AbstractDefaultResourceManagerFilter {

    @Inject
    ObjectManager objectManager;

    @Inject
    JsonMapper jsonMapper;

    @Override
    public Class<?>[] getTypeClasses() {
        return new Class<?>[] { Service.class };
    }

    @Override
    public String[] getTypes() {
        return new String[] { "service", "dnsService", "externalService" };
    }

    @Override
    public Object resourceAction(String type, ApiRequest request, ResourceManager next) {
        if (request.getAction().equals(ServiceDiscoveryConstants.ACTION_SERVICE_ROLLBACK)) {
            Service service = objectManager.loadResource(Service.class, request.getId());
            ServiceUpgrade upgrade = DataAccessor.field(service, ServiceDiscoveryConstants.FIELD_UPGRADE,
                    jsonMapper,
                    ServiceUpgrade.class);

            if (upgrade != null && upgrade.getInServiceStrategy() != null) {
                InServiceUpgradeStrategy strategy = upgrade.getInServiceStrategy();
                if (strategy.getPreviousLaunchConfig() != null || strategy.getSecondaryLaunchConfigs() != null) {
                    ServiceDiscoveryUtil.upgradeServiceConfigs(service, strategy, true);
                }

                objectManager.persist(service);
            }
        }

        return super.resourceAction(type, request, next);
    }
}