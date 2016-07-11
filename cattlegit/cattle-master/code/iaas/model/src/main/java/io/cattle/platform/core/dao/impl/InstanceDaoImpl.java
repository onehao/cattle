package io.cattle.platform.core.dao.impl;

import static io.cattle.platform.core.model.tables.EnvironmentTable.*;
import static io.cattle.platform.core.model.tables.HostTable.*;
import static io.cattle.platform.core.model.tables.InstanceHostMapTable.*;
import static io.cattle.platform.core.model.tables.InstanceTable.*;
import static io.cattle.platform.core.model.tables.ServiceExposeMapTable.*;
import static io.cattle.platform.core.model.tables.ServiceTable.*;

import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.dao.GenericMapDao;
import io.cattle.platform.core.dao.InstanceDao;
import io.cattle.platform.core.model.Account;
import io.cattle.platform.core.model.Host;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.Service;
import io.cattle.platform.core.model.tables.records.HostRecord;
import io.cattle.platform.core.model.tables.records.InstanceRecord;
import io.cattle.platform.core.model.tables.records.ServiceRecord;
import io.cattle.platform.db.jooq.dao.impl.AbstractJooqDao;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.util.DataUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class InstanceDaoImpl extends AbstractJooqDao implements InstanceDao {
    @Inject
    GenericMapDao mapDao;
    @Inject
    ObjectManager objectManager;

    LoadingCache<Long, Map<String, Object>> instanceData = CacheBuilder.newBuilder()
            .expireAfterAccess(24, TimeUnit.HOURS)
            .build(new CacheLoader<Long, Map<String, Object>>() {
                @Override
                public Map<String, Object> load(Long key) throws Exception {
                    return lookupCacheInstanceData(key);
                }
            });

    @Override
    public List<? extends Instance> getNonRemovedInstanceOn(Long hostId) {
        return create()
                .select(INSTANCE.fields())
                .from(INSTANCE)
                .join(INSTANCE_HOST_MAP)
                    .on(INSTANCE_HOST_MAP.HOST_ID.eq(hostId)
                            .and(INSTANCE_HOST_MAP.INSTANCE_ID.eq(INSTANCE.ID)))
                .where(INSTANCE.REMOVED.isNull().and(
                        INSTANCE.STATE.notIn(InstanceConstants.STATE_ERROR, InstanceConstants.STATE_ERRORING,
                                CommonStatesConstants.REMOVING)))
                .fetchInto(InstanceRecord.class);
    }

    @Override
    public Instance getInstanceByUuidOrExternalId(Long accountId, String uuid, String externalId) {
        Instance instance = null;
        Condition condition = INSTANCE.ACCOUNT_ID.eq(accountId).and(INSTANCE.STATE.notIn(CommonStatesConstants.PURGED,
                CommonStatesConstants.PURGING));

        if(StringUtils.isNotEmpty(uuid)) {
            instance = create()
                    .selectFrom(INSTANCE)
                    .where(condition
                    .and(INSTANCE.UUID.eq(uuid)))
                    .fetchAny();
        }

        if (instance == null && StringUtils.isNotEmpty(externalId)) {
            instance = create()
                    .selectFrom(INSTANCE)
                    .where(condition
                    .and(INSTANCE.EXTERNAL_ID.eq(externalId)))
                    .fetchAny();
        }

        return instance;
    }

    @Override
    public List<? extends Service> findServicesFor(Instance instance) {
        return create().select(SERVICE.fields())
                .from(SERVICE)
                .join(SERVICE_EXPOSE_MAP)
                .on(SERVICE_EXPOSE_MAP.SERVICE_ID.eq(SERVICE.ID))
                .where(SERVICE_EXPOSE_MAP.INSTANCE_ID.eq(instance.getId()))
                .fetchInto(ServiceRecord.class);
    }

    @Override
    public List<? extends Instance> listNonRemovedInstances(Account account, boolean forService) {
        List<? extends Instance> serviceInstances = create().select(INSTANCE.fields())
                    .from(INSTANCE)
                    .join(SERVICE_EXPOSE_MAP)
                    .on(SERVICE_EXPOSE_MAP.INSTANCE_ID.eq(INSTANCE.ID))
                .where(INSTANCE.ACCOUNT_ID.eq(account.getId()))
                .and(INSTANCE.REMOVED.isNull())
                    .fetchInto(InstanceRecord.class);
        if (forService) {
            return serviceInstances;
        }
        List<? extends Instance> allInstances = create().select(INSTANCE.fields())
                .from(INSTANCE)
                .where(INSTANCE.ACCOUNT_ID.eq(account.getId()))
                .and(INSTANCE.REMOVED.isNull())
                .fetchInto(InstanceRecord.class);

        allInstances.removeAll(serviceInstances);
        return allInstances;
    }

    @Override
    public List<? extends Instance> findInstancesFor(Service service) {
        return create()
                .select(INSTANCE.fields())
                .from(INSTANCE)
                .join(SERVICE_EXPOSE_MAP)
                .on(SERVICE_EXPOSE_MAP.INSTANCE_ID.eq(INSTANCE.ID)
                        .and(SERVICE_EXPOSE_MAP.SERVICE_ID.eq(service.getId()))
                        .and(SERVICE_EXPOSE_MAP.STATE.in(CommonStatesConstants.ACTIVATING,
                                CommonStatesConstants.ACTIVE, CommonStatesConstants.REQUESTED))
                        .and(INSTANCE.STATE.notIn(CommonStatesConstants.PURGING, CommonStatesConstants.PURGED,
                                CommonStatesConstants.REMOVED, CommonStatesConstants.REMOVING)))
                .fetchInto(InstanceRecord.class);
    }

    @Override
    public List<? extends Instance> findInstanceByServiceName(long accountId, String serviceName) {
        return create().select(INSTANCE.fields())
            .from(INSTANCE)
            .join(SERVICE_EXPOSE_MAP)
                .on(INSTANCE.ID.eq(SERVICE_EXPOSE_MAP.INSTANCE_ID))
            .join(SERVICE)
                .on(SERVICE.ID.eq(SERVICE_EXPOSE_MAP.SERVICE_ID))
            .where(INSTANCE.STATE.eq(InstanceConstants.STATE_RUNNING)
                    .and(INSTANCE.ACCOUNT_ID.eq(accountId))
                    .and(SERVICE_EXPOSE_MAP.REMOVED.isNull())
                    .and(SERVICE.REMOVED.isNull())
                    .and(SERVICE.NAME.eq(serviceName)))
            .fetchInto(InstanceRecord.class);
    }

    @Override
    public List<? extends Instance> findInstanceByServiceName(long accountId, String serviceName, String environmentName) {
        return create().select(INSTANCE.fields())
            .from(INSTANCE)
            .join(SERVICE_EXPOSE_MAP)
                .on(INSTANCE.ID.eq(SERVICE_EXPOSE_MAP.INSTANCE_ID))
            .join(SERVICE)
                .on(SERVICE.ID.eq(SERVICE_EXPOSE_MAP.SERVICE_ID))
            .join(ENVIRONMENT)
                .on(ENVIRONMENT.ID.eq(SERVICE.ENVIRONMENT_ID))
            .where(INSTANCE.STATE.eq(InstanceConstants.STATE_RUNNING)
                    .and(INSTANCE.ACCOUNT_ID.eq(accountId))
                    .and(SERVICE_EXPOSE_MAP.REMOVED.isNull())
                    .and(SERVICE.REMOVED.isNull())
                    .and(SERVICE.NAME.eq(serviceName))
                    .and(ENVIRONMENT.NAME.eq(environmentName)))
            .fetchInto(InstanceRecord.class);
    }

    @Override
    public List<? extends Host> findHosts(long accountId, long instanceId) {
        return create().select(HOST.fields())
                .from(INSTANCE)
                .join(INSTANCE_HOST_MAP)
                    .on(INSTANCE.ID.eq(INSTANCE_HOST_MAP.INSTANCE_ID))
                .join(HOST)
                    .on(HOST.ID.eq(INSTANCE_HOST_MAP.HOST_ID))
                .where(HOST.REMOVED.isNull()
                    .and(INSTANCE_HOST_MAP.REMOVED.isNull())
                    .and(INSTANCE.ID.eq(instanceId)))
                .fetchInto(HostRecord.class);
    }

    protected Map<String, Object> lookupCacheInstanceData(long instanceId) {
        Instance instance = objectManager.loadResource(Instance.class, instanceId);
        if (instance == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> newData = new HashMap<>();
        newData.put(DataUtils.FIELDS, instance.getData().get(DataUtils.FIELDS));
        return newData;
    }

    @Override
    public Map<String, Object> getCacheInstanceData(long instanceId) {
        return instanceData.getUnchecked(instanceId);
    }

    @Override
    public void clearCacheInstanceData(long instanceId) {
        instanceData.invalidate(instanceId);
    }

}
