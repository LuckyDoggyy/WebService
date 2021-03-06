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

    List<Flow> listFlows(String flowid, String flowname, String uid);

    boolean deleteFlows(String autoids);

    boolean enableFlows(String autoids);

    boolean unableFlows(String autoids);

    boolean checkEnable(String autoid);

    String getJsonById(String autoid);

    Flow getById(String autoid);

    Map<String, Object> call(String autoId, String callParams) throws Exception;

    boolean unableUserInFlow(String uids, String flowid);

    boolean enableUserInFlow(String uids, String flowid);

}
