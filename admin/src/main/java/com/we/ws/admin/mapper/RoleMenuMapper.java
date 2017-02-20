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
    @Deprecated
    @Select("select mid as id ,menuname as text,'true' as leaf from T_RoleMenu where pid=#{pid} and rid= (select rid from T_User where uid=#{uid})")
    List<Map<String, String>> getRoleMenu(@Param("pid") String pid, @Param("uid") long uid);

    @Insert("insert into T_Role(rname) values (#{rolename})")
    int insertRole(@Param("rolename") String rolename);

    @Update("update T_Role set rname=#{rolename} where rid=#{rid}")
    int updateRole(@Param("rolename") String rolename, String rid);

    @Delete("delete from T_Role where rid=#{rid}")
    int deleteRole(@Param("rid")String rid);

    @Select("select * from T_Role")
    List<Role> listRole(int pageSize, int offset);

    @Select("select mid as id ,menuname as text,'true' as leaf from T_Menu where pid=#{pid}")
    List<Map<String, String>> getLeafMenus(@Param("pid") String pid);

    @Select("select count(*) from T_RoleMenu where mid=#{mid} and rid=#{rid} and state=0")
    int checkOwner(@Param("mid") String mid, @Param("rid") String rid);

    @Select("select count(*) from T_RoleMenu where mid=#{mid} and rid=#{rid}")
    int checkExist(@Param("mid") String mid, @Param("rid") String rid);

    @Delete("delete from T_RoleMenu where rid=#{rid}")
    int deleteAll(@Param("rid") String rid);

    @Select("SELECT DISTINCT mid AS id, menuname AS text, 'true' AS leaf FROM T_RoleMenu WHERE pid =#{pid} and rid in( select DISTINCT rid from T_UserRole where uid = #{uid})")
    List<Map<String, String>> getRoleMenuForLogin(@Param("pid") String pid, @Param("uid") String uid);

    int insertMenus(@Param("mids") String[] mids, @Param("rid") String rid);

}
