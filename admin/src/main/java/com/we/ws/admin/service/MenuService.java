package com.we.ws.admin.service;

import com.we.ws.admin.domain.Menu;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-12
 */
public interface MenuService {
    List<Menu> listMenu(String mid);

    List<Menu> getMenuNodes();

    boolean addNewMenu(Menu menu);

    boolean updateMenu(Menu menu);

    boolean deleteMenu(String autoids);
}
