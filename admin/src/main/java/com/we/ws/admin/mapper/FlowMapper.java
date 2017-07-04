package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Flow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
@Mapper
public interface FlowMapper {
    void insertReturnKey(Flow flow);

    //@Update("update T_Flow set flowid=#{flowid},flowname=#{flowname},description=#{description},input=#{input},flowjson=#{flowjson} where autoid=#{autoid}")
    @Update("update T_Flow set input=#{input},flowjson=#{flowjson} where autoid=#{autoid}")
    int update(Flow flow);

    int setFlowState(@Param("autoids") String[] autoids, @Param("state") int state);

    @Select("select state from T_Flow where autoid=#{autoid}")
    int getState(@Param("autoid") String autoid);

    List<Flow> listFlows(@Param("flowid") String flowid, @Param("flowname") String flowname, @Param("uid") String uid);

    @Select("select flowjson from T_Flow where autoid=#{autoid}")
    String getJsonById(@Param("autoid") String autoid);

    @Select("select autoid,flowid,flowname,description,input from T_Flow where autoid=#{autoid}")
    Flow getById(@Param("autoid") String autoid);

}