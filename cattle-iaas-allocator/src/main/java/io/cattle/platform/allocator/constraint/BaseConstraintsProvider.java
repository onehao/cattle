package io.cattle.platform.allocator.constraint;

import io.cattle.platform.allocator.dao.AllocatorDao;
import io.cattle.platform.allocator.service.AllocationAttempt;
import io.cattle.platform.allocator.service.AllocationLog;
import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.constants.VolumeConstants;
import io.cattle.platform.core.dao.GenericMapDao;
import io.cattle.platform.core.dao.StoragePoolDao;
import io.cattle.platform.core.model.Host;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.Nic;
import io.cattle.platform.core.model.StoragePool;
import io.cattle.platform.core.model.Subnet;
import io.cattle.platform.core.model.Vnet;
import io.cattle.platform.core.model.Volume;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.object.util.DataUtils;
import io.cattle.platform.util.type.Priority;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

public class BaseConstraintsProvider implements AllocationConstraintsProvider, Priority {

    @Inject
    AllocatorDao allocatorDao;
    @Inject
    ObjectManager objectManager;
    @Inject
    GenericMapDao mapDao;
    @Inject
    StoragePoolDao storagePoolDao;

    @Override
    public void appendConstraints(AllocationAttempt attempt, AllocationLog log, List<Constraint> constraints) {
        populateConstraints(attempt, log);
    }

    protected void populateConstraints(AllocationAttempt attempt, AllocationLog log) {
        List<Constraint> constraints = attempt.getConstraints();
        addLogConstraints(attempt, log);
        addComputeConstraints(attempt, constraints);
        addNetworkConstraints(attempt, constraints);
        addStorageConstraints(attempt, constraints);
    }

    protected void addLogConstraints(AllocationAttempt attempt, AllocationLog log) {
    }

    protected void addNetworkConstraints(AllocationAttempt attempt, List<Constraint> constraints) {
        for (Nic nic : attempt.getNics()) {
            Long vnetId = nic.getVnetId();
            Long subnetId = nic.getSubnetId();

            ValidSubnetsConstraint constraint = new ValidSubnetsConstraint(nic.getId());
            if (subnetId != null) {
                constraint.addSubnet(subnetId);
            }

            if (vnetId != null) {
                Vnet vnet = objectManager.loadResource(Vnet.class, vnetId);
                if (vnet != null) {
                    for (Subnet subnet : objectManager.mappedChildren(vnet, Subnet.class)) {
                        constraint.addSubnet(subnet.getId());
                    }
                }
            }

            if (constraint.getSubnets().size() > 0) {
                constraints.add(constraint);
            }
        }
    }

    protected void addComputeConstraints(AllocationAttempt attempt, List<Constraint> constraints) {
        ValidHostsConstraint hostSet = new ValidHostsConstraint();
        for (Host host : attempt.getHosts()) {
            hostSet.addHost(host.getId());
        }

        Instance instance = attempt.getInstance();
        if (instance != null) {
            Long requestedHostId = DataAccessor.fieldLong(instance, InstanceConstants.FIELD_REQUESTED_HOST_ID);
            if (requestedHostId != null) {
                hostSet.addHost(requestedHostId);
            }

            List<Long> validHosts = DataUtils.getFieldList(instance.getData(), InstanceConstants.FIELD_VALID_HOST_IDS, Long.class);
            if (validHosts != null) {
                for (Long id : validHosts) {
                    if (id != null) {
                        hostSet.addHost(id);
                    }
                }
            }
        }

        if (hostSet.getHosts().size() > 0) {
            constraints.add(hostSet);
        }
    }

    protected void addStorageConstraints(AllocationAttempt attempt, List<Constraint> constraints) {
        for (Map.Entry<Volume, Set<StoragePool>> entry : attempt.getPools().entrySet()) {
            Volume volume = entry.getKey();
            boolean alreadyMappedToPool = entry.getValue().size() > 0;
            VolumeValidStoragePoolConstraint volumeToPoolConstraint = new VolumeValidStoragePoolConstraint(volume, alreadyMappedToPool);
            for (StoragePool pool : entry.getValue()) {
                volumeToPoolConstraint.getStoragePools().add(pool.getId());
                storagePoolToHostConstraint(constraints, pool);
            }

            if (!alreadyMappedToPool) {
                String driver = DataAccessor.fieldString(volume, VolumeConstants.FIELD_VOLUME_DRIVER);
                boolean restrictToUnmanagedPool = true;
                if (StringUtils.isNotEmpty(driver) && !VolumeConstants.LOCAL_DRIVER.equals(driver)) {
                    StoragePool pool = storagePoolDao.findStoragePoolByDriverName(volume.getAccountId(), driver);
                    if (pool != null) {
                        VolumeValidStoragePoolConstraint spByDriverConstraint = new VolumeValidStoragePoolConstraint(volume, alreadyMappedToPool);
                        spByDriverConstraint.getStoragePools().add(pool.getId());
                        constraints.add(spByDriverConstraint);
                        storagePoolToHostConstraint(constraints, pool);
                        restrictToUnmanagedPool = false;
                    }
                }

                if (restrictToUnmanagedPool) {
                    constraints.add(new UnmanagedStoragePoolKindConstraint(volume));
                }

                Instance instance = objectManager.loadResource(Instance.class, volume.getInstanceId());
                if (instance != null) {
                    for (Host host : allocatorDao.getHosts(instance)) {
                        for (StoragePool pool : allocatorDao.getAssociatedUnmanagedPools(host)) {
                            volumeToPoolConstraint.getStoragePools().add(pool.getId());
                        }
                    }
                }
            }

            if (volumeToPoolConstraint.getStoragePools().size() > 0) {
                constraints.add(volumeToPoolConstraint);
            }

            if (volume.getImageId() != null) {
                constraints.add(new UnmanagedStoragePoolKindConstraint(volume));
            }
        }

        if (attempt.getInstance() != null) {
            String driver = DataAccessor.fieldString(attempt.getInstance(), InstanceConstants.FIELD_VOLUME_DRIVER);
            if (StringUtils.isNotEmpty(driver) && !VolumeConstants.LOCAL_DRIVER.equals(driver)) {
                StoragePool pool = storagePoolDao.findStoragePoolByDriverName(attempt.getInstance().getAccountId(), driver);
                if (pool != null) {
                    storagePoolToHostConstraint(constraints, pool);
                }
            }
        }
    }

    void storagePoolToHostConstraint(List<Constraint> constraints, StoragePool pool) {
        ValidHostsConstraint hostSet = new ValidHostsConstraint();
        for (Host host : allocatorDao.getHosts(pool)) {
            hostSet.addHost(host.getId());
        }
        constraints.add(hostSet);
    }

    @Override
    public int getPriority() {
        return Priority.PRE;
    }

    @Override
    public boolean isCritical() {
        return true;
    }
}
