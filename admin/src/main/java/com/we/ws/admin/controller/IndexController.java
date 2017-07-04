package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.domain.User;
import com.we.ws.admin.service.RoleService;
import com.we.ws.admin.service.UserService;
import com.we.ws.common.data.Pair;
import com.we.ws.common.exception.TokenException;
import com.we.ws.common.util.MD5Utils;
import com.we.ws.common.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-01
 */
@Controller
public class IndexController extends BaseController {
    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "redirect:login.html";
        }
        for (Cookie c : cookies) {
            if ("uid".equals(c.getName())) {
                return "redirect:home.html";
            }
        }
        return "redirect:login.html";
    }

    @RequestMapping(value = "/in", method = {RequestMethod.POST})
    public String login(HttpServletResponse response, String j_username, String j_password) throws TokenException {
        User u = userService.getByAccount(j_username);
        if (u != null && MD5Utils.getStringMD5(j_password).equals(u.getPassword())) {
            Cookie uid = new Cookie("uid", Long.toString(u.getUid()));
            uid.setMaxAge(90 * 60);
            Cookie token = new Cookie("token", TokenUtils.generateToken(u.getUid()));
            token.setMaxAge(90 * 60);
            token.setMaxAge(90 * 60);
            response.addCookie(uid);
            response.addCookie(token);
            return "redirect:home.html";
        } else {
            return "redirect:relogin.html";
        }
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("uid", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping(value = "/checklogin")
    @ResponseBody
    public Map<String, Object> checkLogin(HttpServletRequest request,HttpServletResponse response) {
        Pair<Boolean, String> result = checkCoookies(request,response);
        Map<String, Object> map = new HashMap<>();
        if (result.getL()) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/getParentMenu")
    @ResponseBody
    public List<Menu> getParentMenu(HttpServletRequest request) {
        String uid = getUserid(request);
        return roleService.getParentMenuForLogin(uid);
    }

    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public List<Map<String, Object>> getMenu(HttpServletRequest request, String node) {
        String uid = getUserid(request);
        return roleService.getRoleMenuForLogin(node, uid);
    }

    @RequestMapping(value = "/getView")
    @ResponseBody
    public Map<String, Object> getView(HttpServletRequest request, String mid) {
        return roleService.getView(mid);
    }
}