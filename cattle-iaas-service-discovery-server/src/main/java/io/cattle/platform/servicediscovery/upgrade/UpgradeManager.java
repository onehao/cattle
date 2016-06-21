package io.cattle.platform.servicediscovery.upgrade;

import io.cattle.platform.core.addon.RollingRestartStrategy;
import io.cattle.platform.core.addon.ServiceUpgradeStrategy;
import io.cattle.platform.core.model.Service;

public interface UpgradeManager {

    void upgrade(Service service, ServiceUpgradeStrategy strategy);

    void rollback(Service service, ServiceUpgradeStrategy strategy);

    void finishUpgrade(Service service, boolean reconcile);

    void restart(Service service, RollingRestartStrategy strategy);

}
