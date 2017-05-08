package com.we.ws.admin.flow.node;

import com.we.ws.common.data.Pair;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class If extends Node {

    private String judgeExpress;

    private Node trueNode;
    private Node falseNode;

    public If(String judgeExpress) {
        this.judgeExpress = judgeExpress;
    }

    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) {
        return null;
    }

    public Node getTrueNode() {
        return trueNode;
    }

    public void setTrueNode(Node trueNode) {
        this.trueNode = trueNode;
    }

    public Node getFalseNode() {
        return falseNode;
    }

    public void setFalseNode(Node falseNode) {
        this.falseNode = falseNode;
    }

    public String getJudgeExpress() {
        return judgeExpress;
    }

    public void setJudgeExpress(String judgeExpress) {
        this.judgeExpress = judgeExpress;
    }
}
