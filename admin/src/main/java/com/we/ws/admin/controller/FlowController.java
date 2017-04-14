package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Flow;
import com.we.ws.admin.flow.FlowCache;
import com.we.ws.admin.flow.FlowPraser;
import com.we.ws.admin.flow.node.Node;
import com.we.ws.admin.service.FlowService;
import com.we.ws.common.data.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
@Controller
@RequestMapping("/flow/")
public class FlowController extends BaseController {
    private Logger log = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private FlowService flowService;

    @RequestMapping("addFlow")
    @ResponseBody
    public Map<String, Object> addFlow(String json) throws Exception {
        Map<String, Object> map = new HashMap();
        Pair<Node, Flow> pair = FlowPraser.praseWithFlow(json);
        Flow flow = pair.getR();
        flowService.add(flow);
        FlowCache.addCache(flow.getAutoid(), pair.getL());
        map.put("success", true);
        return map;
    }

    @RequestMapping("updateFlow")
    @ResponseBody
    public Map<String, Object> updateFlow(Integer autoid, String json) throws Exception {
        Map<String, Object> map = new HashMap();
        Pair<Node, Flow> pair = FlowPraser.praseWithFlow(json);
        Flow flow = pair.getR();
        flow.setAutoid(autoid);
        if (flowService.update(flow)) {
            FlowCache.addCache(autoid, pair.getL());
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("deleteFlows")
    @ResponseBody
    public Map<String, Object> deleteFlows(String autoids) throws Exception {
        Map<String, Object> map = new HashMap();
        if (flowService.deleteFlows(autoids)) {
            FlowCache.removeCache(autoids.split(","));
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("listFlows")
    @ResponseBody
    public Map<String, Object> listFlows(String flowid, String flowname) throws Exception {
        Map<String, Object> map = new HashMap();
        List<Flow> list = flowService.listFlows(flowid, flowname);
        map.put("rows", list);
        map.put("totalCount", list.size());
        return map;
    }

    @RequestMapping("getJson")
    @ResponseBody
    public String getById(String autoid) throws Exception {
        return flowService.getJsonById(autoid);
    }
}
