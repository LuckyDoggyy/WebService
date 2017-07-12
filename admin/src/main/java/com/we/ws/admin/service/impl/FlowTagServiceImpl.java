package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.FlowTag;
import com.we.ws.admin.mapper.FlowTagMapper;
import com.we.ws.admin.service.FlowTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author twogoods
 * @since 2017-07-11
 */
@Service
public class FlowTagServiceImpl implements FlowTagService {

    @Autowired
    private FlowTagMapper flowTagMapper;

    @Override
    public boolean insert(FlowTag tag) {
        return flowTagMapper.insert(tag) == 1;
    }

    @Override
    public boolean update(FlowTag tag) {
        return flowTagMapper.update(tag) == 1;
    }

    @Override
    public boolean enableFlowTag(String autoids) {

        return flowTagMapper.setFlowTagState(autoids.split(","), 0) > 0;
    }

    @Override
    public boolean disableFlowTag(String autoids) {
        return flowTagMapper.setFlowTagState(autoids.split(","), 1) > 0;
    }

    @Override
    public List<FlowTag> listFlowTags() {
        return flowTagMapper.listFlowTags();
    }

    @Override
    public List<Map<String, Object>> getTagOption() {
        return flowTagMapper.getTagOption();
    }
}
