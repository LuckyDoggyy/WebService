package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-08
 */
@Mapper
public interface MenuMapper {

    @Select("SELECT DISTINCT m.mid AS id, m.menuname AS text, 'true' AS leaf FROM T_RoleMenu r,T_Menu m WHERE" +
            " m.pid = #{pid} and r.rid in ( select DISTINCT rid from T_UserRole where uid = #{uid}) and r.mid=m.mid")
    List<Map<String, String>> getLeafMenu(@Param("pid") String pid, @Param("uid") String uid);

    int insertMenus(@Param("mids") String[] mids, @Param("rid") String rid);

    @Select("select * from T_Menu where pid='0'")
    List<Menu> getFirstLayerMenu();

    @Select("SELECT DISTINCT mid AS id, menuname AS text, 'false' AS leaf FROM T_Menu WHERE pid = #{pid} ")
    List<Map<String, Object>> getSecondLayerMenu(@Param("pid")String firstLayerMid);

    @Select("SELECT DISTINCT mid AS id, menuname AS text, 'false' AS leaf,'true' as checked FROM T_Menu WHERE pid = #{pid} ")
    List<Map<String, Object>> getSecondLayerMenuForList(@Param("pid")String firstLayerMid);

    @Select("select mid as id ,menuname as text,'true' as leaf from T_Menu where pid=#{pid}")
    List<Map<String, String>> getLeafMenusByPid(@Param("pid") String pid);

    @Select("select mid,viewid,viewname,viewcontroller from T_Menu where mid=#{mid}")
    Map<String,String> getView(@Param("mid")String mid);
}
