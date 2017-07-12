package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.FlowTag;
import com.we.ws.admin.domain.Tag;
import com.we.ws.admin.mapper.FlowTagMapper;
import com.we.ws.admin.mapper.TagMapper;
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
    private TagMapper tagMapper;

    @Autowired
    private FlowTagMapper flowTagMapper;

    @Override
    public boolean insertTag(Tag tag) {
        return tagMapper.insert(tag) == 1;
    }

    @Override
    public boolean updateTag(Tag tag) {
        return tagMapper.update(tag) == 1;
    }

    @Override
    public boolean enableTag(String autoids) {
        return tagMapper.setTagState(autoids.split(","), 0) > 0;
    }

    @Override
    public boolean disableTag(String autoids) {
        return tagMapper.setTagState(autoids.split(","), 1) > 0;
    }

    @Override
    public List<Tag> listTags(String pid) {
        return tagMapper.listTags(pid);
    }

    @Override
    public List<Map<String, Object>> getTagOption() {
        return tagMapper.getTagOption();
    }

    @Override
    public boolean insertFlowTag(FlowTag flowTag) {
        return flowTagMapper.insert(flowTag) == 1;
    }

    @Override
    public boolean batchInsertFlowTag(String tagids, String flowid) {
        return flowTagMapper.batchInsertFlowTag(tagids.split(","), flowid) > 0;
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
    public List<FlowTag> listFlowTags(String flowid) {
        return flowTagMapper.listFlowTags(flowid);
    }
}
