package com.we.ws.admin.controller;

import com.we.ws.admin.domain.UserRole;
import com.we.ws.admin.service.UserService;
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
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("addUser")
    @ResponseBody
    public Map<String, Object> addUser(String account, String nickName, String remarks) {
        Map<String, Object> result = new HashMap<>();
        if (userService.addUser(account, nickName, remarks)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(String uid, String nickName, String remarks) {
        Map<String, Object> result = new HashMap<>();
        if (userService.updateUser(uid, nickName, remarks)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(String uids) {
        Map<String, Object> result = new HashMap<>();
        if (userService.deleteUser(uids)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;

    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(int page, int start, int limit, String uid, String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", userService.countUser(uid, name));
        result.put("rows", userService.listUser(uid, name, limit, start));
        return result;
    }

    @RequestMapping("listUserRole")
    @ResponseBody
    public Map<String, Object> listRole(String uid) {
        Map<String, Object> result = new HashMap<>();
        List<UserRole> list = userService.getRolesByUid(uid);
        result.put("totalCount", list.size());
        result.put("rows", list);
        return result;
    }

    @RequestMapping("addUserRole")
    @ResponseBody
    public Map<String, Object> addUserRole(String rids, String uid) {
        Map<String, Object> result = new HashMap<>();
        if (userService.addRoles(rids, uid)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("removeUserRole")
    @ResponseBody
    public Map<String, Object> removeUserRole(String autoids) {
        Map<String, Object> result = new HashMap<>();
        if (userService.removeRoles(autoids)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
