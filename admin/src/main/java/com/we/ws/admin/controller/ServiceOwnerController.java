package com.we.ws.admin.controller;

import com.we.ws.admin.domain.ServiceOwner;
import com.we.ws.admin.service.ServiceOwnerService;
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
@RequestMapping("/wsowner/")
public class ServiceOwnerController {

    private Logger log = LoggerFactory.getLogger(ServiceOwnerController.class);
    @Autowired
    private ServiceOwnerService serviceOwnerService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(int page, int start, int limit, String uid) {
        Map<String, Object> result = new HashMap<>();
        List<ServiceOwner> list=serviceOwnerService.listServerByOwner(uid);
        result.put("totalCount", list.size());
        result.put("rows", list);
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteWsForUser(String uid,String sids) throws Exception{
        Map<String, Object> result = new HashMap<>();
        result.put("success", serviceOwnerService.deleteForUser(uid,sids));
        result.put("obj", "删除成功");
        return result;
    }

    @RequestMapping("addServiceForUser")
    @ResponseBody
    public Map<String, Object> addServiceForUser(String uid,String sids) throws Exception{
        Map<String, Object> result = new HashMap<>();
        result.put("success", serviceOwnerService.addServiceForUser(uid,sids));
        result.put("obj", "添加成功");
        return result;
    }
}
