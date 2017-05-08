package com.we.ws.admin.flow.node;

import com.we.ws.admin.flow.json.State;
import com.we.ws.common.data.Pair;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class Task extends Node {
    private String taskremark;
    private String taskcategory;

    public Task(String taskremark, String taskcategory) {
        this.taskremark = taskremark;
        this.taskcategory = taskcategory;
    }

    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) {
        //TODO 服务调用
        return Pair.of(next, null);
    }

    public static Task of(State state) {
        Map<String, Map<String, String>> props = state.getProps();
        String taskremark = props.get("taskremark").get("value");
        String taskcategory = props.get("taskcategory").get("value");
        return new Task(taskremark, taskcategory);
    }

    public String getTaskremark() {
        return taskremark;
    }

    public void setTaskremark(String taskremark) {
        this.taskremark = taskremark;
    }

    public String getTaskcategory() {
        return taskcategory;
    }

    public void setTaskcategory(String taskcategory) {
        this.taskcategory = taskcategory;
    }
}
