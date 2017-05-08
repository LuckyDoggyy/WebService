package com.we.ws.admin.flow;

import com.we.ws.admin.flow.node.Node;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class FlowCache {
    public static Map<Integer, Node> cache = new ConcurrentHashMap<>();

    public static Node getFlowTree(int autoid) {
        return cache.get(autoid);
    }

    public static void addCache(Integer autoid, Node node) {
        cache.put(autoid, node);
    }

    public static void removeCache(Integer autoid) {
        cache.remove(autoid);
    }

    public static void removeCache(String[] autoids) {
        for (String item : autoids) {
            try {
                cache.remove(Integer.parseInt(item));
            } catch (Exception e) {
            }
        }
    }

}
