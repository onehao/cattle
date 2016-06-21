package io.cattle.platform.servicediscovery.process;

import io.cattle.platform.core.model.ServiceIndex;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.process.common.handler.AbstractObjectProcessHandler;
import io.cattle.platform.servicediscovery.api.constants.ServiceDiscoveryConstants;
import io.cattle.platform.servicediscovery.service.ServiceDiscoveryService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ServiceSuffixRemove extends AbstractObjectProcessHandler {

    @Inject
    ServiceDiscoveryService sdService;

    @Override
    public String[] getProcessNames() {
        return new String[] { ServiceDiscoveryConstants.PROCESS_SERVICE_INDEX_REMOVE };
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        ServiceIndex serviceIndex = (ServiceIndex) state.getResource();

        sdService.releaseIpFromServiceIndex(serviceIndex);

        return null;
    }
}
