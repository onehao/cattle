package io.cattle.platform.allocator.constraint;

import io.cattle.platform.allocator.constraint.AffinityConstraintDefinition.AffinityOps;
import io.cattle.platform.allocator.service.AllocationAttempt;
import io.cattle.platform.allocator.service.AllocationCandidate;
import io.cattle.platform.core.dao.InstanceDao;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.object.ObjectManager;

import java.util.List;
import java.util.Set;

public class ContainerAffinityConstraint implements Constraint {

    public static final String ENV_HEADER_AFFINITY_CONTAINER = "affinity:container";
    public static final String LABEL_HEADER_AFFINITY_CONTAINER = "io.rancher.scheduler." + ENV_HEADER_AFFINITY_CONTAINER;

    AffinityOps op;
    String containerIdentifier;
    ObjectManager objectManager;
    InstanceDao instanceDao;

    // TODO: Might actually do an early lookup for host lists as an optimization
    public ContainerAffinityConstraint(AffinityConstraintDefinition affinityDef, ObjectManager objectManager, InstanceDao instanceDao) {
        this.op = affinityDef.op;
        this.containerIdentifier = affinityDef.value;
        this.objectManager = objectManager;
        this.instanceDao = instanceDao;
    }

    @Override
    public boolean matches(AllocationAttempt attempt,
            AllocationCandidate candidate) {
        Set<Long> hostIds = candidate.getHosts();
        if (op == AffinityOps.SOFT_EQ || op == AffinityOps.EQ) {
            for (Long hostId : hostIds) {
                List<? extends Instance> instances = instanceDao.getNonRemovedInstanceOn(hostId);
                for (Instance instance : instances) {
                    if (containerIdentifier != null && 
                            (containerIdentifier.equalsIgnoreCase(instance.getName()) ||
                                    containerIdentifier.equalsIgnoreCase(instance.getUuid()))) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            for (Long hostId : hostIds) {
                List<? extends Instance> instances = instanceDao.getNonRemovedInstanceOn(hostId);
                for (Instance instance : instances) {
                    if (containerIdentifier != null &&
                            (containerIdentifier.equals(instance.getName()) || containerIdentifier.equals(instance.getUuid()))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    @Override
    public boolean isHardConstraint() {
        return (op == AffinityOps.EQ || op == AffinityOps.NE);
    }

    @Override
    public String toString() {
        return String.format("needs container with label %s%s", containerIdentifier, op.getLabelSymbol());
    }
}
