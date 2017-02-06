package com.we.ws.admin.controller;

import com.we.ws.admin.service.UserService;
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
 * @since 2017-02-02
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(int page, int start, int limit, String uid, String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", userService.countUser(uid, name));
        result.put("rows", userService.listUser(uid, name, limit, start));
        return result;
    }


}
