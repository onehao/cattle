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
public class HostIpAddressMapTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord> {

	private static final long serialVersionUID = -1401989773;

	/**
	 * The singleton instance of <code>cattle.host_ip_address_map</code>
	 */
	public static final io.cattle.platform.core.model.tables.HostIpAddressMapTable HOST_IP_ADDRESS_MAP = new io.cattle.platform.core.model.tables.HostIpAddressMapTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord.class;
	}

	/**
	 * The column <code>cattle.host_ip_address_map.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(16777215).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.host_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.Long> HOST_ID = createField("host_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.host_ip_address_map.ip_address_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.Long> IP_ADDRESS_ID = createField("ip_address_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * Create a <code>cattle.host_ip_address_map</code> table reference
	 */
	public HostIpAddressMapTable() {
		this("host_ip_address_map", null);
	}

	/**
	 * Create an aliased <code>cattle.host_ip_address_map</code> table reference
	 */
	public HostIpAddressMapTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.HostIpAddressMapTable.HOST_IP_ADDRESS_MAP);
	}

	private HostIpAddressMapTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord> aliased) {
		this(alias, aliased, null);
	}

	private HostIpAddressMapTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_HOST_IP_ADDRESS_MAP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_HOST_IP_ADDRESS_MAP_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord>>asList(io.cattle.platform.core.model.Keys.KEY_HOST_IP_ADDRESS_MAP_PRIMARY, io.cattle.platform.core.model.Keys.KEY_HOST_IP_ADDRESS_MAP_IDX_HOST_IP_ADDRESS_MAP_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.HostIpAddressMapRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_HOST_IP_ADDRESS_MAP__HOST_ID, io.cattle.platform.core.model.Keys.FK_HOST_IP_ADDRESS_MAP__IP_ADDRESS_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.HostIpAddressMapTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.HostIpAddressMapTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.HostIpAddressMapTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.HostIpAddressMapTable(name, null);
	}
}
