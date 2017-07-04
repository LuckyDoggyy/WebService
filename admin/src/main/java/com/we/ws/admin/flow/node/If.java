package com.we.ws.admin.flow.node;

import com.we.ws.admin.flow.FlowException;
import com.we.ws.admin.flow.json.State;
import com.we.ws.common.data.Pair;
import com.we.ws.common.data.Tuple;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class If extends Node {
    private Map<String, Object> input;
    private String judgeExpress;

    private Node trueNode;
    private Node falseNode;

    private If(String name, String desc, String input, String judgeExpress) {
        super(name, desc);
        this.input = parseInOrOut(input);
        this.judgeExpress = judgeExpress;
    }


    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) throws FlowException {
        //TODO 暂只能单个判断表达式
        Tuple<String, String, Conditions.Condition> judge = parserExpress();
        String v1 = judge.getL();
        String v2 = judge.getM();
        boolean flag = false;
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            if (entry.getKey().equals(v1)) {
                flag = judge.getR().judge(v2, param.get(entry.getKey()).toString());
                break;
            } else if (entry.getKey().equals(v2)) {
                flag = judge.getR().judge(v1, param.get(entry.getKey()).toString());
                break;
            }
        }
        if (flag) {
            return Pair.of(trueNode, param);
        }
        return Pair.of(falseNode, param);
    }

    private Tuple<String, String, Conditions.Condition> parserExpress() throws FlowException {
        for (Conditions.Condition condition : Conditions.all) {
            if (condition.containedIn(judgeExpress)) {
                return Tuple.of(condition.parseVariable(judgeExpress), condition);
            }
        }
        throw new FlowException("无法匹配任何的判断条件");
    }

    public static If of(State state) {
        Map<String, Map<String, String>> props = state.getProps();
        String name = props.get("text").get("value");
        String desc = props.get("desc").get("value");
        String input = props.get("input").get("value");
        String judgeExpress = props.get("judge").get("value");
        return new If(name, desc, input, judgeExpress);
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
}
