package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.domain.Role;
import com.we.ws.admin.mapper.MenuMapper;
import com.we.ws.admin.mapper.RoleMapper;
import com.we.ws.admin.mapper.RoleMenuMapper;
import com.we.ws.admin.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRole(int pageSize, int offset) {
        return roleMapper.listRole(pageSize, offset);
    }

    @Override
    public boolean addRole(String name) {
        roleMapper.insertRole(name);
        return true;
    }

    @Override
    public boolean updateRole(String name, String rid) {
        roleMapper.updateRole(name,rid);
        return true;
    }

    @Override
    public List<Map<String, Object>> listRoleMenu(String rid) {
        List<Menu> parentMenus = menuMapper.getFirstLayerMenu();
        List<Map<String, Object>> menuArr = new ArrayList<>(3);
        for (Menu menu : parentMenus) {
            Map<String, Object> firstmap = new HashMap<>();
            firstmap.put("text", menu.getMenuname());
            firstmap.put("id", menu.getMid());
            firstmap.put("expanded", "true");
            firstmap.put("checked", "true");
            List<Map<String, Object>> secondlayer = menuMapper.getSecondLayerMenuForList(menu.getMid());
            //checked
            firstmap.put("children", secondlayer);

            for (Map<String, Object> secondMenu : secondlayer) {
                List<Map<String, String>> leafmenus = menuMapper.getLeafMenusByPid(secondMenu.get("id").toString());
                for (Map<String, String> leafmenu : leafmenus) {
                    if (roleMenuMapper.checkOwner(leafmenu.get("id"), rid) > 0) {
                        leafmenu.put("checked", "true");
                    } else {
                        leafmenu.put("checked", "false");
                    }
                }
                secondMenu.put("children", leafmenus);
            }
            menuArr.add(firstmap);
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
    public List<Menu> getParentMenuForLogin() {
        return menuMapper.getFirstLayerMenu();
    }

    @Override
    public List<Map<String, Object>> getRoleMenuForLogin(String pid, String uid) {
        List<Map<String, Object>> secondLayer = menuMapper.getSecondLayerMenu(pid);
        for (Map<String, Object> menu : secondLayer) {
            menu.put("children", menuMapper.getLeafMenu(menu.get("id").toString(), uid));
            menu.put("expanded", "true");
        }
        return secondLayer;
    }

    @Override
    public Map<String, String> getView(String mid) {
        return menuMapper.getView(mid);
    }
}
