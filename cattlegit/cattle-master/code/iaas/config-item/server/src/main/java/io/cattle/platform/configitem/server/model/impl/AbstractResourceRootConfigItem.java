package io.cattle.platform.configitem.server.model.impl;

import io.cattle.platform.configitem.server.model.RefreshableConfigItem;
import io.cattle.platform.configitem.server.resource.ResourceRoot;
import io.cattle.platform.configitem.version.ConfigItemStatusManager;

import java.io.IOException;

public abstract class AbstractResourceRootConfigItem extends AbstractConfigItem implements RefreshableConfigItem {

    ResourceRoot resourceRoot;

    public AbstractResourceRootConfigItem(String name, ConfigItemStatusManager versionManager, ResourceRoot resourceRoot) {
        super(name, versionManager);
        this.resourceRoot = resourceRoot;
    }

    @Override
    public String getSourceRevision() {
        return resourceRoot.getSourceRevision();
    }

    @Override
    public void refresh() throws IOException {
        resourceRoot.scan();
    }

    public ResourceRoot getResourceRoot() {
        return resourceRoot;
    }

}
