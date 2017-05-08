package com.we.ws.admin.flow.node;

import com.we.ws.common.data.Pair;

import java.util.Map;

/**
 * Description:
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public interface Handle {
    Pair<Node, Map<String, Object>> handle(Map<String, Object> param) throws Exception;
}
