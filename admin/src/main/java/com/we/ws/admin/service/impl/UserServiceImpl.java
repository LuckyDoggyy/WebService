package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.mapper.UserMapper;
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

    @Override
    public User getByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public List<User> listUser(String uid, String name, int pageSize, int offset) {
        return userMapper.listUser(uid,name,pageSize,offset);
    }

    @Override
    public int countUser(String uid, String name) {
        return userMapper.countUser(uid,name);
    }
}
