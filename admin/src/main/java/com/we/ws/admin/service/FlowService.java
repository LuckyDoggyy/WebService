package com.we.ws.admin.service;

import com.we.ws.admin.domain.Flow;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public interface FlowService {
    boolean add(Flow flow);

    boolean update(Flow flow);

    List<Flow> listFlows(String flowid, String flowname);

    boolean deleteFlows(String autoids);

    boolean enableFlows(String autoids);

    boolean unableFlows(String autoids);

    String getJsonById(String autoid);

    Map<String, Object> call(String flowId, String callParams) throws Exception;

}
