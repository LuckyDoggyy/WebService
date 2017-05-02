package com.we.ws.admin.flow;

import com.we.ws.admin.domain.Flow;
import com.we.ws.admin.flow.json.FlowProp;
import com.we.ws.admin.flow.json.JsonParseModel;
import com.we.ws.admin.flow.json.Path;
import com.we.ws.admin.flow.json.State;
import com.we.ws.admin.flow.node.*;
import com.we.ws.common.data.Pair;
import com.we.ws.common.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class FlowParser {

    public static Node parse(String json) throws Exception {
        JsonParseModel model = JsonUtils.objectFromJson(json, JsonParseModel.class);
        Node head = null;
        Map<String, Node> parsedNodes = new HashMap<>();
        Map<String, State> states = model.getStates();
        Map<String, Path> pathes = model.getPaths();
        for (Map.Entry<String, Path> entry : pathes.entrySet()) {
            Path path = entry.getValue();
            State fromState = states.get(path.getFrom());
            State toState = states.get(path.getTo());
            Node toNode = getOrGenNode(toState, path.getTo(), parsedNodes);
            Node fromNode = getOrGenNode(fromState, path.getFrom(), parsedNodes);
            if (fromNode instanceof If) {
                handleIfNode(fromNode, toNode, path);
            } else if (fromNode instanceof Start) {
                if (head == null) {
                    head = fromNode;
                } else {
                    throw new FlowException("有多个start节点");
                }
                fromNode.setNext(toNode);
            } else {
                fromNode.setNext(toNode);
            }
        }
        return head;
    }

    public static Pair<Node, Flow> parseWithFlow(String json) throws Exception {
        JsonParseModel model = JsonUtils.objectFromJson(json, JsonParseModel.class);
        Node head = null;
        Map<String, Node> parsedNodes = new HashMap<>();
        Map<String, State> states = model.getStates();
        Map<String, Path> pathes = model.getPaths();
        for (Map.Entry<String, Path> entry : pathes.entrySet()) {
            Path path = entry.getValue();
            State fromState = states.get(path.getFrom());
            State toState = states.get(path.getTo());
            Node toNode = getOrGenNode(toState, path.getTo(), parsedNodes);
            Node fromNode = getOrGenNode(fromState, path.getFrom(), parsedNodes);
            if (fromNode instanceof If) {
                handleIfNode(fromNode, toNode, path);
            } else if (fromNode instanceof Start) {
                if (head == null) {
                    head = fromNode;
                } else {
                    throw new FlowException("有多个start节点");
                }
                fromNode.setNext(toNode);
            } else {
                fromNode.setNext(toNode);
            }
        }


        FlowProp flowProp = model.getProps();
        Flow flow = new Flow(flowProp.getName(), flowProp.getFlowid(), flowProp.getDesc(), json);
        return Pair.of(head, flow);
    }


    private static Node getOrGenNode(State state, String nodeName, Map<String, Node> parsedNodes) {
        Node node;
        if ((node = parsedNodes.get(nodeName)) == null) {
            node = generateNodebyState(state);
            parsedNodes.put(nodeName, node);
        }
        return node;
    }

    private static void handleIfNode(Node fromNode, Node toNode, Path path) {
        If ifNode = (If) fromNode;
        if ("true".equals(path.getText())) {
            ifNode.setTrueNode(toNode);
        } else {
            ifNode.setFalseNode(toNode);
        }
    }

    private static boolean pathIsBoolean(Path path) {
        return "true".equals(path.getText()) | "false".equals(path.getText());
    }

    public static final String START = "start";
    public static final String TASK = "task";
    public static final String END = "end";
    public static final String IF = "fork";

    private static Node generateNodebyState(State state) {
        switch (state.getType()) {
            case START:
                return new Start();
            case END:
                return new End();
            case TASK:
                Map<String, Map<String, String>> props = state.getProps();
                String taskremark = props.get("taskremark").get("value");
                String taskcategory = props.get("taskcategory").get("value");
                return new Task(taskremark, taskcategory);
            case IF:
                String judgeExpress = state.getProps().get("judge").get("value");
                return new If(judgeExpress);
        }
        return null;
    }
}
