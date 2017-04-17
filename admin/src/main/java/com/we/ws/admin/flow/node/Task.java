package com.we.ws.admin.flow.node;

import com.we.ws.common.data.Pair;

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
    public Pair<Node, Object> handle(Object param) {
        //TODO 服务调用
        return Pair.of(next, null);
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
