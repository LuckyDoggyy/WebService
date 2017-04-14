package com.we.ws.admin.flow.node;

import com.we.ws.common.data.Pair;

/**
 * Description:
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public interface Handle {
    Pair<Node, Object> handle(Object param);
}
