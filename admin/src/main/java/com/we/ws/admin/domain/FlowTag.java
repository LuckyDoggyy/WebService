package com.we.ws.admin.domain;

/**
 * @author twogoods
 * @since 2017-07-12
 */
public class FlowTag {
    private int autoid;
    private int flowid;
    private int tagid;
    private String tagName;

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public int getFlowid() {
        return flowid;
    }

    public void setFlowid(int flowid) {
        this.flowid = flowid;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
