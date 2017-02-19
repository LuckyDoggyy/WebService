package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Role;
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
    @Select("select mid as id ,menuname as text,'true' as leaf from T_RoleMenu where pid=#{pid} and rid= (select rid from T_User where uid=#{uid})")
    List<Map<String,String>> getRoleMenu(@Param("pid")String pid,@Param("uid")long uid);

    @Select("select * from T_Role")
    List<Role> listRole(int pageSize,int offset);


}
