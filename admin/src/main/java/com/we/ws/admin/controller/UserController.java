package com.we.ws.admin.controller;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import com.we.ws.admin.service.UserService;
import org.apache.commons.lang3.tuple.Pair;
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
        Pair<Boolean, String> res = userService.addUser(account, nickName, remarks);
        result.put("success", res.getLeft());
        result.put("success", res.getRight());
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

    @RequestMapping("updatePass")
    @ResponseBody
    public Map<String, Object> updatePass(HttpServletRequest request, String oldPassword, String newPassword) {
        Map<String, Object> result = new HashMap<>();
        String uid = getUserid(request);
        if (!"0".equals(uid)) {
            Pair<Boolean, String> res = userService.updatePass(uid, oldPassword, newPassword);
            result.put("success", res.getLeft());
            result.put("obj", res.getRight());
        } else {
            result.put("success", false);
            result.put("obj", "您未登陆");
        }
        return result;
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(int page, int start, int limit, String uid, String name) throws Exception {
        CompletableFuture<Integer> count = CompletableFuture.supplyAsync(() -> userService.countUser(uid, name));
        CompletableFuture<List<User>> list = CompletableFuture.supplyAsync(() -> userService.listUser(uid, name, limit, start));

        CompletableFuture<Map> ret = CompletableFuture.supplyAsync(() -> {
            Map<String, Object> result = new HashMap<>();
            try {
                result.put("totalCount", count.get());
                result.put("rows", list.get());
            } catch (Exception e) {
                //TODO 异步的异常处理
                e.printStackTrace();
            }
            return result;
        });
        return ret.get();
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
