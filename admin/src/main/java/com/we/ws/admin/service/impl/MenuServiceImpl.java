package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.mapper.MenuMapper;
import com.we.ws.admin.service.MenuService;
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
    public boolean addNewMenu(Menu menu) {
        return menuMapper.insert(menu) == 1;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu) == 1;
    }

    @Override
    public boolean deleteMenu(String autoids) {
        menuMapper.deleteMenus(autoids.split(","));
        return true;
    }
}
