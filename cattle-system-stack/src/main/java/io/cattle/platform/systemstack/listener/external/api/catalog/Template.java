package io.cattle.platform.systemstack.listener.external.api.catalog;

import java.util.Map;

public class Template {

    String catalogId;

    String category;

    String defaultVersion;

    String description;

    String id;

    String license;

    String maintainer;

    String minimumRancherVersion;

    String name;

    String path;

    String type;

    Map<String, String> versionLinks;

    Map<String, String> files;

    String uuid;

    String version;

    public String getCatalogId() {
    return catalogId;
    }

    public void setCatalogId(String catalogId) {
    this.catalogId = catalogId;
    }

    public String getCategory() {
    return category;
    }

    public void setCategory(String category) {
    this.category = category;
    }

    public String getDefaultVersion() {
    return defaultVersion;
    }

    public void setDefaultVersion(String defaultVersion) {
    this.defaultVersion = defaultVersion;
    }

    public String getDescription() {
    return description;
    }

    public void setDescription(String description) {
    this.description = description;
    }

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getLicense() {
    return license;
    }

    public void setLicense(String license) {
    this.license = license;
    }

    public String getMaintainer() {
    return maintainer;
    }

    public void setMaintainer(String maintainer) {
    this.maintainer = maintainer;
    }

    public String getMinimumRancherVersion() {
    return minimumRancherVersion;
    }

    public void setMinimumRancherVersion(String minimumRancherVersion) {
    this.minimumRancherVersion = minimumRancherVersion;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getPath() {
    return path;
    }

    public void setPath(String path) {
    this.path = path;
    }

    public String getType() {
    return type;
    }

    public void setType(String type) {
    this.type = type;
    }
    
    public Map<String, String> getVersionLinks() {
    return versionLinks;
    }

    public void setVersionLinks(Map<String, String> versionLinks) {
    this.versionLinks = versionLinks;
    }
    
    public String getDockerCompose() {
        return this.getFiles().get("docker-compose.yml");
    }

    public String getRancherCompose() {
        return this.getFiles().get("rancher-compose.yml");
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }

    public String getUuid() {
    return uuid;
    }

    public void setUuid(String uuid) {
    this.uuid = uuid;
    }

    public String getVersion() {
    return version;
    }

    public void setVersion(String version) {
    this.version = version;
    }

}
