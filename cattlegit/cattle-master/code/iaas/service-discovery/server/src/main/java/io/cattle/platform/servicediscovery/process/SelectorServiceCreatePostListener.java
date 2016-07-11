package io.cattle.platform.servicediscovery.process;


import static io.cattle.platform.core.model.tables.InstanceTable.INSTANCE;
import static io.cattle.platform.core.model.tables.ServiceTable.SERVICE;
import io.cattle.platform.core.addon.LoadBalancerServiceLink;
import io.cattle.platform.core.addon.ServiceLink;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.Service;
import io.cattle.platform.core.model.ServiceExposeMap;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.handler.ProcessPostListener;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.lock.LockCallbackNoReturn;
import io.cattle.platform.lock.LockManager;
import io.cattle.platform.object.process.StandardProcess;
import io.cattle.platform.process.common.handler.AbstractObjectProcessLogic;
import io.cattle.platform.servicediscovery.api.constants.ServiceDiscoveryConstants;
import io.cattle.platform.servicediscovery.api.dao.ServiceConsumeMapDao;
import io.cattle.platform.servicediscovery.api.dao.ServiceExposeMapDao;
import io.cattle.platform.servicediscovery.deployment.impl.lock.ServiceInstanceLock;
import io.cattle.platform.servicediscovery.service.ServiceDiscoveryService;
import io.cattle.platform.util.type.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

public class SelectorServiceCreatePostListener extends AbstractObjectProcessLogic implements ProcessPostListener,
        Priority {

    @Inject
    ServiceDiscoveryService sdService;

    @Inject
    ServiceExposeMapDao exposeMapDao;

    @Inject
    LockManager lockManager;

    @Inject
    ServiceConsumeMapDao consumeMapDao;

    @Override
    public String[] getProcessNames() {
        return new String[] { ServiceDiscoveryConstants.PROCESS_SERVICE_CREATE, ServiceDiscoveryConstants.PROCESS_SERVICE_UPDATE };
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        Service service = (Service)state.getResource();

        registerServiceLinks(service);
        registerInstances(service);

        if (process.getName().equalsIgnoreCase(ServiceDiscoveryConstants.PROCESS_SERVICE_UPDATE)) {
            cleanupOldSelectorLinks(state, service);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    protected void cleanupOldSelectorLinks(ProcessState state, Service service) {
        String selectorLink = service.getSelectorLink();
        String oldSelectorLink = "";
        Object oldObj = state.getData().get("old");
        if (oldObj != null) {
            Map<String, Object> old = (Map<String, Object>) oldObj;
            if (old.containsKey(ServiceDiscoveryConstants.FIELD_SELECTOR_LINK)) {
                oldSelectorLink = old.get(ServiceDiscoveryConstants.FIELD_SELECTOR_LINK).toString();
            }
        }
        if (!StringUtils.isEmpty(oldSelectorLink) && !oldSelectorLink.equalsIgnoreCase(selectorLink)) {
            deregisterOldServiceLinks(service, oldSelectorLink);
        }
    }

    protected void registerServiceLinks(Service service) {
        List<Service> services = objectManager.find(Service.class, SERVICE.ACCOUNT_ID, service.getAccountId(),
                SERVICE.REMOVED, null);

        for (Service targetService : services) {
            // skip itself
            if (targetService.getId().equals(service.getId())) {
                continue;
            }
            if (sdService.isSelectorLinkMatch(service.getSelectorLink(), targetService)) {
                addServiceLink(service, targetService);
            }
            if (sdService.isSelectorLinkMatch(targetService.getSelectorLink(), service)) {
                addServiceLink(targetService, service);
            }
        }
    }

    protected void deregisterOldServiceLinks(Service service, String selectorLink) {
        List<? extends Service> targetServices = consumeMapDao.findLinkedServices(service.getId());
        for (Service targetService : targetServices) {
            if (sdService.isSelectorLinkMatch(selectorLink, targetService)) {
                removeServiceLink(service, targetService);
            }
        }
    }


    protected void removeServiceLink(Service service, Service targetService) {
        ServiceLink link = null;
        if (service.getKind().equalsIgnoreCase(ServiceDiscoveryConstants.KIND_LOAD_BALANCER_SERVICE)) {
            link = new LoadBalancerServiceLink(targetService.getId(), null, new ArrayList<String>());
        } else {
            link = new ServiceLink(targetService.getId(), null);
        }
        sdService.removeServiceLink(service, link);
    }

    protected void addServiceLink(Service service, Service targetService) {
        ServiceLink link = null;
        if (service.getKind().equalsIgnoreCase(ServiceDiscoveryConstants.KIND_LOAD_BALANCER_SERVICE)) {
            link = new LoadBalancerServiceLink(targetService.getId(), null, new ArrayList<String>());
        } else {
            link = new ServiceLink(targetService.getId(), null);
        }
        sdService.addServiceLink(service, link);
    }

    protected void registerInstances(final Service service) {
        List<Instance> instances = objectManager.find(Instance.class, INSTANCE.ACCOUNT_ID, service.getAccountId(),
                INSTANCE.REMOVED, null);

        List<? extends ServiceExposeMap> current = exposeMapDao.getUnmanagedServiceInstanceMapsToRemove(service.getId());
        final Map<Long, ServiceExposeMap> currentMappedInstances = new HashMap<Long, ServiceExposeMap>();
        for (ServiceExposeMap map : current) {
            currentMappedInstances.put(map.getInstanceId(), map);
        }

        for (final Instance instance : instances) {
            boolean matched = sdService.isSelectorContainerMatch(service.getSelectorContainer(), instance.getId());
            if (matched && !currentMappedInstances.containsKey(instance.getId())) {
                lockManager.lock(new ServiceInstanceLock(service, instance), new LockCallbackNoReturn() {
                    @Override
                    public void doWithLockNoResult() {
                        ServiceExposeMap exposeMap = exposeMapDao.createServiceInstanceMap(service, instance, false);
                        if (exposeMap.getState().equalsIgnoreCase(CommonStatesConstants.REQUESTED)) {
                            objectProcessManager.scheduleStandardProcessAsync(StandardProcess.CREATE, exposeMap,
                                    null);
                        }
                    }
                });
            } else if (!matched && currentMappedInstances.containsKey(instance.getId())) {
                lockManager.lock(new ServiceInstanceLock(service, instance), new LockCallbackNoReturn() {
                    @Override
                    public void doWithLockNoResult() {
                        objectProcessManager.scheduleStandardProcessAsync(StandardProcess.REMOVE, currentMappedInstances.get(instance.getId()),
                                null);
                    }
                });
            }
        }
    }

    @Override
    public int getPriority() {
        return Priority.DEFAULT;
    }

}
