package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.FlowTag;
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
public interface FlowTagMapper {

    @Insert("insert into T_FlowTag (tagName) values(#{tagName})")
    int insert(FlowTag tag);

    @Update("update T_FlowTag set tagName=#{tagName},orderIndex=#{orderIndex} where autoid=#{autoId}")
    int update(FlowTag tag);

    int setFlowTagState(@Param("autoids") String[] autoids, @Param("state") int state);

    @Select("select autoid,tagName,orderIndex,state from T_FlowTag where state=0 order by orderIndex")
    List<FlowTag> listFlowTags();

    @Select("select autoid as value,tagName as name from T_FlowTag where state=0")
    List<Map<String, Object>> getTagOption();

}