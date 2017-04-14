package com.we.ws.admin.service;

import com.we.ws.admin.domain.Flow;

import java.util.List;

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

    String getJsonById(String autoid);
}
