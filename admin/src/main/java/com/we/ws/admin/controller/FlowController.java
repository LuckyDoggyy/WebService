package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Flow;
import com.we.ws.admin.domain.Service;
import com.we.ws.admin.flow.FlowCache;
import com.we.ws.admin.flow.FlowParser;
import com.we.ws.admin.flow.node.Node;
import com.we.ws.admin.service.FlowService;
import com.we.ws.common.data.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/**
 * Description:
 *
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

    @RequestMapping("callFlow")
    @ResponseBody
    public Map<String, Object> callFlow(String autoId, String callParams) throws Exception {
        return flowService.call(autoId, callParams);
    }

    @RequestMapping("addFlow")
    @ResponseBody
    public Map<String, Object> addFlow(String json) throws Exception {
        Map<String, Object> map = new HashMap();
        Pair<Node, Flow> pair = FlowParser.parseWithFlow(json);
        Flow flow = pair.getR();
        flowService.add(flow);
        CompletableFuture.runAsync(() -> FlowCache.addCache(flow.getAutoid(), pair.getL()));
        map.put("success", true);
        return map;
    }

    @RequestMapping("updateFlow")
    @ResponseBody
    public Map<String, Object> updateFlow(Integer autoid, String json) throws Exception {
        Map<String, Object> map = new HashMap();
        Pair<Node, Flow> pair = FlowParser.parseWithFlow(json);
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
            map.put("obj", "流程删除成功");
        } else {
            map.put("success", false);
            map.put("obj", "流程删除失败");
        }
        return map;
    }

    @RequestMapping("enableFlows")
    @ResponseBody
    public Map<String, Object> enableFlows(String autoids) throws Exception {
        Map<String, Object> map = new HashMap();
        if (flowService.enableFlows(autoids)) {
            FlowCache.removeCache(autoids.split(","));
            map.put("success", true);
            map.put("obj", "流程启用成功");
        } else {
            map.put("success", false);
            map.put("obj", "流程企业失败");
        }
        return map;
    }

    @RequestMapping("unableFlows")
    @ResponseBody
    public Map<String, Object> unableFlows(String autoids) throws Exception {
        Map<String, Object> map = new HashMap();
        if (flowService.unableFlows(autoids)) {
            FlowCache.removeCache(autoids.split(","));
            map.put("success", true);
            map.put("obj", "流程停用成功");
        } else {
            map.put("success", false);
            map.put("obj", "流程停用失败");
        }
        return map;
    }

    @RequestMapping("listFlows")
    @ResponseBody
    public Map<String, Object> listFlows(HttpServletRequest request, String flowid, String flowname) throws Exception {
        String uid = getUserid(request);
        Map<String, Object> map = new HashMap();
        List<Flow> list = flowService.listFlows(flowid, flowname, uid);
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
