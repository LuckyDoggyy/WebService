package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Service;
import com.we.ws.admin.domain.ServiceParam;
import com.we.ws.admin.service.WsService;
import com.we.ws.common.util.JsonUtils;
import com.we.ws.service.client.KeyValuePair;
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
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
@Controller
@RequestMapping("/ws/")
public class WebServiceController extends BaseController {
    private Logger log = LoggerFactory.getLogger(WebServiceController.class);
    @Autowired
    private WsService wsService;

    @RequestMapping("listWs")
    @ResponseBody
    public Map<String, Object> listWs(int page, int start, int limit, String sid, String serviceName) {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", wsService.countListService(sid, serviceName));
        result.put("rows", wsService.listService(sid, serviceName, limit, start));
        return result;
    }

    @RequestMapping("addWs")
    @ResponseBody
    public Map<String, Object> addWs(Service service, String serviceParams) {
        Map<String, Object> result = new HashMap<>();
        if (wsService.addNewWS(service, serviceParams)) {
            result.put("success", true);
            result.put("obj", "服务添加成功");
        } else {
            result.put("success", false);
            result.put("obj", "服务添加失败");
        }
        return result;
    }

    @RequestMapping("updateWs")
    @ResponseBody
    public Map<String, Object> updateWs(Service service, String serviceParams) {
        Map<String, Object> result = new HashMap<>();
        if (wsService.updateWS(service, serviceParams)) {
            result.put("success", true);
            result.put("obj", "服务修改成功");
        } else {
            result.put("success", false);
            result.put("obj", "服务修改失败");
        }
        return result;
    }

    @RequestMapping("deleteWs")
    @ResponseBody
    public Map<String, Object> deleteWs(String sids) {
        Map<String, Object> result = new HashMap<>();
        wsService.deleteWS(sids);
        result.put("success", true);
        result.put("obj", "服务删除成功");
        return result;
    }

    @RequestMapping("listWsParam")
    @ResponseBody
    public Map<String, Object> listWsParam(String sid) {
        Map<String, Object> result = new HashMap<>();
        List<ServiceParam> list = wsService.listParams(sid);
        result.put("totalCount", list.size());
        result.put("rows", list);
        return result;
    }

    @RequestMapping("callWs")
    @ResponseBody
    public Map<String, Object> callWs(Service service, String callParams) {
        Map<String, Object> result = new HashMap<>();
        List<KeyValuePair> list = JsonUtils.listFromJson(callParams, KeyValuePair.class);
        result.put("success", true);
        result.put("obj", "服务修改失败");
        return result;
    }
}
