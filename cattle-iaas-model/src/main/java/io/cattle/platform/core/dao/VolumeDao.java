package io.cattle.platform.core.dao;

import io.cattle.platform.core.model.StoragePool;
import io.cattle.platform.core.model.Volume;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface VolumeDao {
    Volume findVolumeByExternalId(Long storagePoolId, String externalId);

    void createVolumeInStoragePool(Map<String, Object> volumeData, String volumeName, StoragePool storagePool);

    List<? extends Volume> findSharedOrUnmappedVolumes(long accountId, String volumeName);

    Set<? extends Volume> findNonremovedVolumesWithNoOtherMounts(long instanceId);

    boolean isVolumeInUseByRunningInstance(long volumeId);
}
