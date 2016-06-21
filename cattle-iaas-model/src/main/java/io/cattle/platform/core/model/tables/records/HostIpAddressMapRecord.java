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
@javax.persistence.Table(name = "host_ip_address_map", schema = "cattle")
public class HostIpAddressMapRecord extends org.jooq.impl.UpdatableRecordImpl<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord> implements io.cattle.platform.db.jooq.utils.TableRecordJaxb, org.jooq.Record12<java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Map<String,Object>, java.lang.Long, java.lang.Long>, io.cattle.platform.core.model.HostIpAddressMap {

	private static final long serialVersionUID = 2094537998;

	/**
	 * Setter for <code>cattle.host_ip_address_map.id</code>.
	 */
	@Override
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	@Override
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.name</code>.
	 */
	@Override
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.name</code>.
	 */
	@javax.persistence.Column(name = "name", length = 255)
	@Override
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.kind</code>.
	 */
	@Override
	public void setKind(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.kind</code>.
	 */
	@javax.persistence.Column(name = "kind", nullable = false, length = 255)
	@Override
	public java.lang.String getKind() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.uuid</code>.
	 */
	@Override
	public void setUuid(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.uuid</code>.
	 */
	@javax.persistence.Column(name = "uuid", unique = true, nullable = false, length = 128)
	@Override
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.description</code>.
	 */
	@Override
	public void setDescription(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.description</code>.
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	@Override
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.state</code>.
	 */
	@Override
	public void setState(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.state</code>.
	 */
	@javax.persistence.Column(name = "state", nullable = false, length = 128)
	@Override
	public java.lang.String getState() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.created</code>.
	 */
	@Override
	public void setCreated(java.util.Date value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.created</code>.
	 */
	@javax.persistence.Column(name = "created")
	@Override
	public java.util.Date getCreated() {
		return (java.util.Date) getValue(6);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.removed</code>.
	 */
	@Override
	public void setRemoved(java.util.Date value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.removed</code>.
	 */
	@javax.persistence.Column(name = "removed")
	@Override
	public java.util.Date getRemoved() {
		return (java.util.Date) getValue(7);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.remove_time</code>.
	 */
	@Override
	public void setRemoveTime(java.util.Date value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.remove_time</code>.
	 */
	@javax.persistence.Column(name = "remove_time")
	@Override
	public java.util.Date getRemoveTime() {
		return (java.util.Date) getValue(8);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.data</code>.
	 */
	@Override
	public void setData(java.util.Map<String,Object> value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.data</code>.
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	@Override
	public java.util.Map<String,Object> getData() {
		return (java.util.Map<String,Object>) getValue(9);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.host_id</code>.
	 */
	@Override
	public void setHostId(java.lang.Long value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.host_id</code>.
	 */
	@javax.persistence.Column(name = "host_id", precision = 19)
	@Override
	public java.lang.Long getHostId() {
		return (java.lang.Long) getValue(10);
	}

	/**
	 * Setter for <code>cattle.host_ip_address_map.ip_address_id</code>.
	 */
	@Override
	public void setIpAddressId(java.lang.Long value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cattle.host_ip_address_map.ip_address_id</code>.
	 */
	@javax.persistence.Column(name = "ip_address_id", precision = 19)
	@Override
	public java.lang.Long getIpAddressId() {
		return (java.lang.Long) getValue(11);
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
	// Record12 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row12<java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Map<String,Object>, java.lang.Long, java.lang.Long> fieldsRow() {
		return (org.jooq.Row12) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row12<java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Map<String,Object>, java.lang.Long, java.lang.Long> valuesRow() {
		return (org.jooq.Row12) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.KIND;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.UUID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Date> field7() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Date> field8() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Date> field9() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.REMOVE_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.Map<String,Object>> field10() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field11() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.HOST_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field12() {
		return io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP.IP_ADDRESS_ID;
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
	public java.lang.String value3() {
		return getKind();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getUuid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date value7() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date value8() {
		return getRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date value9() {
		return getRemoveTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Map<String,Object> value10() {
		return getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value11() {
		return getHostId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value12() {
		return getIpAddressId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value1(java.lang.Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value2(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value3(java.lang.String value) {
		setKind(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value4(java.lang.String value) {
		setUuid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value5(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value6(java.lang.String value) {
		setState(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value7(java.util.Date value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value8(java.util.Date value) {
		setRemoved(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value9(java.util.Date value) {
		setRemoveTime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value10(java.util.Map<String,Object> value) {
		setData(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value11(java.lang.Long value) {
		setHostId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord value12(java.lang.Long value) {
		setIpAddressId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostIpAddressMapRecord values(java.lang.Long value1, java.lang.String value2, java.lang.String value3, java.lang.String value4, java.lang.String value5, java.lang.String value6, java.util.Date value7, java.util.Date value8, java.util.Date value9, java.util.Map<String,Object> value10, java.lang.Long value11, java.lang.Long value12) {
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(io.cattle.platform.core.model.HostIpAddressMap from) {
		setId(from.getId());
		setName(from.getName());
		setKind(from.getKind());
		setUuid(from.getUuid());
		setDescription(from.getDescription());
		setState(from.getState());
		setCreated(from.getCreated());
		setRemoved(from.getRemoved());
		setRemoveTime(from.getRemoveTime());
		setData(from.getData());
		setHostId(from.getHostId());
		setIpAddressId(from.getIpAddressId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends io.cattle.platform.core.model.HostIpAddressMap> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached HostIpAddressMapRecord
	 */
	public HostIpAddressMapRecord() {
		super(io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP);
	}

	/**
	 * Create a detached, initialised HostIpAddressMapRecord
	 */
	public HostIpAddressMapRecord(java.lang.Long id, java.lang.String name, java.lang.String kind, java.lang.String uuid, java.lang.String description, java.lang.String state, java.util.Date created, java.util.Date removed, java.util.Date removeTime, java.util.Map<String,Object> data, java.lang.Long hostId, java.lang.Long ipAddressId) {
		super(io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP);

		setValue(0, id);
		setValue(1, name);
		setValue(2, kind);
		setValue(3, uuid);
		setValue(4, description);
		setValue(5, state);
		setValue(6, created);
		setValue(7, removed);
		setValue(8, removeTime);
		setValue(9, data);
		setValue(10, hostId);
		setValue(11, ipAddressId);
	}
}
