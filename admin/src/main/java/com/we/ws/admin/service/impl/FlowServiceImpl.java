package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Flow;
import com.we.ws.admin.flow.FlowCache;
import com.we.ws.admin.flow.FlowException;
import com.we.ws.admin.flow.FlowParser;
import com.we.ws.admin.flow.node.Node;
import com.we.ws.admin.mapper.FlowMapper;
import com.we.ws.admin.service.FlowService;
import com.we.ws.common.data.Pair;
import com.we.ws.common.util.JsonUtils;
import com.we.ws.service.client.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private FlowMapper flowMapper;

    @Override
    public boolean add(Flow flow) {
        flowMapper.insertReturnKey(flow);
        return true;
    }

    @Override
    public boolean update(Flow flow) {
        return flowMapper.update(flow) == 1;
    }

    @Override
    public List<Flow> listFlows(String flowid, String flowname) {
        //TODO like
        return flowMapper.listFlows(flowid, flowname);
    }

    @Override
    public boolean deleteFlows(String autoids) {
        return flowMapper.setFlowState(autoids.split(","), 2) > 0;
    }

    @Override
    public boolean enableFlows(String autoids) {
        return flowMapper.setFlowState(autoids.split(","), 0) > 0;
    }

    @Override
    public boolean unableFlows(String autoids) {
        return flowMapper.setFlowState(autoids.split(","), 1) > 0;
    }

    @Override
    public String getJsonById(String autoid) {
        return flowMapper.getJsonById(autoid);
    }

    @Override
    public Map<String, Object> call(String flowId, String callParams) throws Exception {
        Node node = FlowCache.getFlowTree(Integer.parseInt(flowId));
        if (node == null) {
            Pair<Node, Flow> flow = FlowParser.parseWithFlow(getJsonById(flowId));
            node = flow.getL();
            if (node == null) {
                throw new FlowException(String.format("can't find flow id: %s", flowId));
            }
            FlowCache.addCache(Integer.parseInt(flowId), node);
        }
        List<RequestParam> list = JsonUtils.listFromJson(callParams, RequestParam.class);
        Map<String, Object> param = new HashMap<>();
        for (RequestParam requestParam : list) {
            param.put(requestParam.getName(), requestParam.getValue());
        }
        while (node != null) {
            Pair<Node, Map<String, Object>> result = node.handle(param);
            node = result.getL();
            param = result.getR();
        }
        return param;
    }
}
