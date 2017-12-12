package com.we.ws.admin.flow;

import com.we.ws.admin.flow.json.JsonParseModel;
import com.we.ws.admin.flow.json.Path;
import com.we.ws.admin.flow.json.State;
import com.we.ws.admin.flow.json.Text;
import com.we.ws.admin.flow.match.ReadWSDL.DbManager;
import com.we.ws.admin.flow.match.ReadWSDL.WsdlDAO;
import com.we.ws.admin.flow.match.SemanticSimilarity.Lin;
import com.we.ws.admin.flow.match.ServiceGraph.ServiceNode;
import com.we.ws.admin.flow.match.ServiceGraph.ServiceSimilarity;
import com.we.ws.common.util.JsonUtils;
import org.apache.lucene.search.similarities.Similarity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by xuxyu on 2017/8/30.
 */
public class StateMatch {

    public static HashMap<String, State> getInvokeState(JsonParseModel model) {
        //获取所有invoke state

        HashMap<String, State> stateMap = new HashMap<>();
        Map<String, State> map = model.getStates();
        for (Map.Entry<String, State> entry : map.entrySet()) {
            State tmpState = entry.getValue();
            if (tmpState.getType().equals("invoke"))
                stateMap.put(entry.getKey(), entry.getValue());
        }
        return stateMap;

    }


    //Out Interface : get new paths
    public static HashMap<String, Path> getRectPathsByStateTempplates(HashMap<String, State> statesTemplates,
                                                                      JsonParseModel model, ServiceSimilarity ss) throws Exception {
        //单个state匹配首尾path及StringStateMap


        HashMap<String, Path> result = new HashMap<>();

        //所有single invoke rect相关的path，from head to tail
        //HashMap<rectName, TreeMap<pathName, path>> path.from == rectName || path.to == rectName
        HashMap<String, LinkedHashMap<String, Path>> statesHeadTailPaths = getRectsPaths(statesTemplates, model);

        //所有single invoke rect匹配到的服务组合
        //HashMap<rectName, TreeMap<serializeRectName(altered by rectName), State>
        HashMap<String, TreeMap<String, State>> stateMap = getStringStateMap(statesTemplates, ss);


        //根据state.rectName得到stateComposition and states Head&Tail paths
        for (String rectName : stateMap.keySet()) {

            TreeMap<String, State> stateComposition = stateMap.get(rectName);
            LinkedHashMap<String, Path> statesToPaths = statesHeadTailPaths.get(rectName);

            int statesSize = stateComposition.size();
            if (statesSize == 1) {
                result.putAll(statesToPaths);
                continue;
            }

            HashMap<String, Path> tempPaths = new HashMap<>();

            String prevFromRect = null;

            Path startPath = new Path();
            Path endPath = new Path();

            int num = 0;

            String startPathName = null;
            String endPathName = null;
            for (String key : statesToPaths.keySet()) {
                if (num == 0) {
                    startPath = statesToPaths.get(key);
                    startPath.setText(new Text());
                    startPathName = key;
                    num++;
                    continue;
                }
                if (num == statesToPaths.size() - 1) {
                    endPath = statesToPaths.get(key);
                    endPathName = key;
                    break;
                }

            }


            int i = 0;

            for (String rectKey : stateComposition.keySet()) {
                if (i == 0) {
                    //tempPaths first path change path.to from rectNameX to new rectNameX.X
                    startPath.setTo(stateComposition.firstKey());
                    tempPaths.put(startPathName, startPath);
                    i++;
                    prevFromRect = startPath.getTo();
                    continue;
                }

                Path path = new Path();
                path.setFrom(prevFromRect);
                path.setTo(rectKey);
                path.setText(new Text());
                prevFromRect = rectKey;
                tempPaths.put(startPathName + i, path);
            }

            //tempPaths first path change path.from from rectNameX to new rectNameX.X
            endPath.setFrom(stateComposition.lastKey());
            tempPaths.put(endPathName, endPath);

            result.putAll(tempPaths);
        }
        return result;
    }

