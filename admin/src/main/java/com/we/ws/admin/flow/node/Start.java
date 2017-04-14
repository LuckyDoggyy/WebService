package com.we.ws.admin.flow.node;

import com.we.ws.admin.flow.node.Node;
import com.we.ws.common.data.Pair;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class Start extends Node {

    @Override
    public Pair<Node, Object> handle(Object param) {
        return Pair.of(next, null);
    }
}
