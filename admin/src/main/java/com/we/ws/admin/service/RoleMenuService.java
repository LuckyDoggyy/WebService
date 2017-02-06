package com.we.ws.admin.service;

import com.we.ws.admin.domain.Role;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-04
 */
public interface RoleMenuService {

    List<Role> listRole(int pageSize,int offset);

    boolean addRole(String name);

    boolean updateRole(String name, String rid);
}
