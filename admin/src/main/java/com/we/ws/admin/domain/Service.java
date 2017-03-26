package com.we.ws.admin.domain;

/**
 * Description:
 * @author twogoods
 * @version 0.1
 * @since 2017-03-26
 */
public class Service {
    private long sid;
    private String serviceName;
    private String remark;
    private String url;
    private String wsdlUrl;
    private String targetNamespace;
    private String method;
    private int version=2;
    private int state;

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWsdlUrl() {
        return wsdlUrl;
    }

    public void setWsdlUrl(String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }

    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

    @Override
    public String toString() {
        return "Service{" +
                "sid=" + sid +
                ", serviceName='" + serviceName + '\'' +
                ", remark='" + remark + '\'' +
                ", url='" + url + '\'' +
                ", wsdlUrl='" + wsdlUrl + '\'' +
                ", targetNamespace='" + targetNamespace + '\'' +
                ", method='" + method + '\'' +
                ", version=" + version +
                ", state=" + state +
                '}';
    }
}
