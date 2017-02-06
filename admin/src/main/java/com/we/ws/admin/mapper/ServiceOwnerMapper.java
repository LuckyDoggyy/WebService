package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.ServiceOwner;
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
 * @since 2017-02-05
 */
@Mapper
public interface ServiceOwnerMapper {
    @Select("select own.autoid,u.uid,u.name as username,info.sid,info.name as servername,info.ip,info.port,info.detail " +
            "from T_User u,T_ServiceOwner own,T_ServiceInfo info where own.uid=#{uid} " +
            "and own.state=0 and u.uid=own.uid and own.sid=info.sid")
    List<ServiceOwner> listServiceByowner(@Param("uid")String uid);


    void deleteServiceForUser(@Param("uid")String uid,@Param("sids")String[] sids);

    @Insert("insert into T_ServiceOwner (uid,sid) values (#{uid},#{sid})")
    int insertForUser(@Param("uid")String uid,@Param("sid")String sid);

    @Select("select count(*) from T_ServiceOwner where uid=#{uid} and sid=#{sid} and state=0")
    int checkExist(@Param("uid")String uid,@Param("sid")String sid);

}
