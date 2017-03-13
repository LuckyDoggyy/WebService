package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.mapper.MenuMapper;
import com.we.ws.admin.service.MenuService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-12
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listMenu(String mid) {
        return menuMapper.listMenu(mid);
    }

    @Override
    public List<Menu> getMenuNodes() {
        return menuMapper.getNodes();
    }

    @Override
    public Pair<Boolean,String> addNewMenu(Menu menu) {
        if(menuMapper.checkIdExist(menu.getMid())>0){
            return Pair.of(false,"菜单id已存在");
        }
        if(menuMapper.insert(menu) != 1){
            return Pair.of(false,"菜单添加异常");
        }
        return Pair.of(true,"菜单添加成功");
    }

    @Override
    public Pair<Boolean,String> updateMenu(Menu menu) {
        if(menuMapper.updateMenu(menu) != 1){
            return Pair.of(false,"菜单更新异常");
        }
        return Pair.of(true,"菜单更新成功");
    }

    @Override
    public boolean deleteMenu(String autoids) {
        menuMapper.deleteMenus(autoids.split(","));
        return true;
    }
}
