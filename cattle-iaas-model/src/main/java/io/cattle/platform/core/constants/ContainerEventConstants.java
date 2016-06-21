package io.cattle.platform.core.constants;


public class ContainerEventConstants {
    public static final String EVENT_STOP = "stop";
    public static final String EVENT_START = "start";
    public static final String EVENT_DIE = "die";
    public static final String EVENT_DESTROY = "destroy";
    
    public static final String CONTAINER_EVENT_SYNC_NAME = "container.event.sync.name";
    public static final String CONTAINER_EVENT_SYNC_LABELS = "container.event.sync.labels";
    
    public static final String LABEL_RANCHER_SYSTEM_CONTAINER = "io.rancher.container.system";
    public static final String LABEL_RANCHER_UUID = "io.rancher.container.uuid";
    public static final String RANCHER_NETWORK = "io.rancher.container.network";
    public static final String LABEL_DISPLAY_NAME = "io.rancher.container.display_name";
    
    public static final String CONTAINER_EVENT_KIND = "containerEvent";
}
