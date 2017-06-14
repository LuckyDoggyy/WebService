package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Menu;
import org.apache.ibatis.annotations.*;

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
            " m.pid = #{pid} and m.state=0 and r.rid in ( select DISTINCT rid from T_UserRole where uid = #{uid}) and r.mid=m.mid")
    List<Map<String, String>> getLeafMenuWithCheck(@Param("pid") String pid, @Param("uid") String uid);

    @Select("SELECT DISTINCT mid AS id, menuname AS text, 'true' AS leaf FROM T_Menu WHERE pid = #{pid} and state=0")
    List<Map<String, String>> getLeafMenu(@Param("pid") String pid);

    @Select("select * from T_Menu where pid='root'")
    List<Menu> getFirstLayerMenu();

    @Select("select count(mid) from T_RoleMenu where rid in (select rid from T_UserRole where uid=#{uid}) and mid like #{mid}")
    int checkFirstLayerMenu(@Param("uid") String uid, @Param("mid") String mid);

    @Select("SELECT DISTINCT mid AS id, menuname AS text, 'false' AS leaf FROM T_Menu WHERE pid = #{pid} and state=0")
    List<Map<String, Object>> getSecondLayerMenu(@Param("pid") String firstLayerMid);

    @Select("SELECT DISTINCT mid AS id, menuname AS text, 'false' AS leaf,'true' as checked FROM T_Menu WHERE pid = #{pid} and state=0")
    List<Map<String, Object>> getSecondLayerMenuForList(@Param("pid") String firstLayerMid);

    @Select("select mid as id ,menuname as text,'true' as leaf from T_Menu where pid=#{pid} and state=0")
    List<Map<String, String>> getLeafMenusByPid(@Param("pid") String pid);

    @Select("select mid,viewid,viewname,viewcontroller,fid from T_Menu where mid=#{mid} and state=0")
    Map<String, Object> getView(@Param("mid") String mid);

    List<Menu> listMenu(@Param("mid") String mid);

    @Select("select mid,menuname from T_Menu where state=0 and pid in (select mid from T_Menu where pid='root') or pid='null' or pid='root'")
    List<Menu> getNodes();

    @Insert("insert into T_Menu (mid,menuname,pid,viewid,viewname,viewcontroller,fid) values(#{mid},#{menuname},#{pid},#{viewid},#{viewname},#{viewcontroller},#{fid})")
    int insert(Menu menu);

    int deleteMenus(@Param("autoids") String[] autoids);

    @Update("update T_Menu set menuname=#{menuname},pid=#{pid},viewid=#{viewid},viewname=#{viewname},viewcontroller=#{viewcontroller},fid=#{fid} where autoid=#{autoid}")
    int updateMenu(Menu menu);

    @Select("select count(*) from T_Menu where state=0 and mid=#{id} or pid=#{id}")
    int checkIdExist(String id);

}
