package io.cattle.platform.core.dao;

import io.cattle.platform.core.model.Host;
import io.cattle.platform.core.model.IpAddress;

import java.util.List;

public interface HostDao {

    List<? extends Host> getHosts(Long accountId, List<String> uuids);

    List<? extends Host> getActiveHosts(Long accountId);

    Host getHostForIpAddress(long ipAddressId);

    IpAddress getIpAddressForHost(Long hostId);

    boolean isServiceSupportedOnHost(long hostId, long networkId, String serviceKind);
}
