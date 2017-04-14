package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Flow;
import com.we.ws.admin.mapper.FlowMapper;
import com.we.ws.admin.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private FlowMapper flowMapper;

    @Override
    public boolean add(Flow flow) {
        flowMapper.insertReturnKey(flow);
        return true;
    }

    @Override
    public boolean update(Flow flow) {
        return flowMapper.update(flow) == 1;
    }

    @Override
    public List<Flow> listFlows(String flowid, String flowname) {
        //TODO like
        return flowMapper.listFlows(flowid,flowname);
    }

    @Override
    public boolean deleteFlows(String autoids) {
        return flowMapper.deleteFlows(autoids.split(",")) > 0;
    }

    @Override
    public String getJsonById(String autoid) {
        return flowMapper.getJsonById(autoid);
    }
}
