package com.we.ws.admin.service;

import com.we.ws.admin.domain.Menu;
import com.we.ws.admin.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-04
 */
public interface RoleService {

    List<Role> listRole(int pageSize, int offset);

    boolean addRole(String name);

    boolean updateRole(String name, String rid);

    List<Map<String, Object>> listRoleMenu(String rid);

    boolean saveRoleMenu(String mids, String rid);

    List<Menu> getParentMenuForLogin();

    List<Map<String, Object>> getRoleMenuForLogin(String pid,String uid);

}
