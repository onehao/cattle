package io.cattle.platform.configitem.context.data.metadata.version2;

import io.cattle.platform.configitem.context.data.metadata.common.ContainerMetaData;
import io.cattle.platform.configitem.context.data.metadata.common.ServiceMetaData;

import java.util.List;

public class ServiceMetaDataVersion2 extends ServiceMetaData {

    public ServiceMetaDataVersion2(ServiceMetaData serviceData) {
        super(serviceData);
    }

    public void setContainers(List<ContainerMetaData> containers) {
        this.containers = containers;
    }

    public List<ContainerMetaData> getContainers() {
        return containers;
    }

    public Long getCreate_index() {
        return create_index;
    }

    public void setCreate_index(Long create_index) {
        this.create_index = create_index;
    }
}
