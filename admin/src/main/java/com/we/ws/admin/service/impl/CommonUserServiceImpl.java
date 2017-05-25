package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import com.we.ws.admin.mapper.CommonUserMapper;
import com.we.ws.admin.mapper.UserMapper;
import com.we.ws.admin.mapper.UserRoleMapper;
import com.we.ws.admin.service.CommonUserService;
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
public class CommonUserServiceImpl implements CommonUserService {
    @Autowired
    private CommonUserMapper commonUserMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByAccount(String name) {
        return commonUserMapper.getByAccount(name);
    }

    @Override
    public List<User> listUser(String uid, String name, int pageSize, int offset) {
        return commonUserMapper.listUser(uid, name, pageSize, offset);
    }

    @Override
    public int countUser(String uid, String name) {
        return commonUserMapper.countUser(uid, name);
    }

    @Override
    public Pair<Boolean, String> addUser(String account, String nickName) {
        if (commonUserMapper.checkExist(account) > 0 || userMapper.checkExist(account) > 0) {
            return Pair.of(false, "账号已存在请更换");
        }
        if (commonUserMapper.insert(account, nickName) != 1) {
            return Pair.of(false, "用户注册异常");
        }
        return Pair.of(true, "用户注册成功");
    }

    @Override
    public boolean updateUser(String uid, String nickName) {
        return commonUserMapper.update(uid, nickName) == 1;
    }

    @Override
    public boolean deleteUser(String uids) {
        commonUserMapper.delete(uids.split(","));
        return true;
    }

    @Override
    public Pair<Boolean, String> updatePass(String uid, String oldPass, String newPass) {
        User user = commonUserMapper.getByUid(uid);
        if (user != null && MD5Utils.getStringMD5(oldPass).equals(user.getPassword())) {
            commonUserMapper.updatePass(uid, MD5Utils.getStringMD5(newPass));
        } else {
            return Pair.of(false, "原密码错误");
        }
        return Pair.of(true, "密码修改成功");
    }

}
