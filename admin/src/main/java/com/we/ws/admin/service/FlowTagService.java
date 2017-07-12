package com.we.ws.admin.service;

import com.we.ws.admin.domain.FlowTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author twogoods
 * @since 2017-07-11
 */
public interface FlowTagService {

    boolean insert(FlowTag tag);

    boolean update(FlowTag tag);

    boolean enableFlowTag(String autoids);

    boolean disableFlowTag(String autoids);

    List<FlowTag> listFlowTags();

    List<Map<String, Object>> getTagOption();
}
