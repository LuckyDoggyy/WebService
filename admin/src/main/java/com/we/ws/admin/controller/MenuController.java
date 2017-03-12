package com.we.ws.admin.controller;

import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.service.MenuService;
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
 * @since 2017-03-08
 */
@Controller
@RequestMapping("/menu/")
public class MenuController {
    private Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> listMenu(String mid) {
        List<Menu> list = menuService.listMenu(mid);
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", list.size());
        result.put("rows", list);
        return result;
    }

    @RequestMapping("listNode")
    @ResponseBody
    public Map<String, Object> listNode() {
        List<Menu> list = menuService.getMenuNodes();
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", list.size());
        result.put("rows", list);
        return result;
    }

    @RequestMapping("addMenu")
    @ResponseBody
    public Map<String, Object> addMenu(Menu menu) {
        Map<String, Object> result = new HashMap<>();
        if (menuService.addNewMenu(menu)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("updateMenu")
    @ResponseBody
    public Map<String, Object> updateMenu(Menu menu) {
        Map<String, Object> result = new HashMap<>();
        if (menuService.updateMenu(menu)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("deleteMenu")
    @ResponseBody
    public Map<String, Object> deleteMenu(String autoids) {
        Map<String, Object> result = new HashMap<>();
        if (menuService.deleteMenu(autoids)) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
