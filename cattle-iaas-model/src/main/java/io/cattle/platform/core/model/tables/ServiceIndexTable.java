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
public class ServiceIndexTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.ServiceIndexRecord> {

	private static final long serialVersionUID = -1181078724;

	/**
	 * The singleton instance of <code>cattle.service_index</code>
	 */
	public static final io.cattle.platform.core.model.tables.ServiceIndexTable SERVICE_INDEX = new io.cattle.platform.core.model.tables.ServiceIndexTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.ServiceIndexRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.ServiceIndexRecord.class;
	}

	/**
	 * The column <code>cattle.service_index.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.service_index.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.service_index.account_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.Long> ACCOUNT_ID = createField("account_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.service_index.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.service_index.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.service_index.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.service_index.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.service_index.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.service_index.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.service_index.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.service_index.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(65535).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.service_index.service_index</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> SERVICE_INDEX_ = createField("service_index", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.service_index.launch_config_name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> LAUNCH_CONFIG_NAME = createField("launch_config_name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.service_index.service_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.Long> SERVICE_ID = createField("service_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.service_index.address</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.String> ADDRESS = createField("address", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * Create a <code>cattle.service_index</code> table reference
	 */
	public ServiceIndexTable() {
		this("service_index", null);
	}

	/**
	 * Create an aliased <code>cattle.service_index</code> table reference
	 */
	public ServiceIndexTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.ServiceIndexTable.SERVICE_INDEX);
	}

	private ServiceIndexTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.ServiceIndexRecord> aliased) {
		this(alias, aliased, null);
	}

	private ServiceIndexTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.ServiceIndexRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_SERVICE_INDEX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.ServiceIndexRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_SERVICE_INDEX_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.ServiceIndexRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.ServiceIndexRecord>>asList(io.cattle.platform.core.model.Keys.KEY_SERVICE_INDEX_PRIMARY, io.cattle.platform.core.model.Keys.KEY_SERVICE_INDEX_IDX_SERVICE_SUFFIX_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.ServiceIndexRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_SERVICE_SUFFIX__ACCOUNT_ID, io.cattle.platform.core.model.Keys.FK_SERVICE_SUFFIX__SERVICE_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.ServiceIndexTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.ServiceIndexTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.ServiceIndexTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.ServiceIndexTable(name, null);
	}
}
