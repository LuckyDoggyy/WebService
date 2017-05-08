package com.we.ws.admin.flow.node;

import com.we.ws.common.data.Pair;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class End extends Node {

    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) {
        return Pair.of(null, param);
    }
}
