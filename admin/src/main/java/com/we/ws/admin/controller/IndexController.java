package com.we.ws.admin.controller;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.mapper.RoleMenuMapper;
import com.we.ws.admin.service.impl.UserServiceImpl;
import com.we.ws.common.data.Pair;
import com.we.ws.common.exception.TokenException;
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
    private UserServiceImpl userService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    //

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
    public String index(HttpServletResponse response, String j_username, String j_password) throws TokenException {
        User u = userService.getByName(j_username);
        if (u != null && j_password.equals(u.getPassword())) {
            Cookie uid = new Cookie("uid", "1");
            uid.setMaxAge(2000);
            Cookie token = new Cookie("token", TokenUtils.generateToken(1));
            token.setMaxAge(2000);
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
    public Map<String, Object> checkLogin(HttpServletRequest request) {
        Pair<Boolean, String> result = checkCoookies(request);
        Map<String, Object> map = new HashMap<>();
        if (result.getL()) {
            map.put("success", true);
        }else{
            map.put("success", false);
        }
        return map;
    }


    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public List<Map<String, String>> getMenu(HttpServletRequest request, String node) {
        String uid = getUserid(request);
        return roleMenuMapper.getRoleMenu(node, Long.parseLong(uid));
    }


}
