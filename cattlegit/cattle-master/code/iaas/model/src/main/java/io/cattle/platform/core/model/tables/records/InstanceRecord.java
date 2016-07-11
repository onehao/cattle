/**
 * This class is generated by jOOQ
 */
package io.cattle.platform.core.model.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "instance", schema = "cattle")
public class InstanceRecord extends org.jooq.impl.UpdatableRecordImpl<io.cattle.platform.core.model.tables.records.InstanceRecord> implements io.cattle.platform.db.jooq.utils.TableRecordJaxb, io.cattle.platform.core.model.Instance {

	private static final long serialVersionUID = 1644336573;

	/**
	 * Setter for <code>cattle.instance.id</code>.
	 */
	@Override
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cattle.instance.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	@Override
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>cattle.instance.name</code>.
	 */
	@Override
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cattle.instance.name</code>.
	 */
	@javax.persistence.Column(name = "name", length = 255)
	@Override
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>cattle.instance.account_id</code>.
	 */
	@Override
	public void setAccountId(java.lang.Long value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cattle.instance.account_id</code>.
	 */
	@javax.persistence.Column(name = "account_id", precision = 19)
	@Override
	public java.lang.Long getAccountId() {
		return (java.lang.Long) getValue(2);
	}

	/**
	 * Setter for <code>cattle.instance.kind</code>.
	 */
	@Override
	public void setKind(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cattle.instance.kind</code>.
	 */
	@javax.persistence.Column(name = "kind", nullable = false, length = 255)
	@Override
	public java.lang.String getKind() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>cattle.instance.uuid</code>.
	 */
	@Override
	public void setUuid(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cattle.instance.uuid</code>.
	 */
	@javax.persistence.Column(name = "uuid", unique = true, nullable = false, length = 128)
	@Override
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>cattle.instance.description</code>.
	 */
	@Override
	public void setDescription(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cattle.instance.description</code>.
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	@Override
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>cattle.instance.state</code>.
	 */
	@Override
	public void setState(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cattle.instance.state</code>.
	 */
	@javax.persistence.Column(name = "state", nullable = false, length = 128)
	@Override
	public java.lang.String getState() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>cattle.instance.created</code>.
	 */
	@Override
	public void setCreated(java.util.Date value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cattle.instance.created</code>.
	 */
	@javax.persistence.Column(name = "created")
	@Override
	public java.util.Date getCreated() {
		return (java.util.Date) getValue(7);
	}

	/**
	 * Setter for <code>cattle.instance.removed</code>.
	 */
	@Override
	public void setRemoved(java.util.Date value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cattle.instance.removed</code>.
	 */
	@javax.persistence.Column(name = "removed")
	@Override
	public java.util.Date getRemoved() {
		return (java.util.Date) getValue(8);
	}

	/**
	 * Setter for <code>cattle.instance.remove_time</code>.
	 */
	@Override
	public void setRemoveTime(java.util.Date value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cattle.instance.remove_time</code>.
	 */
	@javax.persistence.Column(name = "remove_time")
	@Override
	public java.util.Date getRemoveTime() {
		return (java.util.Date) getValue(9);
	}

	/**
	 * Setter for <code>cattle.instance.data</code>.
	 */
	@Override
	public void setData(java.util.Map<String,Object> value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cattle.instance.data</code>.
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	@Override
	public java.util.Map<String,Object> getData() {
		return (java.util.Map<String,Object>) getValue(10);
	}

	/**
	 * Setter for <code>cattle.instance.allocation_state</code>.
	 */
	@Override
	public void setAllocationState(java.lang.String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cattle.instance.allocation_state</code>.
	 */
	@javax.persistence.Column(name = "allocation_state", length = 255)
	@Override
	public java.lang.String getAllocationState() {
		return (java.lang.String) getValue(11);
	}

	/**
	 * Setter for <code>cattle.instance.compute</code>.
	 */
	@Override
	public void setCompute(java.lang.Long value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cattle.instance.compute</code>.
	 */
	@javax.persistence.Column(name = "compute", precision = 19)
	@Override
	public java.lang.Long getCompute() {
		return (java.lang.Long) getValue(12);
	}

	/**
	 * Setter for <code>cattle.instance.memory_mb</code>.
	 */
	@Override
	public void setMemoryMb(java.lang.Long value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>cattle.instance.memory_mb</code>.
	 */
	@javax.persistence.Column(name = "memory_mb", precision = 19)
	@Override
	public java.lang.Long getMemoryMb() {
		return (java.lang.Long) getValue(13);
	}

	/**
	 * Setter for <code>cattle.instance.image_id</code>.
	 */
	@Override
	public void setImageId(java.lang.Long value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>cattle.instance.image_id</code>.
	 */
	@javax.persistence.Column(name = "image_id", precision = 19)
	@Override
	public java.lang.Long getImageId() {
		return (java.lang.Long) getValue(14);
	}

	/**
	 * Setter for <code>cattle.instance.offering_id</code>.
	 */
	@Override
	public void setOfferingId(java.lang.Long value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>cattle.instance.offering_id</code>.
	 */
	@javax.persistence.Column(name = "offering_id", precision = 19)
	@Override
	public java.lang.Long getOfferingId() {
		return (java.lang.Long) getValue(15);
	}

	/**
	 * Setter for <code>cattle.instance.hostname</code>.
	 */
	@Override
	public void setHostname(java.lang.String value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>cattle.instance.hostname</code>.
	 */
	@javax.persistence.Column(name = "hostname", length = 255)
	@Override
	public java.lang.String getHostname() {
		return (java.lang.String) getValue(16);
	}

	/**
	 * Setter for <code>cattle.instance.zone_id</code>.
	 */
	@Override
	public void setZoneId(java.lang.Long value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>cattle.instance.zone_id</code>.
	 */
	@javax.persistence.Column(name = "zone_id", precision = 19)
	@Override
	public java.lang.Long getZoneId() {
		return (java.lang.Long) getValue(17);
	}

	/**
	 * Setter for <code>cattle.instance.instance_triggered_stop</code>.
	 */
	@Override
	public void setInstanceTriggeredStop(java.lang.String value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>cattle.instance.instance_triggered_stop</code>.
	 */
	@javax.persistence.Column(name = "instance_triggered_stop", length = 128)
	@Override
	public java.lang.String getInstanceTriggeredStop() {
		return (java.lang.String) getValue(18);
	}

	/**
	 * Setter for <code>cattle.instance.agent_id</code>.
	 */
	@Override
	public void setAgentId(java.lang.Long value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>cattle.instance.agent_id</code>.
	 */
	@javax.persistence.Column(name = "agent_id", precision = 19)
	@Override
	public java.lang.Long getAgentId() {
		return (java.lang.Long) getValue(19);
	}

	/**
	 * Setter for <code>cattle.instance.domain</code>.
	 */
	@Override
	public void setDomain(java.lang.String value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>cattle.instance.domain</code>.
	 */
	@javax.persistence.Column(name = "domain", length = 128)
	@Override
	public java.lang.String getDomain() {
		return (java.lang.String) getValue(20);
	}

	/**
	 * Setter for <code>cattle.instance.first_running</code>.
	 */
	@Override
	public void setFirstRunning(java.util.Date value) {
		setValue(21, value);
	}

	/**
	 * Getter for <code>cattle.instance.first_running</code>.
	 */
	@javax.persistence.Column(name = "first_running")
	@Override
	public java.util.Date getFirstRunning() {
		return (java.util.Date) getValue(21);
	}

	/**
	 * Setter for <code>cattle.instance.token</code>.
	 */
	@Override
	public void setToken(java.lang.String value) {
		setValue(22, value);
	}

	/**
	 * Getter for <code>cattle.instance.token</code>.
	 */
	@javax.persistence.Column(name = "token", length = 255)
	@Override
	public java.lang.String getToken() {
		return (java.lang.String) getValue(22);
	}

	/**
	 * Setter for <code>cattle.instance.userdata</code>.
	 */
	@Override
	public void setUserdata(java.lang.String value) {
		setValue(23, value);
	}

	/**
	 * Getter for <code>cattle.instance.userdata</code>.
	 */
	@javax.persistence.Column(name = "userdata", length = 65535)
	@Override
	public java.lang.String getUserdata() {
		return (java.lang.String) getValue(23);
	}

	/**
	 * Setter for <code>cattle.instance.system_container</code>.
	 */
	@Override
	public void setSystemContainer(java.lang.String value) {
		setValue(24, value);
	}

	/**
	 * Getter for <code>cattle.instance.system_container</code>.
	 */
	@javax.persistence.Column(name = "system_container", length = 128)
	@Override
	public java.lang.String getSystemContainer() {
		return (java.lang.String) getValue(24);
	}

	/**
	 * Setter for <code>cattle.instance.registry_credential_id</code>.
	 */
	@Override
	public void setRegistryCredentialId(java.lang.Long value) {
		setValue(25, value);
	}

	/**
	 * Getter for <code>cattle.instance.registry_credential_id</code>.
	 */
	@javax.persistence.Column(name = "registry_credential_id", precision = 19)
	@Override
	public java.lang.Long getRegistryCredentialId() {
		return (java.lang.Long) getValue(25);
	}

	/**
	 * Setter for <code>cattle.instance.external_id</code>.
	 */
	@Override
	public void setExternalId(java.lang.String value) {
		setValue(26, value);
	}

	/**
	 * Getter for <code>cattle.instance.external_id</code>.
	 */
	@javax.persistence.Column(name = "external_id", length = 128)
	@Override
	public java.lang.String getExternalId() {
		return (java.lang.String) getValue(26);
	}

	/**
	 * Setter for <code>cattle.instance.native_container</code>.
	 */
	@Override
	public void setNativeContainer(java.lang.Boolean value) {
		setValue(27, value);
	}

	/**
	 * Getter for <code>cattle.instance.native_container</code>.
	 */
	@javax.persistence.Column(name = "native_container", nullable = false, precision = 1)
	@Override
	public java.lang.Boolean getNativeContainer() {
		return (java.lang.Boolean) getValue(27);
	}

	/**
	 * Setter for <code>cattle.instance.network_container_id</code>.
	 */
	@Override
	public void setNetworkContainerId(java.lang.Long value) {
		setValue(28, value);
	}

	/**
	 * Getter for <code>cattle.instance.network_container_id</code>.
	 */
	@javax.persistence.Column(name = "network_container_id", precision = 19)
	@Override
	public java.lang.Long getNetworkContainerId() {
		return (java.lang.Long) getValue(28);
	}

	/**
	 * Setter for <code>cattle.instance.health_state</code>.
	 */
	@Override
	public void setHealthState(java.lang.String value) {
		setValue(29, value);
	}

	/**
	 * Getter for <code>cattle.instance.health_state</code>.
	 */
	@javax.persistence.Column(name = "health_state", length = 128)
	@Override
	public java.lang.String getHealthState() {
		return (java.lang.String) getValue(29);
	}

	/**
	 * Setter for <code>cattle.instance.start_count</code>.
	 */
	@Override
	public void setStartCount(java.lang.Long value) {
		setValue(30, value);
	}

	/**
	 * Getter for <code>cattle.instance.start_count</code>.
	 */
	@javax.persistence.Column(name = "start_count", precision = 19)
	@Override
	public java.lang.Long getStartCount() {
		return (java.lang.Long) getValue(30);
	}

	/**
	 * Setter for <code>cattle.instance.create_index</code>.
	 */
	@Override
	public void setCreateIndex(java.lang.Long value) {
		setValue(31, value);
	}

	/**
	 * Getter for <code>cattle.instance.create_index</code>.
	 */
	@javax.persistence.Column(name = "create_index", precision = 19)
	@Override
	public java.lang.Long getCreateIndex() {
		return (java.lang.Long) getValue(31);
	}

	/**
	 * Setter for <code>cattle.instance.deployment_unit_uuid</code>.
	 */
	@Override
	public void setDeploymentUnitUuid(java.lang.String value) {
		setValue(32, value);
	}

	/**
	 * Getter for <code>cattle.instance.deployment_unit_uuid</code>.
	 */
	@javax.persistence.Column(name = "deployment_unit_uuid", length = 128)
	@Override
	public java.lang.String getDeploymentUnitUuid() {
		return (java.lang.String) getValue(32);
	}

	/**
	 * Setter for <code>cattle.instance.version</code>.
	 */
	@Override
	public void setVersion(java.lang.String value) {
		setValue(33, value);
	}

	/**
	 * Getter for <code>cattle.instance.version</code>.
	 */
	@javax.persistence.Column(name = "version", length = 255)
	@Override
	public java.lang.String getVersion() {
		return (java.lang.String) getValue(33);
	}

	/**
	 * Setter for <code>cattle.instance.health_updated</code>.
	 */
	@Override
	public void setHealthUpdated(java.util.Date value) {
		setValue(34, value);
	}

	/**
	 * Getter for <code>cattle.instance.health_updated</code>.
	 */
	@javax.persistence.Column(name = "health_updated")
	@Override
	public java.util.Date getHealthUpdated() {
		return (java.util.Date) getValue(34);
	}

	/**
	 * Setter for <code>cattle.instance.service_index_id</code>.
	 */
	@Override
	public void setServiceIndexId(java.lang.Long value) {
		setValue(35, value);
	}

	/**
	 * Getter for <code>cattle.instance.service_index_id</code>.
	 */
	@javax.persistence.Column(name = "service_index_id", precision = 19)
	@Override
	public java.lang.Long getServiceIndexId() {
		return (java.lang.Long) getValue(35);
	}

	/**
	 * Setter for <code>cattle.instance.dns_internal</code>.
	 */
	@Override
	public void setDnsInternal(java.lang.String value) {
		setValue(36, value);
	}

	/**
	 * Getter for <code>cattle.instance.dns_internal</code>.
	 */
	@javax.persistence.Column(name = "dns_internal", length = 255)
	@Override
	public java.lang.String getDnsInternal() {
		return (java.lang.String) getValue(36);
	}

	/**
	 * Setter for <code>cattle.instance.dns_search_internal</code>.
	 */
	@Override
	public void setDnsSearchInternal(java.lang.String value) {
		setValue(37, value);
	}

	/**
	 * Getter for <code>cattle.instance.dns_search_internal</code>.
	 */
	@javax.persistence.Column(name = "dns_search_internal", length = 255)
	@Override
	public java.lang.String getDnsSearchInternal() {
		return (java.lang.String) getValue(37);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Long> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(io.cattle.platform.core.model.Instance from) {
		setId(from.getId());
		setName(from.getName());
		setAccountId(from.getAccountId());
		setKind(from.getKind());
		setUuid(from.getUuid());
		setDescription(from.getDescription());
		setState(from.getState());
		setCreated(from.getCreated());
		setRemoved(from.getRemoved());
		setRemoveTime(from.getRemoveTime());
		setData(from.getData());
		setAllocationState(from.getAllocationState());
		setCompute(from.getCompute());
		setMemoryMb(from.getMemoryMb());
		setImageId(from.getImageId());
		setOfferingId(from.getOfferingId());
		setHostname(from.getHostname());
		setZoneId(from.getZoneId());
		setInstanceTriggeredStop(from.getInstanceTriggeredStop());
		setAgentId(from.getAgentId());
		setDomain(from.getDomain());
		setFirstRunning(from.getFirstRunning());
		setToken(from.getToken());
		setUserdata(from.getUserdata());
		setSystemContainer(from.getSystemContainer());
		setRegistryCredentialId(from.getRegistryCredentialId());
		setExternalId(from.getExternalId());
		setNativeContainer(from.getNativeContainer());
		setNetworkContainerId(from.getNetworkContainerId());
		setHealthState(from.getHealthState());
		setStartCount(from.getStartCount());
		setCreateIndex(from.getCreateIndex());
		setDeploymentUnitUuid(from.getDeploymentUnitUuid());
		setVersion(from.getVersion());
		setHealthUpdated(from.getHealthUpdated());
		setServiceIndexId(from.getServiceIndexId());
		setDnsInternal(from.getDnsInternal());
		setDnsSearchInternal(from.getDnsSearchInternal());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends io.cattle.platform.core.model.Instance> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached InstanceRecord
	 */
	public InstanceRecord() {
		super(io.cattle.platform.core.model.tables.InstanceTable.INSTANCE);
	}

	/**
	 * Create a detached, initialised InstanceRecord
	 */
	public InstanceRecord(java.lang.Long id, java.lang.String name, java.lang.Long accountId, java.lang.String kind, java.lang.String uuid, java.lang.String description, java.lang.String state, java.util.Date created, java.util.Date removed, java.util.Date removeTime, java.util.Map<String,Object> data, java.lang.String allocationState, java.lang.Long compute, java.lang.Long memoryMb, java.lang.Long imageId, java.lang.Long offeringId, java.lang.String hostname, java.lang.Long zoneId, java.lang.String instanceTriggeredStop, java.lang.Long agentId, java.lang.String domain, java.util.Date firstRunning, java.lang.String token, java.lang.String userdata, java.lang.String systemContainer, java.lang.Long registryCredentialId, java.lang.String externalId, java.lang.Boolean nativeContainer, java.lang.Long networkContainerId, java.lang.String healthState, java.lang.Long startCount, java.lang.Long createIndex, java.lang.String deploymentUnitUuid, java.lang.String version, java.util.Date healthUpdated, java.lang.Long serviceIndexId, java.lang.String dnsInternal, java.lang.String dnsSearchInternal) {
		super(io.cattle.platform.core.model.tables.InstanceTable.INSTANCE);

		setValue(0, id);
		setValue(1, name);
		setValue(2, accountId);
		setValue(3, kind);
		setValue(4, uuid);
		setValue(5, description);
		setValue(6, state);
		setValue(7, created);
		setValue(8, removed);
		setValue(9, removeTime);
		setValue(10, data);
		setValue(11, allocationState);
		setValue(12, compute);
		setValue(13, memoryMb);
		setValue(14, imageId);
		setValue(15, offeringId);
		setValue(16, hostname);
		setValue(17, zoneId);
		setValue(18, instanceTriggeredStop);
		setValue(19, agentId);
		setValue(20, domain);
		setValue(21, firstRunning);
		setValue(22, token);
		setValue(23, userdata);
		setValue(24, systemContainer);
		setValue(25, registryCredentialId);
		setValue(26, externalId);
		setValue(27, nativeContainer);
		setValue(28, networkContainerId);
		setValue(29, healthState);
		setValue(30, startCount);
		setValue(31, createIndex);
		setValue(32, deploymentUnitUuid);
		setValue(33, version);
		setValue(34, healthUpdated);
		setValue(35, serviceIndexId);
		setValue(36, dnsInternal);
		setValue(37, dnsSearchInternal);
	}
}