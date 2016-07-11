/**
 * This class is generated by jOOQ
 */
package io.cattle.platform.core.model.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IpAddressNicMapTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord> {

	private static final long serialVersionUID = -2075504335;

	/**
	 * The singleton instance of <code>cattle.ip_address_nic_map</code>
	 */
	public static final io.cattle.platform.core.model.tables.IpAddressNicMapTable IP_ADDRESS_NIC_MAP = new io.cattle.platform.core.model.tables.IpAddressNicMapTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord.class;
	}

	/**
	 * The column <code>cattle.ip_address_nic_map.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(16777215).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.ip_address_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.Long> IP_ADDRESS_ID = createField("ip_address_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.ip_address_nic_map.nic_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.Long> NIC_ID = createField("nic_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * Create a <code>cattle.ip_address_nic_map</code> table reference
	 */
	public IpAddressNicMapTable() {
		this("ip_address_nic_map", null);
	}

	/**
	 * Create an aliased <code>cattle.ip_address_nic_map</code> table reference
	 */
	public IpAddressNicMapTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.IpAddressNicMapTable.IP_ADDRESS_NIC_MAP);
	}

	private IpAddressNicMapTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord> aliased) {
		this(alias, aliased, null);
	}

	private IpAddressNicMapTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_IP_ADDRESS_NIC_MAP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_IP_ADDRESS_NIC_MAP_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord>>asList(io.cattle.platform.core.model.Keys.KEY_IP_ADDRESS_NIC_MAP_PRIMARY, io.cattle.platform.core.model.Keys.KEY_IP_ADDRESS_NIC_MAP_IDX_IP_ADDRESS_NIC_MAP_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_IP_ADDRESS_NIC_MAP__IP_ADDRESS_ID, io.cattle.platform.core.model.Keys.FK_IP_ADDRESS_NIC_MAP__NIC_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.IpAddressNicMapTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.IpAddressNicMapTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.IpAddressNicMapTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.IpAddressNicMapTable(name, null);
	}
}
