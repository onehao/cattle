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
public class ServiceTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.ServiceRecord> {

	private static final long serialVersionUID = 814887565;

	/**
	 * The singleton instance of <code>cattle.service</code>
	 */
	public static final io.cattle.platform.core.model.tables.ServiceTable SERVICE = new io.cattle.platform.core.model.tables.ServiceTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.ServiceRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.ServiceRecord.class;
	}

	/**
	 * The column <code>cattle.service.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.service.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.service.account_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.Long> ACCOUNT_ID = createField("account_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.service.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.service.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.service.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.service.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.service.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.service.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.service.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.service.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(16777215).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.service.environment_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.Long> ENVIRONMENT_ID = createField("environment_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.service.vip</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> VIP = createField("vip", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.service.create_index</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.Long> CREATE_INDEX = createField("create_index", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.service.selector_link</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> SELECTOR_LINK = createField("selector_link", org.jooq.impl.SQLDataType.VARCHAR.length(4096), this, "");

	/**
	 * The column <code>cattle.service.selector_container</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> SELECTOR_CONTAINER = createField("selector_container", org.jooq.impl.SQLDataType.VARCHAR.length(4096), this, "");

	/**
	 * The column <code>cattle.service.external_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> EXTERNAL_ID = createField("external_id", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.service.health_state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.String> HEALTH_STATE = createField("health_state", org.jooq.impl.SQLDataType.VARCHAR.length(128), this, "");

	/**
	 * Create a <code>cattle.service</code> table reference
	 */
	public ServiceTable() {
		this("service", null);
	}

	/**
	 * Create an aliased <code>cattle.service</code> table reference
	 */
	public ServiceTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.ServiceTable.SERVICE);
	}

	private ServiceTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.ServiceRecord> aliased) {
		this(alias, aliased, null);
	}

	private ServiceTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.ServiceRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.ServiceRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_SERVICE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.ServiceRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_SERVICE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.ServiceRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.ServiceRecord>>asList(io.cattle.platform.core.model.Keys.KEY_SERVICE_PRIMARY, io.cattle.platform.core.model.Keys.KEY_SERVICE_IDX_SERVICE_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.ServiceRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.ServiceRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_SERVICE__ACCOUNT_ID, io.cattle.platform.core.model.Keys.FK_SERVICE__ENVIRONMENT_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.ServiceTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.ServiceTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.ServiceTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.ServiceTable(name, null);
	}
}