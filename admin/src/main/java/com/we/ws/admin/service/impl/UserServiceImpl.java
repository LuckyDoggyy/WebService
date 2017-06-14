package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import com.we.ws.admin.mapper.UserMapper;
import com.we.ws.admin.mapper.UserRoleMapper;
import com.we.ws.admin.service.UserService;
import com.we.ws.common.util.MD5Utils;
import org.apache.commons.lang3.tuple.Pair;
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
    public User getByAccount(String name) {
        return userMapper.getByAccount(name);
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
    public Pair<Boolean, String> addUser(String account, String nickName, String remarks) {
        if (userMapper.checkExist(account) > 0) {
            return Pair.of(false, "账号已存在请更换");
        }
        if (userMapper.insert(account, nickName, remarks) != 1) {
            return Pair.of(false, "用户添加异常");
        }
        return Pair.of(true, "用户添加成功");
    }

    @Override
    public boolean updateUser(String uid, String nickName, String remarks) {
        return userMapper.update(uid, nickName, remarks) == 1;
    }

    @Override
    public boolean deleteUser(String uids) {
        userMapper.delete(uids.split(","));
        return true;
    }

    @Override
    public Pair<Boolean, String> updatePass(String uid, String oldPass, String newPass) {
        User user = userMapper.getByUid(uid);
        if (user != null && MD5Utils.getStringMD5(oldPass).equals(user.getPassword())) {
            userMapper.updatePass(uid, MD5Utils.getStringMD5(newPass));
        } else {
            return Pair.of(false, "原密码错误");
        }
        return Pair.of(true, "密码修改成功");
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
