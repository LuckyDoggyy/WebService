package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Role;
import com.we.ws.admin.service.RoleService;
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
 * @since 2017-02-04
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController {

    private Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleMenuService;

    @RequestMapping("listRole")
    @ResponseBody
    public Map<String, Object> listRole() {
        List<Role> list = roleMenuService.listRole(50, 0);
        Map<String, Object> map = new HashMap();
        map.put("rows", list);
        map.put("totalCount", list.size());
        return map;
    }

    @RequestMapping("addRole")
    @ResponseBody
    public Map<String, Object> addRole(String name) {
        Map<String, Object> map = new HashMap();
        if (roleMenuService.addRole(name)) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("updateRole")
    @ResponseBody
    public Map<String, Object> updateRole(String rname, String rid) {
        Map<String, Object> map = new HashMap();
        if (roleMenuService.updateRole(rname, rid)) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("listRoleMenu")
    @ResponseBody
    public List<Map<String, Object>> listRoleMenu(String node, String rid) {
        return roleMenuService.listRoleMenu(rid);
    }

    @RequestMapping("saveRoleMenu")
    @ResponseBody
    public Map<String, Object> saveRoleMenu(String mids, String rid) {
        Map<String, Object> map = new HashMap();
        if (roleMenuService.saveRoleMenu(mids, rid)) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }
}
