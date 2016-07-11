package io.cattle.platform.core.constants;

public class AgentConstants {

    public static final String TYPE = "agent";
    public static final String USER = "user";
    public static final String STATE_RECONNECTING = "reconnecting";
    public static final String ID_REF = "agentId";

    public static final String DATA_AGENT_RESOURCES_ACCOUNT_ID = "agentResourcesAccountId";
    public static final String DATA_ACCOUNT_DATA = "accountData";

    public static final String PROCESS_ACTIVATE = "agent.activate";
    public static final String PROCESS_RECONNECT = "agent.reconnect";
    public static final String PROCESS_DEACTIVATE = "agent.deactivate";
    public static final String PROCESS_REMOVE = "agent.remove";

    public static final String REMOVE_OPTION = "remove";

    public static final String AGENT_INSTANCE_BIND_MOUNT = "/var/lib/rancher/etc:/var/lib/rancher/etc:ro";

}
