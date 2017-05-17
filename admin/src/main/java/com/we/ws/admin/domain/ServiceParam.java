package com.we.ws.admin.domain;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-27
 */
public class ServiceParam {
    private long autoid;
    private long sid;
    private String paramName;
    private String alies;
    private String remark;
    private int state;

    public long getAutoid() {
        return autoid;
    }

    public void setAutoid(long autoid) {
        this.autoid = autoid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ServiceParam{" +
                "autoid=" + autoid +
                ", sid=" + sid +
                ", paramName='" + paramName + '\'' +
                ", alies='" + alies + '\'' +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                '}';
    }
}