    public static HashMap<String, LinkedHashMap<String, Path>> getRectsPaths(HashMap<String, State> stateMap, JsonParseModel model) {
        //根据stateTemplate的rect名称获得pathMap:From and To
        //返回stateTemplate.rectName, TreeMap<String, Path> Head and tail

        HashMap<String, LinkedHashMap<String, Path>> result = new HashMap<>();

        LinkedHashMap<String, Path> singleStatePath;

        Map<String, Path> pathMap = model.getPaths();


        for (String key : stateMap.keySet()) {
            singleStatePath = new LinkedHashMap<>();
            for (String rect : stateMap.keySet())
                for (Map.Entry<String, Path> entry : pathMap.entrySet()) {
                    if (rect.equals(entry.getValue().getTo()))
                        singleStatePath.put(entry.getKey(), entry.getValue());
                }

            for (String rect : stateMap.keySet())
                for (Map.Entry<String, Path> entry : pathMap.entrySet()) {
                    if (rect.equals(entry.getValue().getFrom()))
                        singleStatePath.put(entry.getKey(), entry.getValue());
                }

            result.put(key, singleStatePath);
        }
        return result;
    }


    //Out Interface : get new states by state template
    public static HashMap<String, TreeMap<String, State>> getStringStateMap(HashMap<String, State> map, ServiceSimilarity ss)
            throws Exception {
        //  根据States生成组合state的序列
        //  state.rectName, TreeMap<rectNameNo, State>
        HashMap<String, TreeMap<String, State>> result = new HashMap<>();
        for (String key : map.keySet()) {
            LinkedList<State> states = statesFromServiceNodes(map.get(key), ss);
            TreeMap<String, State> stateComposition = new TreeMap();
            if (states.size() == 1) {
                stateComposition.put(key, states.get(0));
            } else {
                for (int i = 0; i < states.size(); i++) {
                    String newKey = key + (i + 1);
                    stateComposition.put(newKey, states.get(i));
                }
            }
            result.put(key, stateComposition);
        }

        return result;
    }

    public static LinkedList<State> statesFromServiceNodes(State stateTemplate, ServiceSimilarity ss)
            throws Exception {

        // 根据state template生成服务组合，继而生成state组合

        LinkedList<ServiceNode> serviceList = matchByState(stateTemplate, ss);
/*        if(serviceList.size() == 1){
            serviceList.get(0).getWSDL()
        }*/
        LinkedList<State> stateList = new LinkedList<>();
        String type = stateTemplate.getType();


//        Map<String, Map<String, String>> props = stateTemplate.getProps();

        for (ServiceNode serviceNode : serviceList) {
            //serviceid
            String url = serviceNode.getWSDL().toString();
            String serviceId = String.valueOf(WsdlDAO.searchSidByUrl(url));
            Map<String, Map<String, String>> serviceProps = new LinkedHashMap<>();
            Map<String, String> serviceIdMap = new HashMap<>();
            serviceIdMap.put("value", serviceId.trim());
            serviceProps.put("serviceId", serviceIdMap);

            //text  serviceName
            String serviceName = serviceNode.getServiceName();
            Map<String, String> textMap = new HashMap<>();
            textMap.put("value", serviceName.trim());
            serviceProps.put("text", textMap);

            //input
            ArrayList<String> inputs = serviceNode.getInputsParmeters();
            Map<String, String> inputMap = new HashMap<>();
            String inputJson = "";
            for (String input : inputs)
                inputJson += input.trim() + ":,";
            inputJson = inputJson.substring(0, inputJson.length() - 1);
            inputMap.put("value", inputJson);
            serviceProps.put("input", inputMap);

            //output
            ArrayList<String> outputs = serviceNode.getOutputsParmeters();
            Map<String, String> outputMap = new HashMap<>();
            String outputJson = "";
            for (String output : outputs)
                outputJson += output.trim() + ":,";
            outputJson = outputJson.substring(0, outputJson.length() - 1);
            outputMap.put("value", outputJson);
            serviceProps.put("output", outputMap);

            Map<String, Integer> attr = new HashMap<>();
            Map<String, String> text = new HashMap<>();
            attr.putAll(stateTemplate.getAttr());
            text.putAll(stateTemplate.getText());
            State tmpState = new State(type, serviceProps, attr, text);
            stateList.add(tmpState);
        }
        return stateList;
    }

    public static LinkedList<ServiceNode> matchByState(State stateTemplate, ServiceSimilarity ss)
            throws Exception {
        //  根据state template的input、output匹配服务，返回服务组合
        Map<String, Map<String, String>> map = stateTemplate.getProps();
        ArrayList<String> inputs = getParam(map, "input");
        ArrayList<String> outputs = getParam(map, "output");
        String description = getParam(map, "desc").get(0);
        ArrayList<LinkedList<ServiceNode>> list = ss.getSelectedCompositions(inputs, outputs, description);
        return list.get(0);
    }

