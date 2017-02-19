package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Role;
import com.we.ws.admin.mapper.RoleMenuMapper;
import com.we.ws.admin.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return roleMenuMapper.listRole(pageSize,offset);
    }

    @Override
    public boolean addRole(String name) {
        return false;
    }

    @Override
    public boolean updateRole(String name, String rid) {
        return false;
    }
}
