package io.cattle.platform.core.addon;

import io.github.ibuildthecloud.gdapi.annotation.Field;
import io.github.ibuildthecloud.gdapi.annotation.Type;

import java.util.List;

@Type(list = false, parent = "serviceUpgradeStrategy")
public class InServiceUpgradeStrategy extends ServiceUpgradeStrategy {
    Object launchConfig;
    List<Object> secondaryLaunchConfigs;
    Object previousLaunchConfig;
    List<Object> previousSecondaryLaunchConfigs;
    boolean startFirst;

    public Object getLaunchConfig() {
        return launchConfig;
    }

    public void setLaunchConfig(Object launchConfig) {
        this.launchConfig = launchConfig;
    }

    public List<Object> getSecondaryLaunchConfigs() {
        return secondaryLaunchConfigs;
    }

    public void setSecondaryLaunchConfigs(List<Object> secondaryLaunchConfigs) {
        this.secondaryLaunchConfigs = secondaryLaunchConfigs;
    }

    public Object getPreviousLaunchConfig() {
        return previousLaunchConfig;
    }

    public void setPreviousLaunchConfig(Object previousLaunchConfig) {
        this.previousLaunchConfig = previousLaunchConfig;
    }

    public List<Object> getPreviousSecondaryLaunchConfigs() {
        return previousSecondaryLaunchConfigs;
    }

    public void setPreviousSecondaryLaunchConfigs(List<Object> previousSecondaryLaunchConfigs) {
        this.previousSecondaryLaunchConfigs = previousSecondaryLaunchConfigs;
    }

    @Field(nullable = false, defaultValue = "false")
    public boolean getStartFirst() {
        return startFirst;
    }

    public void setStartFirst(boolean startFirst) {
        this.startFirst = startFirst;
    }

    public boolean isFullUpgrade() {
        boolean primaryUpgrade = this.launchConfig != null && this.previousLaunchConfig != null;
        boolean isEmptySec = this.secondaryLaunchConfigs == null || this.secondaryLaunchConfigs.isEmpty();
        boolean isEmptyPrevSesc = this.previousSecondaryLaunchConfigs == null
                || this.previousSecondaryLaunchConfigs.isEmpty();

        boolean allSecondaryUpgrades = (isEmptySec == isEmptyPrevSesc)
                && (isEmptySec || this.secondaryLaunchConfigs.size() == this.previousSecondaryLaunchConfigs.size());
        return primaryUpgrade && allSecondaryUpgrades;
    }
}
