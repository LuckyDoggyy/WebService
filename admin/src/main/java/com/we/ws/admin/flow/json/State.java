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

    protected Map<String, Integer> attr;

    protected Map<String, String> text;

    public State(){

    }

    public State(String type, Map<String, Map<String, String>> props, Map<String, Integer> attr, Map<String, String> text){
        this.attr = attr;
        this.props = props;
        this.type = type;
        this.text = text;
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }

    public Map<String, Integer> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, Integer> attr) {
        this.attr = attr;
    }

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
