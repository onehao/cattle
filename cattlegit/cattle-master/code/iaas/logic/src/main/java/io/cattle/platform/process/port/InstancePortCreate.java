package io.cattle.platform.process.port;

import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.constants.PortConstants;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.Port;
import io.cattle.platform.core.util.PortSpec;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.handler.ProcessPostListener;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.process.StandardProcess;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.object.util.DataUtils;
import io.cattle.platform.process.common.handler.AbstractObjectProcessLogic;
import io.cattle.platform.util.type.Priority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

@Named
public class InstancePortCreate extends AbstractObjectProcessLogic implements ProcessPostListener, Priority {

    @Override
    public String[] getProcessNames() {
        return new String[] { "instance.create" };
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        Instance instance = (Instance)state.getResource();
        ObjectManager objectManager = getObjectManager();

        List<String> portDefs = DataUtils.getFieldList(instance.getData(), InstanceConstants.FIELD_PORTS, String.class);
        if (portDefs == null) {
            return null;
        }

        Map<String, Port> ports = new HashMap<>();
        for (Port port : objectManager.children(instance, Port.class)) {
            ports.put(toKey(port), port);
        }

        for (String port : portDefs) {
            PortSpec spec = new PortSpec(port);

            if (ports.containsKey(toKey(spec))) {
                continue;
            }

            Port portObj = objectManager.newRecord(Port.class);
            portObj.setAccountId(instance.getAccountId());
            portObj.setKind(PortConstants.KIND_USER);
            portObj.setInstanceId(instance.getId());
            portObj.setPublicPort(spec.getPublicPort());
            portObj.setPrivatePort(spec.getPrivatePort());
            portObj.setProtocol(spec.getProtocol());
            if (StringUtils.isNotEmpty(spec.getIpAddress())) {
                DataAccessor.fields(portObj).withKey(PortConstants.FIELD_BIND_ADDR).set(spec.getIpAddress());
            }
            portObj = objectManager.create(portObj);
            ports.put(toKey(portObj), portObj);
        }

        for (Port port : ports.values()) {
            getObjectProcessManager().executeStandardProcess(StandardProcess.CREATE, port, state.getData());
        }

        return null;
    }

    protected String toKey(PortSpec spec) {
        return String.format("%d:%d/%s", spec.getPublicPort(), spec.getPrivatePort(), spec.getProtocol());
    }

    protected String toKey(Port port) {
        return String.format("%d:%d/%s", port.getPublicPort(), port.getPrivatePort(), port.getProtocol());
    }

    @Override
    public int getPriority() {
        return Priority.DEFAULT;
    }

}
