package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("select * from T_Role")
    List<Role> listRole(int pageSize, int offset);
}
