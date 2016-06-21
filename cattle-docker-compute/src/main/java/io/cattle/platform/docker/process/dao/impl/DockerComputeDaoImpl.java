package io.cattle.platform.docker.process.dao.impl;

import static io.cattle.platform.core.model.tables.IpAddressNicMapTable.*;
import static io.cattle.platform.core.model.tables.IpAddressTable.*;
import static io.cattle.platform.core.model.tables.NicTable.*;
import static io.cattle.platform.core.model.tables.VolumeStoragePoolMapTable.*;
import static io.cattle.platform.core.model.tables.VolumeTable.*;
import static io.cattle.platform.docker.constants.DockerVolumeConstants.*;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.VolumeConstants;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.IpAddress;
import io.cattle.platform.core.model.StoragePool;
import io.cattle.platform.core.model.Volume;
import io.cattle.platform.core.model.VolumeStoragePoolMap;
import io.cattle.platform.core.model.tables.VolumeTable;
import io.cattle.platform.core.model.tables.records.IpAddressRecord;
import io.cattle.platform.core.model.tables.records.VolumeRecord;
import io.cattle.platform.db.jooq.dao.impl.AbstractJooqDao;
import io.cattle.platform.docker.constants.DockerIpAddressConstants;
import io.cattle.platform.docker.process.dao.DockerComputeDao;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.util.DataAccessor;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;

public class DockerComputeDaoImpl extends AbstractJooqDao implements DockerComputeDao {

    ObjectManager objectManager;

    @Override
    public IpAddress getDockerIp(String ip, Instance instance) {
        if ( instance == null || ip == null ) {
            return null;
        }

        List<IpAddressRecord> ips = create()
                .select(IP_ADDRESS.fields())
                .from(IP_ADDRESS)
                .join(IP_ADDRESS_NIC_MAP)
                    .on(IP_ADDRESS_NIC_MAP.IP_ADDRESS_ID.eq(IP_ADDRESS.ID))
                .join(NIC)
                    .on(IP_ADDRESS_NIC_MAP.NIC_ID.eq(NIC.ID))
                .where(NIC.INSTANCE_ID.eq(instance.getId())
                        .and(IP_ADDRESS.KIND.eq(DockerIpAddressConstants.KIND_DOCKER)
                                .or(IP_ADDRESS.ADDRESS.eq(ip))))
                .fetchInto(IpAddressRecord.class);

        if ( ips.size() == 0 ) {
            return null;
        } else if ( ips.size() > 1 ) {
            IpAddressRecord kindRecord = null;
            for ( IpAddressRecord record : ips ) {
                if ( record.getKind().equals(DockerIpAddressConstants.KIND_DOCKER) ) {
                    kindRecord = record;
                } else {
                    return record;
                }
            }

            return kindRecord;
        } else {
            return ips.get(0);
        }
    }

    @Override
    public Volume getDockerVolumeInPool(String volumeUri, String externalId, StoragePool storagePool) {
        if ( StringUtils.isEmpty(volumeUri) || storagePool == null )
            throw new IllegalArgumentException("Volume URI and storage pool must have values.");

        Condition condition = VOLUME.URI.eq(volumeUri);
        if (externalId != null) {
            condition = condition.or(VOLUME.EXTERNAL_ID.eq(externalId).or(VOLUME.NAME.eq(externalId)));
        }

        List<VolumeRecord> volumes = create()
                .select(VolumeTable.VOLUME.fields())
                    .from(VOLUME_STORAGE_POOL_MAP)
                        .join(VOLUME)
                            .on(VOLUME_STORAGE_POOL_MAP.VOLUME_ID.eq(VOLUME.ID))
                    .where(VOLUME_STORAGE_POOL_MAP.STORAGE_POOL_ID.eq(storagePool.getId()))
                        .and(condition)
                        .and(VOLUME_STORAGE_POOL_MAP.REMOVED.isNull())
                .fetchInto(VolumeRecord.class);

        if ( volumes.isEmpty() )
            return null;
        else if ( volumes.size() == 1 )
            return volumes.get(0);
        else
            throw new IllegalStateException(String.format(
                    "More than one volume exists for volume URI [%s] or name/externalId [%s] and storage pool [%s].", volumeUri,
                    externalId, storagePool.getId()));
    }

    @Override
    public Volume createDockerVolumeInPool(Long accountId, String name, String volumeUri, String externalId, String driver, StoragePool storagePool,
            boolean isHostPath) {
        Volume volume = getDockerVolumeInPool(volumeUri, externalId, storagePool);
        if (volume != null) {
            return volume;
        }

        volume = objectManager.create(Volume.class,
                VOLUME.ACCOUNT_ID, accountId,
                VOLUME.NAME, name,
                VOLUME.ATTACHED_STATE, CommonStatesConstants.INACTIVE,
                VOLUME.DEVICE_NUMBER, -1,
                VOLUME.ALLOCATION_STATE, CommonStatesConstants.ACTIVE,
                VOLUME.URI, volumeUri,
                VOLUME.EXTERNAL_ID, externalId);

        DataAccessor.fields(volume).withKey(FIELD_DOCKER_IS_HOST_PATH).set(isHostPath);
        DataAccessor.fields(volume).withKey(VolumeConstants.FIELD_VOLUME_DRIVER).set(driver);

        objectManager.persist(volume);

        objectManager.create(VolumeStoragePoolMap.class,
                VOLUME_STORAGE_POOL_MAP.VOLUME_ID, volume.getId(),
                VOLUME_STORAGE_POOL_MAP.STORAGE_POOL_ID, storagePool.getId());

        return volume;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    @Inject
    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }
}
