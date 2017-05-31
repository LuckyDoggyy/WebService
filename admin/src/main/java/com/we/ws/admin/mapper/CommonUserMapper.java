package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-25
 */
@Mapper
public interface CommonUserMapper {
    @Insert("insert into T_CommonUser(account,password,nickName) values ( #{account},#{account},#{nickName})")
    int insert(@Param("account") String account, @Param("nickName") String nickName);

    @Update("update T_CommonUser set nickName=#{nickName} where uid=#{uid} ")
    int update(@Param("uid") String uid, @Param("nickName") String nickName);

    int delete(@Param("uids") String[] uids);

    @Select("select * from T_CommonUser where account=#{account}")
    User getByAccount(String account);

    @Select("select * from T_CommonUser where uid=#{uid}")
    User getByUid(String uid);

    @Update("update T_CommonUser set password=#{pass} where uid=#{uid}")
    int updatePass(@Param("uid") String uid, @Param("pass") String pass);

    List<User> listUser(@Param("uid") String uid, @Param("account") String account, @Param("pageSize") int pageSize, @Param("offset") int offset);

    int countUser(@Param("uid") String uid, @Param("account") String account);

    @Select("select count(*) from T_CommonUser where account=#{account}")
    int checkExist(@Param("account")String account);

}
