package com.we.ws.admin.flow.node;

import com.we.ws.admin.flow.json.State;
import com.we.ws.common.data.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-23
 */
public class Reply extends Node {
    private Map<String, Object> output;

    public Reply(String name, String desc, String output) {
        super(name, desc);
        this.output = parseInOrOut(output);
    }

    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : output.entrySet()) {
            Object obj = param.get(entry.getKey());
            if (obj != null) {
                returnMap.put(entry.getKey(), obj);
            }
        }
        if (returnMap.size() == 0) {
            return Pair.of(next, param);
        }
        return Pair.of(next, returnMap);
    }

    public static Reply of(State state) {
        Map<String, Map<String, String>> props = state.getProps();
        String name = props.get("text").get("value");
        String desc = props.get("desc").get("value");
        String output = props.get("output").get("value");
        return new Reply(name, desc, output);
    }
}
