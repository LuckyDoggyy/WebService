package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Flow;
import com.we.ws.admin.domain.Service;
import com.we.ws.admin.domain.User;
import com.we.ws.admin.flow.FlowCache;
import com.we.ws.admin.flow.FlowParser;
import com.we.ws.admin.flow.node.Node;
import com.we.ws.admin.service.FlowService;
import com.we.ws.admin.service.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping("callFlow")
    @ResponseBody
    public Map<String, Object> callFlow(String autoId, String callParams) throws Exception {
        if (!flowService.checkEnable(autoId)) {
            Map<String, Object> result = new HashMap<>();
            result.put("error", "暂不提供服务,稍后再试");
            return result;
        }
        return flowService.call(autoId, callParams);
    }

    @RequestMapping("callFlowInternal")
    @ResponseBody
    public Map<String, Object> callFlowInternal(String autoId, String callParams) throws Exception {
        return flowService.call(autoId, callParams);
    }

    @RequestMapping("addFlow")
    @ResponseBody
    public Map<String, Object> addFlow(HttpServletRequest request, String json) {
        Map<String, Object> map = new HashMap();
        Pair<Node, Flow> pair = FlowParser.parseWithFlow(json);
        Flow flow = pair.getR();
        flow.setUid(Integer.valueOf(getUserid(request)));
        flowService.add(flow);
        if (pair.getL() == null) {
            map.put("success", true);
            map.put("msg", "添加成功,但流程图不正确");
        } else {
            CompletableFuture.runAsync(() -> FlowCache.addCache(flow.getAutoid(), pair.getL()));
            map.put("success", true);
            map.put("msg", "添加成功");
        }
        return map;
    }

    @RequestMapping("updateFlow")
    @ResponseBody
    public Map<String, Object> updateFlow(Integer autoid, String json) {
        Map<String, Object> map = new HashMap();
        Pair<Node, Flow> pair = null;
        pair = FlowParser.parseWithFlow(json);
        Flow flow = pair.getR();
        flow.setAutoid(autoid);
        //TODO 用户鉴权
        if (flowService.update(flow)) {
            if (pair.getL() == null) {
                map.put("success", true);
                map.put("msg", "修改成功,但流程图不正确");
            } else {
                FlowCache.addCache(autoid, pair.getL());
                map.put("success", true);
                map.put("msg", "修改成功");
            }
        } else {
            map.put("success", false);
            map.put("msg", "修改失败");
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
    public String getJsonById(String autoid) throws Exception {
        return flowService.getJsonById(autoid);
    }

    @RequestMapping("getAllById")
    @ResponseBody
    public Flow getById(String autoid) throws Exception {
        return flowService.getById(autoid);
    }

    @RequestMapping("listUser")
    @ResponseBody
    public Map<String, Object> listUser(int page, int start, int limit, String flowid, String uid, String name) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", userService.countUserForFlow(flowid, uid, name));
        result.put("rows", userService.listUserForFlow(flowid, uid, name, limit, start));
        return result;
    }

    @RequestMapping("unableUserInFlow")
    @ResponseBody
    public Map<String, Object> unableUserInFlow(String uids, String flowid) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (flowService.unableUserInFlow(uids, flowid)) {
            result.put("success", true);
            result.put("obj", "用户禁用成功");
        } else {
            result.put("success", false);
            result.put("obj", "禁用失败");
        }
        return result;
    }

    @RequestMapping("enableUserInFlow")
    @ResponseBody
    public Map<String, Object> enableUserInFlow(String uids, String flowid) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (flowService.enableUserInFlow(uids, flowid)) {
            result.put("success", true);
            result.put("obj", "用户解禁成功");
        } else {
            result.put("success", false);
            result.put("obj", "解禁失败");
        }
        return result;
    }

}
