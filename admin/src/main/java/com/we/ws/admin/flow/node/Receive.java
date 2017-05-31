package com.we.ws.admin.flow.node;

import com.we.ws.admin.flow.json.State;
import com.we.ws.common.data.Pair;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-08
 */
public class Receive extends Node {
    private Map<String, Object> input;

    public Receive(String name, String desc, String input) {
        super(name, desc);
        this.input = parseInOrOut(input);
    }

    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) {
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            Object value = param.get(entry.getKey());
            if (value == null) {
                param.put(entry.getKey(), entry.getValue());
            }
        }
        return Pair.of(next, param);
    }

    public static Receive of(State state) {
        Map<String, Map<String, String>> props = state.getProps();
        String name = props.get("text").get("value");
        String desc = props.get("desc").get("value");
        String input = props.get("input").get("value");
        return new Receive(name, desc, input);
    }

    public String getParamName() {
        StringBuffer sb = new StringBuffer();
        if (input.size() == 0) {
            return "";
        }
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            sb.append(",");
            sb.append(entry.getKey());
        }
        return sb.substring(1);
    }
}
