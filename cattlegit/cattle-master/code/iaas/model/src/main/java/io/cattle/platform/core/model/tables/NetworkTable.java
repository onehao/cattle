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
public class NetworkTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.NetworkRecord> {

	private static final long serialVersionUID = -890109673;

	/**
	 * The singleton instance of <code>cattle.network</code>
	 */
	public static final io.cattle.platform.core.model.tables.NetworkTable NETWORK = new io.cattle.platform.core.model.tables.NetworkTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.NetworkRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.NetworkRecord.class;
	}

	/**
	 * The column <code>cattle.network.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.network.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.network.account_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.Long> ACCOUNT_ID = createField("account_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.network.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.network.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.network.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.network.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.network.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.network.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.network.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.network.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(16777215).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.network.is_public</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.Boolean> IS_PUBLIC = createField("is_public", org.jooq.impl.SQLDataType.BIT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cattle.network.domain</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.String> DOMAIN = createField("domain", org.jooq.impl.SQLDataType.VARCHAR.length(128), this, "");

	/**
	 * Create a <code>cattle.network</code> table reference
	 */
	public NetworkTable() {
		this("network", null);
	}

	/**
	 * Create an aliased <code>cattle.network</code> table reference
	 */
	public NetworkTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.NetworkTable.NETWORK);
	}

	private NetworkTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.NetworkRecord> aliased) {
		this(alias, aliased, null);
	}

	private NetworkTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.NetworkRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.NetworkRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_NETWORK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.NetworkRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_NETWORK_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.NetworkRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.NetworkRecord>>asList(io.cattle.platform.core.model.Keys.KEY_NETWORK_PRIMARY, io.cattle.platform.core.model.Keys.KEY_NETWORK_IDX_NETWORK_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.NetworkRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.NetworkRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_NETWORK__ACCOUNT_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.NetworkTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.NetworkTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.NetworkTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.NetworkTable(name, null);
	}
}
