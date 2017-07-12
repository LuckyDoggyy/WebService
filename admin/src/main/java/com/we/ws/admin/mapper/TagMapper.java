package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-07-11
 */
@Mapper
public interface TagMapper {

    @Insert("insert into T_Tag (tagName,pid) values(#{tagName},#{pid})")
    int insert(Tag tag);

    @Update("update T_Tag set tagName=#{tagName},orderIndex=#{orderIndex},pid=#{pid} where autoid=#{autoId}")
    int update(Tag tag);

    int setTagState(@Param("autoids") String[] autoids, @Param("state") int state);

    List<Tag> listTags(@Param("pid") String pid);

    @Select("select autoid as value,tagName as name from T_Tag where state=0")
    List<Map<String, Object>> getTagOption();

}