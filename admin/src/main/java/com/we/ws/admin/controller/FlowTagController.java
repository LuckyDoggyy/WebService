package com.we.ws.admin.controller;

import com.we.ws.admin.domain.FlowTag;
import com.we.ws.admin.domain.Tag;
import com.we.ws.admin.service.FlowTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author twogoods
 * @since 2017-07-11
 */
@Controller
@RequestMapping("/flowTag/")
public class FlowTagController {
    private Logger log = LoggerFactory.getLogger(FlowTagController.class);

    @Autowired
    private FlowTagService flowTagService;

    @RequestMapping("listTags")
    @ResponseBody
    public Map<String, Object> listTags(String pid) throws Exception {
        Map<String, Object> map = new HashMap();
        List<Tag> list = flowTagService.listTags(pid);
        map.put("rows", list);
        map.put("totalCount", list.size());
        return map;
    }

    @RequestMapping("getTagOption")
    @ResponseBody
    public List<Map<String, Object>> getTagOption() throws Exception {
        return flowTagService.getTagOption();
    }

    @RequestMapping("addTag")
    @ResponseBody
    public Map<String, Object> addTag(Tag tag) {
        Map<String, Object> map = new HashMap();
        if (flowTagService.insertTag(tag)) {
            map.put("success", true);
            map.put("obj", "添加成功");
        } else {
            map.put("success", false);
            map.put("obj", "添加失败");
        }
        return map;
    }

    @RequestMapping("updateTag")
    @ResponseBody
    public Map<String, Object> updateTag(Tag tag) {
        Map<String, Object> map = new HashMap();
        if (flowTagService.updateTag(tag)) {
            map.put("success", true);
            map.put("obj", "更新成功");
        } else {
            map.put("success", false);
            map.put("obj", "更新失败");
        }
        return map;
    }

    @RequestMapping("disableTag")
    @ResponseBody
    public Map<String, Object> disableTag(String autoids) {
        Map<String, Object> map = new HashMap();
        if (flowTagService.disableTag(autoids)) {
            map.put("success", true);
            map.put("obj", "删除成功");
        } else {
            map.put("success", false);
            map.put("obj", "删除失败");
        }
        return map;
    }

    @RequestMapping("listFlowTags")
    @ResponseBody
    public Map<String, Object> listFlowTags(String flowid) throws Exception {
        Map<String, Object> map = new HashMap();
        List<FlowTag> list = flowTagService.listFlowTags(flowid);
        map.put("rows", list);
        map.put("totalCount", list.size());
        return map;
    }


    @RequestMapping("batchAddFlowTags")
    @ResponseBody
    public Map<String, Object> batchAddFlowTags(String tagids, String flowid) {
        Map<String, Object> map = new HashMap();
        if (flowTagService.batchInsertFlowTag(tagids, flowid)) {
            map.put("success", true);
            map.put("obj", "添加成功");
        } else {
            map.put("success", false);
            map.put("obj", "添加失败");
        }
        return map;
    }

    @RequestMapping("batchDelFlowTags")
    @ResponseBody
    public Map<String, Object> batchDelFlowTags(String autoids) {
        Map<String, Object> map = new HashMap();
        if (flowTagService.disableFlowTag(autoids)) {
            map.put("success", true);
            map.put("obj", "删除成功");
        } else {
            map.put("success", false);
            map.put("obj", "删除失败");
        }
        return map;
    }
}
