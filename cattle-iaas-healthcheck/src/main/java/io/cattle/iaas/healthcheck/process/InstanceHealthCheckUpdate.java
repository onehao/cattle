package io.cattle.iaas.healthcheck.process;

import static io.cattle.platform.core.model.tables.InstanceTable.INSTANCE;
import io.cattle.platform.core.constants.HealthcheckConstants;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.handler.ProcessPostListener;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.process.common.handler.AbstractObjectProcessLogic;

import java.util.Date;

public class InstanceHealthCheckUpdate extends AbstractObjectProcessLogic implements ProcessPostListener {

    @Override
    public String[] getProcessNames() {
        return new String[] { HealthcheckConstants.PROCESS_UPDATE_HEALTHY,
                HealthcheckConstants.PROCESS_UPDATE_UNHEALTHY, HealthcheckConstants.PROCESS_UPDATE_REINITIALIZING };
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        return new HandlerResult(INSTANCE.HEALTH_UPDATED, new Date());
    }
}

