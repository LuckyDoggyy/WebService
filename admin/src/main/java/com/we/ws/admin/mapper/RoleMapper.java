package com.we.ws.admin.mapper;

import org.apache.ibatis.annotations.*;

/**
 * Created by twogoods on 17/2/19.
 */
@Mapper
public interface RoleMapper {
    @Insert("insert into T_Role(rname) values (#{rolename})")
    int insertRole(@Param("rolename") String rolename);

    @Update("update T_Role set rname=#{rolename} where rid=#{rid}")
    int updateRole(@Param("rolename") String rolename, String rid);

    @Delete("delete from T_Role where rid=#{rid}")
    int deleteRole(@Param("rid")String rid);
}