    public static ArrayList<String> getParam(Map<String, Map<String, String>> props, String param) {
        //  获取state的props inputs outputs供搜索路径用

        ArrayList<String> paramList = new ArrayList<>();

        if (param.equals("input") || param.equals("output")) {
            for (Map.Entry<String, Map<String, String>> entry : props.entrySet()) {
                if (entry.getKey().equals(param)) {
                    Map<String, String> prop = entry.getValue();
                    String paramString = prop.get("value");
                    String[] params = paramString.split(",");
                    for (String str : params)
                        paramList.add(str.substring(0, str.length() - 1));
                }
            }
        } else {
            for (Map.Entry<String, Map<String, String>> entry : props.entrySet()) {
                if (entry.getKey().equals(param)) {
                    Map<String, String> prop = entry.getValue();
                    String paramString = prop.get("value");
                    paramList.add(paramString);
                }
            }
        }

        return paramList;
    }

    public static String jsonAfterComposition(String json, ServiceSimilarity ss) throws Exception{

        JsonParseModel model = JsonUtils.objectFromJson(json, JsonParseModel.class);

        HashMap<String, State> map = getInvokeState(model);

        //model's states
        Map<String, State> statesMap = model.getStates();
        //newStatesMap all of the states compositions identified by task.rect
        HashMap<String, TreeMap<String, State>> newStatesMap = getStringStateMap(map, ss);
        for(String key : newStatesMap.keySet()){
            //tempMap is the states composition of task rect
            TreeMap<String, State> tempMap = newStatesMap.get(key);
            //if tempMap.size is 1, rect was not changed, put the tmpMap
            if(tempMap.size() == 1)
                statesMap.putAll(tempMap);
            else{
                //if tempMap.size > 1, rects' name was changed all, so delete the origin rect, the put all the changed rects
                statesMap.remove(key);
                statesMap.putAll(tempMap);
            }
        }
        model.setStates(statesMap);

        //origin pathMap of model
        Map<String, Path> pathMap = model.getPaths();
        //pathMap is the paths added by states composition
        HashMap<String, Path> newPathsMap = getRectPathsByStateTempplates(map, model, ss);
        //since origin path has been modified without the modification of path name, so add them to the origin paths directly
        pathMap.putAll(newPathsMap);
        model.setPaths(pathMap);

        String newJson = JsonUtils.jsonFromObject(model);
        return newJson;

    }

    public static void main(String[] args) throws Exception {
        String json = "{\n" +
                "\"states\":{\n" +
                "\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":380,\"y\":29,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}},\n" +
                "\"rect2\":{\"type\":\"end\",\"text\":{\"text\":\"结束\"},\"attr\":{\"x\":381,\"y\":448,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"结束\"}}},\n" +
                "\n" +
                "\n" +
                "\"rect3\":{\"type\":\"invoke\",\"text\":{\"text\":\"\"},\"attr\":{\"x\":381,\"y\":227,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"\"},\"text\":{\"value\":\"\"},\"desc\":{\"value\":\"This service provide the accommodation which has the sports of hiking and surfing.\"},\"input\":{\"value\":\"hiking:,surfing:\"},\"output\":{\"value\":\"accommodation:\"}}},\n" +
                "\n" +
                "\n" +
                "\"rect5\":{\"type\":\"receive\",\"text\":{\"text\":\"接收\"},\"attr\":{\"x\":380,\"y\":126,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"接收\"},\"desc\":{\"value\":\"输入\"},\"input\":{\"value\":\"mobile:\"}}}},\n" +
                "\n" +
                "\"paths\":{\n" +
                "\"path6\":{\"from\":\"rect1\",\"to\":\"rect5\",\"dots\":[],\"text\":{\"text\":\"TO 接收\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\n" +
                "\"path7\":{\"from\":\"rect5\",\"to\":\"rect3\",\"dots\":[],\"text\":{\"text\":\"TO 手机\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\n" +
                "\"path10\":{\"from\":\"rect3\",\"to\":\"rect2\",\"dots\":[],\"text\":{\"text\":\"TO 结束\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}}},\n" +
                "\n" +
                "\"props\":{\"props\":{\"name\":{\"value\":\"新建流程\"},\"flowid\":{\"value\":\"标识\"},\"tagid\":{\"value\":\"1\"},\"desc\":{\"value\":\"描述\"}}}\n" +
                "}\n";


        ServiceSimilarity ss = new ServiceSimilarity("http://127.0.0.1/domains/1.1/travel/");


        System.out.println(jsonAfterComposition(json, ss));

    }

}
