package io.cattle.platform.register.process;

import static io.cattle.platform.core.model.tables.GenericObjectTable.*;
import io.cattle.platform.core.model.GenericObject;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.handler.ProcessPostListener;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.process.common.handler.AbstractObjectProcessLogic;
import io.cattle.platform.register.util.RegisterConstants;

public class RegisterTokenAgentRemove extends AbstractObjectProcessLogic implements ProcessPostListener {

    @Override
    public String[] getProcessNames() {
        return new String[] {"agent.remove"};
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        String key = DataAccessor.fromDataFieldOf(state.getResource())
                .withKey(RegisterConstants.AGENT_DATA_REGISTRATION_KEY).as(String.class);

        if (key != null) {
            GenericObject obj = objectManager.findAny(GenericObject.class,
                    GENERIC_OBJECT.KEY, key);
            deactivateThenScheduleRemove(obj, state.getData());
        }

        return null;
    }
}
