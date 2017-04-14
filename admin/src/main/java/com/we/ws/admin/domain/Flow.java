package com.we.ws.admin.domain;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class Flow {
    private int autoid;
    private String flowid;
    private String flowname;
    private String description;
    private String flowjson;

    public Flow() {
    }

    public Flow(String flowid, String flowname, String description, String flowjson) {
        this.flowid = flowid;
        this.flowname = flowname;
        this.description = description;
        this.flowjson = flowjson;
    }

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlowjson() {
        return flowjson;
    }

    public void setFlowjson(String flowjson) {
        this.flowjson = flowjson;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "autoid=" + autoid +
                ", flowid='" + flowid + '\'' +
                ", flowname='" + flowname + '\'' +
                ", description='" + description + '\'' +
                ", flowjson='" + flowjson + '\'' +
                '}';
    }
}
