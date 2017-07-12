package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Tag;
import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.domain.Role;
import com.we.ws.admin.mapper.*;
import com.we.ws.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private FlowMapper flowMapper;

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
        roleMapper.updateRole(name, rid);
        return true;
    }

    @Override
    public List<Map<String, Object>> listRoleMenu(String rid) {
        List<Menu> parentMenus = menuMapper.getFirstLayerMenu();
        List<Map<String, Object>> menuArr = new ArrayList<>(3);
        for (Menu menu : parentMenus) {
            //硬编码，服务菜单不展示
            if (menu.getMid().equals("003")) {
                continue;
            }

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
    public List<Menu> getParentMenuForLogin(String uid) {
        List<Menu> all = menuMapper.getFirstLayerMenu();

        Iterator iterator = all.iterator();
        while (iterator.hasNext()) {
            Menu menu = (Menu) iterator.next();
            //硬编码，服务直接展示
            if (menu.getMid().equals("003")) {
                continue;
            }
            if (menuMapper.checkFirstLayerMenu(uid, menu.getMid() + "%") == 0) {
                iterator.remove();
            }
        }
        return all;
    }

    @Override
    public List<Map<String, Object>> getRoleMenuForLogin(String pid, String uid) {
        if ("003".equals(pid)) {
            return generateMenu(uid, 1);
        }
        List<Map<String, Object>> secondLayer = menuMapper.getSecondLayerMenu(pid);
        Iterator iterator = secondLayer.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> menu = (Map<String, Object>) iterator.next();
            List<Map<String, String>> leaf;
            if ("003".equals(pid)) {
                //TODO 注意，服务栏菜单 特殊处理,此处可以去掉
                leaf = menuMapper.getLeafMenuForFlow(menu.get("id").toString(), uid);
            } else {
                leaf = menuMapper.getLeafMenuWithCheck(menu.get("id").toString(), uid);
            }
            if (leaf.size() == 0) {
                iterator.remove();
            } else {
                menu.put("children", leaf);
                menu.put("expanded", "true");
            }
        }
        return secondLayer;
    }

    public List<Map<String, Object>> getServiceMenuForLogin(String uid) {
//        List<Map<String, Object>> menus = new ArrayList<>();
//        List<FlowTag> tags = flowTagMapper.listFlowTags("0");
//        tags.forEach(tag -> {
//            List<Map<String, Object>> flows = flowMapper.getForMenu(tag.getAutoId(), uid);
//            if (flows.size() > 0) {
//                List<Map<String, Object>> leafs = new ArrayList<>();
//                flows.forEach(flow -> {
//                    Map<String, Object> leaf = new HashMap<>();
//                    leaf.put("id", "fid:" + flow.get("autoid").toString());
//                    leaf.put("leaf", "true");
//                    leaf.put("text", flow.get("flowid"));
//                    leafs.add(leaf);
//                });
//                Map<String, Object> item = new HashMap<>();
//                item.put("id", "parent:" + tag.getAutoId());
//                item.put("leaf", "false");
//                item.put("text", tag.getTagName());
//                item.put("expanded", "true");
//                item.put("children", leafs);
//                menus.add(item);
//            }
//        });
//        return menus;
        return generateMenu(uid, 1);
    }


    private List<Map<String, Object>> generateMenu(String uid, int pid) {
        List<Tag> tags = tagMapper.listTags(String.valueOf(pid));
        List<Map<String, Object>> menus = new ArrayList<>();
        tags.forEach(tag -> {
            List<Map<String, Object>> pres = generateMenu(uid, tag.getAutoId());
            List<Map<String, Object>> flows = flowMapper.getForMenu(tag.getAutoId(), uid);
            List<Map<String, Object>> leafs = new ArrayList<>();
            if (flows.size() > 0) {
                flows.forEach(flow -> {
                    Map<String, Object> leaf = new HashMap<>();
                    leaf.put("id", "fid:" + flow.get("autoid").toString());
                    leaf.put("leaf", "true");
                    leaf.put("text", flow.get("flowid"));
                    leafs.add(leaf);
                });
            }
            leafs.addAll(pres);
            Map<String, Object> item = new HashMap<>();
            item.put("id", "parent:" + tag.getAutoId());
            item.put("leaf", "false");
            item.put("text", tag.getTagName());
            item.put("expanded", "true");
            item.put("children", leafs);
            menus.add(item);
        });
        return menus;
    }

    @Override
    public Map<String, Object> getView(String mid) {
        return menuMapper.getView(mid);
    }
}
