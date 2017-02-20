package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Role;
import com.we.ws.admin.mapper.RoleMenuMapper;
import com.we.ws.admin.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
@Component
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> listRole(int pageSize, int offset) {
        return roleMenuMapper.listRole(pageSize, offset);
    }

    @Override
    public boolean addRole(String name) {
        roleMenuMapper.insertRole(name);
        return true;
    }

    @Override
    public boolean updateRole(String name, String rid) {
        return false;
    }

    private String[] pids = {"basicinfomanage", "systemmanage"};
    private String[] pnames = {"基本信息管理", "系统管理"};


    @Override
    public List<Map<String, Object>> listRoleMenu(String rid) {

        List<Map<String, Object>> menuArr = new ArrayList<>(3);
        for (int i = 0; i < pids.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", pnames[i]);
            map.put("id", pids[i]);
            map.put("expanded", "true");
            map.put("checked", "true");
            List<Map<String, String>> leafmenus = roleMenuMapper.getLeafMenus(pids[i]);
            for (Map<String, String> menu : leafmenus) {
                if (roleMenuMapper.checkOwner(menu.get("id"), rid) > 0) {
                    menu.put("checked", "true");
                } else {
                    menu.put("checked", "false");
                }
            }
            map.put("children", leafmenus);
            menuArr.add(map);
        }
        return menuArr;
    }

    @Override
    public boolean saveRoleMenu(String mids, String rid) {
        String[] menuids = mids.split(",");
        roleMenuMapper.deleteAll(rid);
        if (menuids.length > 0) {
            roleMenuMapper.insertMenus(menuids, rid);
        }
        return true;
    }

    @Override
    public List<Map<String, String>> getRoleMenuForLogin(String pid, String uid) {
        return roleMenuMapper.getRoleMenuForLogin(pid,uid);
    }
}
