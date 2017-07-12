package com.we.ws.admin.service;

import com.we.ws.admin.domain.FlowTag;
import com.we.ws.admin.domain.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author twogoods
 * @since 2017-07-11
 */
public interface FlowTagService {

    boolean insertTag(Tag tag);

    boolean updateTag(Tag tag);

    boolean enableTag(String autoids);

    boolean disableTag(String autoids);

    List<Tag> listTags(String pid);

    List<Map<String, Object>> getTagOption();

    boolean insertFlowTag(FlowTag flowTag);

    boolean batchInsertFlowTag(String tagids, String flowid);

    boolean enableFlowTag(String autoids);

    boolean disableFlowTag(String autoids);

    List<FlowTag> listFlowTags(String flowid);
}
