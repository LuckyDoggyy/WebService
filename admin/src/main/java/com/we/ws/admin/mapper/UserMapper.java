package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Insert("insert into T_test(name) values ( #{name} )")
    int insert(@Param("name")String name);

    @Select("select * from T_User where name=#{name}")
    User getUserByName(String name);

    List<User> listUser(@Param("uid")String uid,@Param("name")String name,@Param("pageSize")int pageSize,@Param("offset")int offset);

    int countUser(@Param("uid")String uid,@Param("name")String name);

}
