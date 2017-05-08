package com.we.ws.admin.flow.node;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public abstract class Node implements Handle {

    protected Node next;

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    protected Map<String, Object> parseInput(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        String[] inputs = input.split(",");
        for (String in : inputs) {
            String[] token = in.split(":");
            if (token.length >= 2) {
                map.put(token[0], token[1]);
            } else {
                map.put(token[0], "");
            }
        }
        return map;
    }

    protected Map<String, Object> parseOutput(String output) {
        if (StringUtils.isEmpty(output)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        String[] outputs = output.split(",");
        for (String out : outputs) {
            String[] token = out.split(":");
            if (token.length >= 2) {
                map.put(token[0], token[1]);
            } else {
                map.put(token[0], "");
            }
        }
        return map;
    }
}
