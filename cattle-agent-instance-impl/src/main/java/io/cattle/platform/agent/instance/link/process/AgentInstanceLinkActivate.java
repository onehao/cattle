package io.cattle.platform.agent.instance.link.process;

import io.cattle.platform.agent.instance.service.AgentInstanceManager;
import io.cattle.platform.agent.instance.service.NetworkServiceInfo;
import io.cattle.platform.archaius.util.ArchaiusUtil;
import io.cattle.platform.async.utils.TimeoutException;
import io.cattle.platform.core.constants.InstanceLinkConstants;
import io.cattle.platform.core.constants.NetworkServiceConstants;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.InstanceLink;
import io.cattle.platform.core.model.Port;
import io.cattle.platform.core.util.PortSpec;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.object.resource.ResourceMonitor;
import io.cattle.platform.object.resource.ResourcePredicate;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.process.common.handler.AbstractObjectProcessHandler;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.DynamicLongProperty;

public class AgentInstanceLinkActivate extends AbstractObjectProcessHandler {

    private static final DynamicLongProperty WAIT_TIME = ArchaiusUtil.getLong("instance.link.target.wait.time.millis");
    private static final Logger log = LoggerFactory.getLogger(AgentInstanceLinkActivate.class);

    @Inject
    AgentInstanceManager agentInstanceManager;
    @Inject
    ResourceMonitor resourceMonitor;

    @Override
    public String[] getProcessNames() {
        return new String[] { "instancelink.activate" };
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        InstanceLink link = (InstanceLink) state.getResource();
        Instance instance = loadResource(Instance.class, link.getInstanceId());
        Instance targetInstance = loadResource(Instance.class, link.getTargetInstanceId());

        if (instance == null) {
            return null;
        }

        NetworkServiceInfo info = agentInstanceManager.getNetworkService(instance, NetworkServiceConstants.KIND_LINK, true);

        if (info == null) {
            return null;
        }

        if (info.getIpAddress() == null || info.getIpAddress().getAddress() == null) {
            log.error("IP address for agent instance is not set");
            return null;
        }

        long timeout = DataAccessor.fromDataFieldOf(instance).withKey(InstanceLinkConstants.DATA_LINK_WAIT_TIME).withDefault(WAIT_TIME.get()).as(Long.class);

        try {
            targetInstance = resourceMonitor.waitFor(targetInstance, timeout, new ResourcePredicate<Instance>() {
                @Override
                public boolean evaluate(Instance obj) {
                    return obj.getFirstRunning() != null;
                }
            });
        } catch (TimeoutException e) {
            /* We are going to ignore this now and just continue forward with no ports.  This is really only needed for the
             * env vars which are not commonly used
             */
        }

        List<Port> ports = children(targetInstance, Port.class);

        if (ports.size() == 0) {
            return null;
        }

        List<PortSpec> result = new ArrayList<PortSpec>();

        String ipAddress = info.getIpAddress().getAddress();
        for (Port port : ports) {
            result.add(new PortSpec(ipAddress, port.getPrivatePort(), port));
        }

        return new HandlerResult(InstanceLinkConstants.FIELD_PORTS, result).withShouldContinue(true);
    }
}
