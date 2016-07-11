package io.cattle.platform.allocator.constraint;

import io.cattle.platform.allocator.dao.AllocatorDao;
import io.cattle.platform.allocator.service.AllocationAttempt;
import io.cattle.platform.allocator.service.AllocationCandidate;
import io.cattle.platform.core.constants.PortConstants;
import io.cattle.platform.core.model.Port;
import io.cattle.platform.object.util.DataAccessor;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class PortsConstraint extends HardConstraint implements Constraint {

    AllocatorDao allocatorDao;

    List<Port> ports;

    public PortsConstraint(List<Port> ports, AllocatorDao allocatorDao) {
        this.ports = ports;
        this.allocatorDao = allocatorDao;
    }

    @Override
    public boolean matches(AllocationAttempt attempt,
            AllocationCandidate candidate) {
        Set<Long> hostIds = candidate.getHosts();

        for (Long hostId : hostIds) {
            // TODO: Performance improvement. Move more of the filtering into the DB query itself
            List<Port> portsUsedByHost = allocatorDao.getUsedPortsForHostExcludingInstance(hostId, attempt.getInstanceId());
            for (Port portUsed : portsUsedByHost) {
                for (Port requestedPort : ports) {
                    if (requestedPort.getPublicPort() != null &&
                            requestedPort.getPublicPort().equals(portUsed.getPublicPort()) &&
                            publicIpTheSame(requestedPort, portUsed) &&
                            requestedPort.getProtocol().equals(portUsed.getProtocol())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean publicIpTheSame(Port requestedPort, Port portUsed) {
        if (requestedPort.getPublicIpAddressId() != null) {
            return requestedPort.getPublicIpAddressId().equals(portUsed.getPublicIpAddressId());
        } else {
            String requestedIp = DataAccessor.fields(requestedPort).withKey(PortConstants.FIELD_BIND_ADDR).as(String.class);
            String usedIp = DataAccessor.fields(portUsed).withKey(PortConstants.FIELD_BIND_ADDR).as(String.class);
            return StringUtils.equals(requestedIp, usedIp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Port port: ports) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(port.getPublicPort());
            sb.append("/");
            sb.append(port.getProtocol());
        }
        return String.format("host needs ports %s available", sb.toString());
    }
}
