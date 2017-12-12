package com.we.ws.admin.flow.json;

import java.util.Map;

/**
 * Description:
 * json基本的三个结构
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class JsonParseModel {

    private Map<String, State> states;
    private Map<String, Path> paths;
    private Map<String, FlowProp> props;

    public void setStates(Map<String, State> states) {
        this.states = states;
    }

    public void setPaths(Map<String, Path> paths) {
        this.paths = paths;
    }

    public void setProps(Map<String, FlowProp> props) {
        this.props = props;
    }

    public Map<String, State> getStates() {
        return states;
    }

    public Map<String, Path> getPaths() {
        return paths;
    }

    public FlowProp getProps() {
        return props.get("props");
    }

}
