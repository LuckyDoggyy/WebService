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

    protected String name;
    protected String desc;

    protected Node next;


    public Node() {
    }

    public Node(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    protected Map<String, Object> parseInOrOut(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        String[] inputs = str.split(",");
        for (String in : inputs) {
            String[] token = in.split(":");
            if (token.length >= 2) {
                map.put(token[0], token[1]);
            } else if (token.length == 1) {
                map.put(token[0], "");
            }
        }
        return map;
    }
}
