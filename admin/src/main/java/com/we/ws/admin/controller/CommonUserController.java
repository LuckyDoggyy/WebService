package com.we.ws.admin.controller;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import com.we.ws.admin.service.CommonUserService;
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

/**
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
@Controller
@RequestMapping("/commonuser/")
public class CommonUserController extends BaseController {
    private Logger log = LoggerFactory.getLogger(CommonUserController.class);

    @Autowired
    private CommonUserService userService;

    @RequestMapping("register")
    @ResponseBody
    public Map<String, Object> addUser(String account, String nickName) {
        Map<String, Object> result = new HashMap<>();
        Pair<Boolean, String> res = userService.addUser(account, nickName);
        result.put("success", res.getLeft());
        result.put("obj", res.getRight());
        return result;
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(String uid, String nickName) {
        Map<String, Object> result = new HashMap<>();
        if (userService.updateUser(uid, nickName)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("disableUser")
    @ResponseBody
    public Map<String, Object> disableUser(String uids) {
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
                log.error("exception: {}",e);
            }
            return result;
        });
        return ret.get();
    }
}
