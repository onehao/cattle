package io.cattle.platform.configitem.context.data;

import io.cattle.platform.core.addon.InstanceHealthCheck;
import io.cattle.platform.core.util.LoadBalancerTargetPortSpec;

public class LoadBalancerTargetInfo {
    private String uuid;
    private String name;
    private String ipAddress;
    private String cookie;
    private LoadBalancerTargetPortSpec portSpec;
    private InstanceHealthCheck healthCheck;

    public LoadBalancerTargetInfo(String ipAddress, String name, String cookie, LoadBalancerTargetPortSpec portSpec, InstanceHealthCheck healthCheck) {
        super();
        this.ipAddress = ipAddress;
        this.name = name;
        this.cookie = cookie;
        this.portSpec = portSpec;
        this.healthCheck = healthCheck;
        // LEGACY: the code below is to handle the case when healtcheck is read from the LoadBalancer healtcheck, and
        // port is not specified there as its not required on that level
        if (this.healthCheck != null && this.healthCheck.getPort() == null) {
            this.healthCheck.setPort(portSpec.getPort());
        }
        setUuid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public LoadBalancerTargetPortSpec getPortSpec() {
        return portSpec;
    }

    public void setPortSpec(LoadBalancerTargetPortSpec portSpec) {
        this.portSpec = portSpec;
    }

    public void setUuid() {
        this.uuid = portSpec.getSourcePort() + portSpec.getDomain() + portSpec.getPath();
    }

    public String getUuid() {
        return uuid;
    }

    public InstanceHealthCheck getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(InstanceHealthCheck healthCheck) {
        this.healthCheck = healthCheck;
    }
}
