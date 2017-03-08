package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import com.we.ws.admin.mapper.UserMapper;
import com.we.ws.admin.mapper.UserRoleMapper;
import com.we.ws.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User getByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public List<User> listUser(String uid, String name, int pageSize, int offset) {
        return userMapper.listUser(uid, name, pageSize, offset);
    }

    @Override
    public int countUser(String uid, String name) {
        return userMapper.countUser(uid, name);
    }

    @Override
    public boolean addUser(String account, String nickName, String remarks) {
        return userMapper.insert(account, nickName, remarks) == 1;
    }

    @Override
    public boolean updateUser(String uid, String nickName, String remarks) {
        return userMapper.update(uid, nickName, remarks)==1;
    }

    @Override
    public boolean deleteUser(String uids) {
        userMapper.delete(uids.split(","));
        return true;
    }

    @Override
    public List<UserRole> getRolesByUid(String uid) {
        return userRoleMapper.getRoles(uid);
    }

    @Override
    public boolean addRoles(String rids, String uid) {
        String[] ids = rids.split(",");
        for (String id : ids) {
            if (userRoleMapper.checkExist(id, uid) == 0) {
                userRoleMapper.addRole(id, uid);
            }
        }
        return true;
    }

    @Override
    public boolean removeRoles(String autoids) {
        userRoleMapper.removeRoles(autoids.split(","));
        return true;
    }
}
