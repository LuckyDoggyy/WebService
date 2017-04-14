package com.we.ws.admin.flow.json;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class FlowProp {
    private Text name;
    private Text flowid;
    private Text desc;

    public String getName() {
        return name.getValue();
    }

    public void setName(Text name) {
        this.name = name;
    }

    public String getFlowid() {
        return flowid.getValue();
    }

    public void setFlowid(Text flowid) {
        this.flowid = flowid;
    }

    public String getDesc() {
        return desc.getValue();
    }

    public void setDesc(Text desc) {
        this.desc = desc;
    }
}
