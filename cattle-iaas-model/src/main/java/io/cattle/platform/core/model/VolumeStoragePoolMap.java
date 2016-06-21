/**
 * This class is generated by jOOQ
 */
package io.cattle.platform.core.model;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "volume_storage_pool_map", schema = "cattle")
public interface VolumeStoragePoolMap extends java.io.Serializable {

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.id</code>.
	 */
	public void setId(java.lang.Long value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	public java.lang.Long getId();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.name</code>.
	 */
	public void setName(java.lang.String value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.name</code>.
	 */
	@javax.persistence.Column(name = "name", length = 255)
	public java.lang.String getName();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.kind</code>.
	 */
	public void setKind(java.lang.String value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.kind</code>.
	 */
	@javax.persistence.Column(name = "kind", nullable = false, length = 255)
	public java.lang.String getKind();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.uuid</code>.
	 */
	public void setUuid(java.lang.String value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.uuid</code>.
	 */
	@javax.persistence.Column(name = "uuid", unique = true, nullable = false, length = 128)
	public java.lang.String getUuid();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.description</code>.
	 */
	public void setDescription(java.lang.String value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.description</code>.
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	public java.lang.String getDescription();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.state</code>.
	 */
	public void setState(java.lang.String value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.state</code>.
	 */
	@javax.persistence.Column(name = "state", nullable = false, length = 128)
	public java.lang.String getState();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.created</code>.
	 */
	public void setCreated(java.util.Date value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.created</code>.
	 */
	@javax.persistence.Column(name = "created")
	public java.util.Date getCreated();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.removed</code>.
	 */
	public void setRemoved(java.util.Date value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.removed</code>.
	 */
	@javax.persistence.Column(name = "removed")
	public java.util.Date getRemoved();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.remove_time</code>.
	 */
	public void setRemoveTime(java.util.Date value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.remove_time</code>.
	 */
	@javax.persistence.Column(name = "remove_time")
	public java.util.Date getRemoveTime();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.data</code>.
	 */
	public void setData(java.util.Map<String,Object> value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.data</code>.
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	public java.util.Map<String,Object> getData();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.volume_id</code>.
	 */
	public void setVolumeId(java.lang.Long value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.volume_id</code>.
	 */
	@javax.persistence.Column(name = "volume_id", precision = 19)
	public java.lang.Long getVolumeId();

	/**
	 * Setter for <code>cattle.volume_storage_pool_map.storage_pool_id</code>.
	 */
	public void setStoragePoolId(java.lang.Long value);

	/**
	 * Getter for <code>cattle.volume_storage_pool_map.storage_pool_id</code>.
	 */
	@javax.persistence.Column(name = "storage_pool_id", precision = 19)
	public java.lang.Long getStoragePoolId();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface VolumeStoragePoolMap
	 */
	public void from(io.cattle.platform.core.model.VolumeStoragePoolMap from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface VolumeStoragePoolMap
	 */
	public <E extends io.cattle.platform.core.model.VolumeStoragePoolMap> E into(E into);
}
