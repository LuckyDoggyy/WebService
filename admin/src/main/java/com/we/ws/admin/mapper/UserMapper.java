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

    @Insert("insert into T_User(account,password,nickName,remarks) values ( #{account},#{account},#{nickName},#{remarks} )")
    int insert(@Param("account")String account,@Param("nickName")String nickName,@Param("remarks")String remarks);

    @Update("update T_User set nickName=#{nickName},remarks=#{remarks} where uid=#{uid} ")
    int update(@Param("uid")String uid,@Param("nickName")String nickName,@Param("remarks")String remarks);

    int delete(@Param("uids")String[] uids);

    @Select("select * from T_User where account=#{account}")
    User getUserByName(String account);

    List<User> listUser(@Param("uid")String uid,@Param("account")String account,@Param("pageSize")int pageSize,@Param("offset")int offset);

    int countUser(@Param("uid")String uid,@Param("account")String account);


}
