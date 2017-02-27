package com.we.ws.admin.controller;

import com.we.ws.admin.service.WsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
public class WebServiceController extends BaseController{

    private Logger log = LoggerFactory.getLogger(WebServiceController.class);
    @Autowired
    private WsService wsService;
    @RequestMapping("listWs")
    @ResponseBody
    public Map<String, Object> listWs(int page, int start, int limit, String sid, String name) {
        log.info("hhah");
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", wsService.countListService(sid,name));
        result.put("rows", wsService.listService(sid, name, limit, start));
        return result;
    }
}
