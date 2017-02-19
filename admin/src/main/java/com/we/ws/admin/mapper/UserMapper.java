package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.User;
import com.we.ws.admin.domain.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
@Mapper
public interface UserMapper {

    @Insert("insert into T_User(name,password) values ( #{name},#{name} )")
    int insert(@Param("name")String name);

    int delete(@Param("uids")String[] uids);

    @Select("select * from T_User where name=#{name}")
    User getUserByName(String name);

    List<User> listUser(@Param("uid")String uid,@Param("name")String name,@Param("pageSize")int pageSize,@Param("offset")int offset);

    int countUser(@Param("uid")String uid,@Param("name")String name);


}
