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
@javax.persistence.Table(name = "service_event", schema = "cattle")
public class ServiceEventRecord extends org.jooq.impl.UpdatableRecordImpl<io.cattle.platform.core.model.tables.records.ServiceEventRecord> implements io.cattle.platform.db.jooq.utils.TableRecordJaxb, org.jooq.Record17<java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Map<String,Object>, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Integer>, io.cattle.platform.core.model.ServiceEvent {

	private static final long serialVersionUID = -1241713138;

	/**
	 * Setter for <code>cattle.service_event.id</code>.
	 */
	@Override
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cattle.service_event.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	@Override
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>cattle.service_event.name</code>.
	 */
	@Override
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cattle.service_event.name</code>.
	 */
	@javax.persistence.Column(name = "name", length = 255)
	@Override
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>cattle.service_event.account_id</code>.
	 */
	@Override
	public void setAccountId(java.lang.Long value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cattle.service_event.account_id</code>.
	 */
	@javax.persistence.Column(name = "account_id", precision = 19)
	@Override
	public java.lang.Long getAccountId() {
		return (java.lang.Long) getValue(2);
	}

	/**
	 * Setter for <code>cattle.service_event.kind</code>.
	 */
	@Override
	public void setKind(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cattle.service_event.kind</code>.
	 */
	@javax.persistence.Column(name = "kind", nullable = false, length = 255)
	@Override
	public java.lang.String getKind() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>cattle.service_event.uuid</code>.
	 */
	@Override
	public void setUuid(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cattle.service_event.uuid</code>.
	 */
	@javax.persistence.Column(name = "uuid", unique = true, nullable = false, length = 128)
	@Override
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>cattle.service_event.description</code>.
	 */
	@Override
	public void setDescription(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cattle.service_event.description</code>.
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	@Override
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>cattle.service_event.state</code>.
	 */
	@Override
	public void setState(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cattle.service_event.state</code>.
	 */
	@javax.persistence.Column(name = "state", nullable = false, length = 128)
	@Override
	public java.lang.String getState() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>cattle.service_event.created</code>.
	 */
	@Override
	public void setCreated(java.util.Date value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cattle.service_event.created</code>.
	 */
	@javax.persistence.Column(name = "created")
	@Override
	public java.util.Date getCreated() {
		return (java.util.Date) getValue(7);
	}

	/**
	 * Setter for <code>cattle.service_event.removed</code>.
	 */
	@Override
	public void setRemoved(java.util.Date value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cattle.service_event.removed</code>.
	 */
	@javax.persistence.Column(name = "removed")
	@Override
	public java.util.Date getRemoved() {
		return (java.util.Date) getValue(8);
	}

	/**
	 * Setter for <code>cattle.service_event.remove_time</code>.
	 */
	@Override
	public void setRemoveTime(java.util.Date value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cattle.service_event.remove_time</code>.
	 */
	@javax.persistence.Column(name = "remove_time")
	@Override
	public java.util.Date getRemoveTime() {
		return (java.util.Date) getValue(9);
	}

	/**
	 * Setter for <code>cattle.service_event.data</code>.
	 */
	@Override
	public void setData(java.util.Map<String,Object> value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cattle.service_event.data</code>.
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	@Override
	public java.util.Map<String,Object> getData() {
		return (java.util.Map<String,Object>) getValue(10);
	}

	/**
	 * Setter for <code>cattle.service_event.host_id</code>.
	 */
	@Override
	public void setHostId(java.lang.Long value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cattle.service_event.host_id</code>.
	 */
	@javax.persistence.Column(name = "host_id", precision = 19)
	@Override
	public java.lang.Long getHostId() {
		return (java.lang.Long) getValue(11);
	}

	/**
	 * Setter for <code>cattle.service_event.healthcheck_uuid</code>.
	 */
	@Override
	public void setHealthcheckUuid(java.lang.String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cattle.service_event.healthcheck_uuid</code>.
	 */
	@javax.persistence.Column(name = "healthcheck_uuid", length = 255)
	@Override
	public java.lang.String getHealthcheckUuid() {
		return (java.lang.String) getValue(12);
	}

	/**
	 * Setter for <code>cattle.service_event.instance_id</code>.
	 */
	@Override
	public void setInstanceId(java.lang.Long value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>cattle.service_event.instance_id</code>.
	 */
	@javax.persistence.Column(name = "instance_id", precision = 19)
	@Override
	public java.lang.Long getInstanceId() {
		return (java.lang.Long) getValue(13);
	}

	/**
	 * Setter for <code>cattle.service_event.healthcheck_instance_id</code>.
	 */
	@Override
	public void setHealthcheckInstanceId(java.lang.Long value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>cattle.service_event.healthcheck_instance_id</code>.
	 */
	@javax.persistence.Column(name = "healthcheck_instance_id", precision = 19)
	@Override
	public java.lang.Long getHealthcheckInstanceId() {
		return (java.lang.Long) getValue(14);
	}

	/**
	 * Setter for <code>cattle.service_event.reported_health</code>.
	 */
	@Override
	public void setReportedHealth(java.lang.String value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>cattle.service_event.reported_health</code>.
	 */
	@javax.persistence.Column(name = "reported_health", length = 255)
	@Override
	public java.lang.String getReportedHealth() {
		return (java.lang.String) getValue(15);
	}

	/**
	 * Setter for <code>cattle.service_event.external_timestamp</code>.
	 */
	@Override
	public void setExternalTimestamp(java.lang.Integer value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>cattle.service_event.external_timestamp</code>.
	 */
	@javax.persistence.Column(name = "external_timestamp", precision = 10)
	@Override
	public java.lang.Integer getExternalTimestamp() {
		return (java.lang.Integer) getValue(16);
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
	// Record17 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row17<java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Map<String,Object>, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row17) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row17<java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Map<String,Object>, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Integer> valuesRow() {
		return (org.jooq.Row17) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field3() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.ACCOUNT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.KIND;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.UUID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Date> field8() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Date> field9() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Date> field10() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.REMOVE_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Map<String,Object>> field11() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field12() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.HOST_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field13() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.HEALTHCHECK_UUID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field14() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.INSTANCE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field15() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.HEALTHCHECK_INSTANCE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field16() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.REPORTED_HEALTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field17() {
		return io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT.EXTERNAL_TIMESTAMP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value3() {
		return getAccountId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getKind();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getUuid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date value8() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date value9() {
		return getRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date value10() {
		return getRemoveTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Map<String,Object> value11() {
		return getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value12() {
		return getHostId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value13() {
		return getHealthcheckUuid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value14() {
		return getInstanceId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value15() {
		return getHealthcheckInstanceId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value16() {
		return getReportedHealth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value17() {
		return getExternalTimestamp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value1(java.lang.Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value2(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value3(java.lang.Long value) {
		setAccountId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value4(java.lang.String value) {
		setKind(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value5(java.lang.String value) {
		setUuid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value6(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value7(java.lang.String value) {
		setState(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value8(java.util.Date value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value9(java.util.Date value) {
		setRemoved(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value10(java.util.Date value) {
		setRemoveTime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value11(java.util.Map<String,Object> value) {
		setData(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value12(java.lang.Long value) {
		setHostId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value13(java.lang.String value) {
		setHealthcheckUuid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value14(java.lang.Long value) {
		setInstanceId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value15(java.lang.Long value) {
		setHealthcheckInstanceId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value16(java.lang.String value) {
		setReportedHealth(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord value17(java.lang.Integer value) {
		setExternalTimestamp(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEventRecord values(java.lang.Long value1, java.lang.String value2, java.lang.Long value3, java.lang.String value4, java.lang.String value5, java.lang.String value6, java.lang.String value7, java.util.Date value8, java.util.Date value9, java.util.Date value10, java.util.Map<String,Object> value11, java.lang.Long value12, java.lang.String value13, java.lang.Long value14, java.lang.Long value15, java.lang.String value16, java.lang.Integer value17) {
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(io.cattle.platform.core.model.ServiceEvent from) {
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
		setHostId(from.getHostId());
		setHealthcheckUuid(from.getHealthcheckUuid());
		setInstanceId(from.getInstanceId());
		setHealthcheckInstanceId(from.getHealthcheckInstanceId());
		setReportedHealth(from.getReportedHealth());
		setExternalTimestamp(from.getExternalTimestamp());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends io.cattle.platform.core.model.ServiceEvent> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ServiceEventRecord
	 */
	public ServiceEventRecord() {
		super(io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT);
	}

	/**
	 * Create a detached, initialised ServiceEventRecord
	 */
	public ServiceEventRecord(java.lang.Long id, java.lang.String name, java.lang.Long accountId, java.lang.String kind, java.lang.String uuid, java.lang.String description, java.lang.String state, java.util.Date created, java.util.Date removed, java.util.Date removeTime, java.util.Map<String,Object> data, java.lang.Long hostId, java.lang.String healthcheckUuid, java.lang.Long instanceId, java.lang.Long healthcheckInstanceId, java.lang.String reportedHealth, java.lang.Integer externalTimestamp) {
		super(io.cattle.platform.core.model.tables.ServiceEventTable.SERVICE_EVENT);

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
		setValue(11, hostId);
		setValue(12, healthcheckUuid);
		setValue(13, instanceId);
		setValue(14, healthcheckInstanceId);
		setValue(15, reportedHealth);
		setValue(16, externalTimestamp);
	}
}
