package io.cattle.platform.core.dao.impl;

import static io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP;
import static io.cattle.platform.core.model.tables.HostTable.HOST;
import static io.cattle.platform.core.model.tables.InstanceHostMapTable.INSTANCE_HOST_MAP;
import static io.cattle.platform.core.model.tables.IpAddressTable.IP_ADDRESS;
import static io.cattle.platform.core.model.tables.NetworkServiceProviderTable.NETWORK_SERVICE_PROVIDER;
import static io.cattle.platform.core.model.tables.NetworkServiceTable.NETWORK_SERVICE;
import static io.cattle.platform.core.model.tables.NicTable.NIC;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.NetworkServiceProviderConstants;
import io.cattle.platform.core.dao.HostDao;
import io.cattle.platform.core.model.Host;
import io.cattle.platform.core.model.IpAddress;
import io.cattle.platform.core.model.tables.records.HostRecord;
import io.cattle.platform.core.model.tables.records.IpAddressRecord;
import io.cattle.platform.db.jooq.dao.impl.AbstractJooqDao;
import io.cattle.platform.object.ObjectManager;

import java.util.List;

import javax.inject.Inject;

public class HostDaoImpl extends AbstractJooqDao implements HostDao {

    @Inject
    ObjectManager objectManager;

    @Override
    public List<? extends Host> getHosts(Long accountId, List<String> uuids) {
        return create()
            .selectFrom(HOST)
            .where(HOST.ACCOUNT_ID.eq(accountId)
            .and(HOST.STATE.notIn(CommonStatesConstants.REMOVED, CommonStatesConstants.REMOVING))
            .and(HOST.UUID.in(uuids))).fetch();
    }

    @Override
    public List<? extends Host> getActiveHosts(Long accountId) {
        return create()
            .selectFrom(HOST)
            .where(HOST.ACCOUNT_ID.eq(accountId)
            .and(HOST.STATE.in(CommonStatesConstants.ACTIVATING, CommonStatesConstants.ACTIVE)))
            .fetch();
    }

    @Override
    public IpAddress getIpAddressForHost(Long hostId) {
        return create()
            .select(IP_ADDRESS.fields())
                .from(IP_ADDRESS)
                .join(HOST_IP_ADDRESS_MAP)
                    .on(IP_ADDRESS.ID.eq(HOST_IP_ADDRESS_MAP.IP_ADDRESS_ID))
                .where(HOST_IP_ADDRESS_MAP.HOST_ID.eq(hostId)
                    .and(HOST_IP_ADDRESS_MAP.REMOVED.isNull())
                    .and(IP_ADDRESS.REMOVED.isNull()))
            .fetchOneInto(IpAddressRecord.class);
    }

    @Override
    public Host getHostForIpAddress(long ipAddressId) {
        List<? extends Host> hosts = create()
                .select(HOST.fields())
                .from(HOST)
                .join(HOST_IP_ADDRESS_MAP)
                .on(HOST_IP_ADDRESS_MAP.HOST_ID.eq(HOST.ID))
                .where(HOST_IP_ADDRESS_MAP.IP_ADDRESS_ID.eq(ipAddressId)
                        .and(HOST_IP_ADDRESS_MAP.REMOVED.isNull()))
                .fetchInto(HostRecord.class);

        if (hosts.isEmpty()) {
            return null;
        }
        return hosts.get(0);

    }

    @Override
    public boolean isServiceSupportedOnHost(long hostId, long networkId, String serviceKind) {
        List<Long> ids = create()
                .select(NETWORK_SERVICE_PROVIDER.ID)
                .from(NETWORK_SERVICE_PROVIDER)
                .join(NETWORK_SERVICE)
                .on(NETWORK_SERVICE_PROVIDER.NETWORK_ID.eq(NETWORK_SERVICE.NETWORK_ID))
                .join(NIC)
                .on(NIC.NETWORK_ID.eq(NETWORK_SERVICE.NETWORK_ID))
                .join(INSTANCE_HOST_MAP)
                .on(INSTANCE_HOST_MAP.INSTANCE_ID.eq(NIC.INSTANCE_ID))
                .where(INSTANCE_HOST_MAP.HOST_ID.eq(hostId)
                        .and(NETWORK_SERVICE.NETWORK_ID.eq(networkId))
                        .and(NETWORK_SERVICE.KIND.eq(serviceKind))
                        .and(NETWORK_SERVICE_PROVIDER.KIND.eq(NetworkServiceProviderConstants.KIND_AGENT_INSTANCE))
                        .and(NETWORK_SERVICE.REMOVED.isNull())
                        .and(INSTANCE_HOST_MAP.REMOVED.isNull())
                        .and(NIC.REMOVED.isNull()))
                .fetchInto(Long.class);

        if (ids.isEmpty()) {
            return false;
        }
        return true;
    }
}
