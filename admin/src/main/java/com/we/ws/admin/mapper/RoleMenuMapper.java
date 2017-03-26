package com.we.ws.admin.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
@Mapper
public interface RoleMenuMapper {
    @Deprecated
    @Select("select mid as id ,menuname as text,'true' as leaf from T_RoleMenu where pid=#{pid} and rid= (select rid from T_User where uid=#{uid})")
    List<Map<String, String>> getRoleMenu(@Param("pid") String pid, @Param("uid") long uid);

    @Select("select count(*) from T_RoleMenu where mid=#{mid} and rid=#{rid} and state=0")
    int checkOwner(@Param("mid") String mid, @Param("rid") String rid);

    @Select("select count(*) from T_RoleMenu where mid=#{mid} and rid=#{rid}")
    int checkExist(@Param("mid") String mid, @Param("rid") String rid);

    @Delete("delete from T_RoleMenu where rid=#{rid}")
    int deleteAll(@Param("rid") String rid);

    int insertMenus(@Param("mids") String[] mids, @Param("rid") String rid);

}
