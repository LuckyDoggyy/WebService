package com.we.ws.admin.flow.json;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class State {
    protected String type;
    protected Map<String, Map<String, String>> props;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Map<String, String>> getProps() {
        return props;
    }

    public void setProps(Map<String, Map<String, String>> props) {
        this.props = props;
    }
}
