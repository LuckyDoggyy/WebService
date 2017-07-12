package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.FlowTag;
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
public interface FlowTagMapper {

    @Insert("insert into T_FlowTag (flowid,tagid) values(#{flowid},#{tagid})")
    int insert(FlowTag flowTag);

    int setFlowTagState(@Param("autoids") String[] autoids, @Param("state") int state);

    @Select("select f.autoid,f.flowid,f.tagid,t.tagName from T_FlowTag f,T_Tag t where f.flowid=#{flowid} and f.tagid=t.autoid and f.state=0")
    List<FlowTag> listFlowTags(@Param("flowid") String flowid);

    int batchInsertFlowTag(@Param("tagids")String[] tagids,@Param("flowid") String flowid);

}