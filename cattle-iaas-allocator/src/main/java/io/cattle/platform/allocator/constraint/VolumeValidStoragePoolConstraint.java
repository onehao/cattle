package io.cattle.platform.allocator.constraint;

import io.cattle.platform.allocator.service.AllocationAttempt;
import io.cattle.platform.allocator.service.AllocationCandidate;
import io.cattle.platform.core.model.Volume;

import java.util.HashSet;
import java.util.Set;

public class VolumeValidStoragePoolConstraint extends HardConstraint implements Constraint {

    Volume volume;
    Set<Long> storagePools = new HashSet<Long>();
    boolean exactMatch;

    public VolumeValidStoragePoolConstraint(Volume volume, boolean exactMatch) {
        super();
        this.volume = volume;
        this.exactMatch = exactMatch;
    }

    public Set<Long> getStoragePools() {
        return storagePools;
    }

    @Override
    public boolean matches(AllocationAttempt attempt, AllocationCandidate candidate) {
        Set<Long> poolIds = candidate.getPools().get(volume.getId());

        if (storagePools.size() == 0 && poolIds.size() == 0) {
            return true;
        }

        if (exactMatch) {
            return storagePools.equals(poolIds);
        }

        for (Long poolId : poolIds) {
            if (!storagePools.contains(poolId)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String matchText = exactMatch ? "have exactly these pool(s): " : "must be one of pool(s)";
        return String.format("volume [%d] %s %s", volume.getId(), matchText, storagePools);
    }

}
