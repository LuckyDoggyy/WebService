package com.we.ws.admin.service;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
public interface CommonUserService {
    User getByAccount(String name);

    List<User> listUser(String uid, String name, int pageSize, int offset);

    int countUser(String uid, String name);

    Pair<Boolean,String> addUser(String account, String nickName);

    boolean updateUser(String uid, String nickName);

    boolean deleteUser(String uids);

    Pair<Boolean,String> updatePass(String uid, String oldPass, String newPass);
}
