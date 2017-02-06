package com.we.ws.admin.service;

import com.we.ws.admin.domain.User;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
public interface UserService {
    User getByName(String name);

    List<User> listUser(String uid,String name,int pageSize,int offset);

    int countUser(String uid,String name);
}
