package com.we.ws.admin.flow.match.ServiceGraph;

import com.we.ws.admin.flow.match.SemanticSimilarity.KuhnMunkres;
import com.we.ws.admin.flow.match.SemanticSimilarity.TextSimilarity;

import java.util.*;


/**
 * Created by xuxyu on 2017/8/14.
 */
public class ServiceSimilarity {

    ServiceGraph sg;
    TextSimilarity ts;
    KuhnMunkres km;

    public ServiceSimilarity(String htmlUrl) throws Exception{
        sg = new ServiceGraph(htmlUrl);
        ts = new TextSimilarity();
        km = new KuhnMunkres();

    }

    public HashMap<ServiceNode, Double> searchByInput(ServiceGraph sg, ArrayList<String> in){
        in.removeAll(Collections.singleton(null));
        double similarity = 0;
        HashMap<ServiceNode, Double> map = new HashMap<ServiceNode, Double>();
        for (int i = 0; i < sg.serviceNodes.size(); i++) {
            ServiceNode serviceNode = sg.serviceNodes.get(i);
            similarity = addInputNode(serviceNode, in);
            if(similarity > 0.9){
                map.put(serviceNode, similarity);
            }
        }
        return map;
    }

    public double addInputNode(ServiceNode serviceNode, ArrayList<String> in){
        in.removeAll(Collections.singleton(null));
        ArrayList<String> serviceInputs = serviceNode.getInputsParmeters();
        int length = serviceInputs.size() > in.size() ? serviceInputs.size() : in.size();
        double [][] value = setParameterServiceMatrix(in, serviceInputs);
        double [] xWeight = ts.formatText(in, serviceInputs);
        km.setN(length);
        km.setValue(value);
        km.setxWeight(xWeight);
        double similarity = km.getKM(1);
        serviceNode.getInputsParmeters().removeAll(Collections.singleton(null));
        return similarity;
    }

    public HashMap<ServiceNode, Double> searchByOutput(ServiceGraph sg, ArrayList<String> out){
        out.removeAll(Collections.singleton(null));
        double similarity = 0;
        HashMap<ServiceNode, Double> map = new HashMap<ServiceNode, Double>();
        for (int i = 0; i < sg.serviceNodes.size(); i++) {
            ServiceNode serviceNode = sg.serviceNodes.get(i);
            similarity = addOutputNode(serviceNode, out);
            if(similarity > 0.9){
                map.put(serviceNode, similarity);
            }
        }
        return map;
    }

    public double addOutputNode(ServiceNode serviceNode, ArrayList<String> out){//根据相似度计算节点与输出参数匹配度
        out.removeAll(Collections.singleton(null));
        ArrayList<String> serviceOutputs = serviceNode.getOutputsParmeters();
        int length = serviceOutputs.size() > out.size() ? serviceOutputs.size() : out.size();
        double [][] value = setParameterServiceMatrix(out, serviceOutputs);
        double [] xWeight = ts.formatText(out, serviceOutputs);
        km.setN(length);
        km.setValue(value);
        km.setxWeight(xWeight);
        serviceNode.getOutputsParmeters().removeAll(Collections.singleton(null));
        return km.getKM(1);
    }

    public double [][] setParameterServiceMatrix(ArrayList<String> parameters, ArrayList<String> serviceParameters){
        double[][] value = new double[100][100];
        for (int i = 0; i < parameters.size(); i++)
            for (int j = 0; j < serviceParameters.size(); j++){
                value[i][j] = getParameterSimilarity(parameters.get(i), serviceParameters.get(j));
            }
        return value;
    }


    public double getParameterSimilarity(String text1, String text2){
        ArrayList<String> text1List = formatParameter(text1);
        ArrayList<String> text2List = formatParameter(text2);
        int length = text1List.size() > text2List.size() ? text1List.size() : text2List.size();
        double [] xWeight = ts.formatText(text1List,text2List);
        double [][] value = ts.setValueMatrix(text1List, text2List);
        km.setN(length);
        km.setValue(value);
        km.setxWeight(xWeight);
        return km.getKM(1);
    }


    public ArrayList<String> formatParameter(String parameter){
        parameter = parameter.replace('-',' ');
        char [] parameterArray = parameter.toCharArray();
        ArrayList<Character> charList = new ArrayList<>();
        for(int i = 0 ; i < parameterArray.length ; i ++)
            charList.add(parameterArray[i]);
        for(int i = charList.size() - 1 ; i > 0 ; i --){
            char c = charList.get(i);
            if(c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')
                charList.add(i, ' ');
        }
        StringBuilder handledParameter = new StringBuilder();
        for(int i = 0 ; i < charList.size() ; i ++)
            handledParameter.append(charList.get(i));
        String parameterTemp = handledParameter.toString().toLowerCase();
        ArrayList<String> result = new ArrayList<>();
        String [] parameterSplit = parameterTemp.split(" ");
        for(int i = 0 ; i < parameterSplit.length ; i ++)
            result.add(parameterSplit[i]);
        return result;
    }

/*    public ArrayList<LinkedList<ServiceNode>> searchServicesCompositions(ArrayList<String> ins, ArrayList<String> outs){
        ArrayList<LinkedList<ServiceNode>> compositions = new ArrayList<>();
        LinkedList<ServiceNode> composition = new LinkedList<>();
        ArrayList<ServiceNode> startNodes = searchByInput(sg, ins);
        ArrayList<ServiceNode> endNodes = searchByInput(sg, outs);
        for(int i = 0 ; i < startNodes.size() ; i ++){
            ServiceNode startNode = startNodes.get(i);
            composition.clear();
            for(int j = 0 ; j < endNodes.size() ; j ++){
                sg.searchPath(startNode, endNodes.get(j));
                composition = sg.targetPath;
                if(composition.size() == 0)
                    continue;
                compositions.add(composition);
            }
        }
        return compositions;
    }*/

    public HashMap<LinkedList<ServiceNode>, Double> searchServicesCompositions(HashMap<ServiceNode, Double> startNodes, HashMap<ServiceNode, Double> endNodes){
        HashMap<LinkedList<ServiceNode>, Double> compositions = new HashMap<>();
        for(Map.Entry<ServiceNode, Double> startEntry: startNodes.entrySet()){
            ServiceNode startNode = startEntry.getKey();
            sg.traverseEdges(startNode);
            for(Map.Entry<ServiceNode, Double> endEntry : endNodes.entrySet()){
                ServiceNode endNode = endEntry.getKey();
                sg.searchPath(startNode, endNode);
                LinkedList<ServiceNode> composition = sg.searchPath(startNode, endNode);
                if(composition == null)
                    continue;
                double parameterSimilarity = (startEntry.getValue() + endEntry.getValue()) / 3;
                compositions.put(composition, parameterSimilarity);
            }
        }
        return compositions;
    }

    public double descriptionSimilarity(LinkedList<ServiceNode> composition, String text) throws Exception{
        double result = 0;
        String mixText = null;
        if(composition.size() == 1)
            mixText = composition.get(0).getTtextDescription();
        if(composition.size() > 1)
            mixText = composition.getFirst().getTtextDescription() + composition.getLast().getTtextDescription();
        result = ts.textSimilarity(text, mixText);
        return result;
    }

    public ArrayList<LinkedList<ServiceNode>> selectCompostionsByDescription(
            HashMap<LinkedList<ServiceNode>, Double> compositions, String text) throws Exception{
        ArrayList<LinkedList<ServiceNode>> selectedCompositions = new ArrayList<>();
        double descriptionSimilarity = 0;
        for(Map.Entry<LinkedList<ServiceNode>, Double> entry : compositions.entrySet()){
            double parameterSimilarity = entry.getValue();
            descriptionSimilarity = descriptionSimilarity(entry.getKey(), text) / 3;
            if(parameterSimilarity + descriptionSimilarity >= 0.9)
                selectedCompositions.add(entry.getKey());
        }
        return selectedCompositions;
    }


    public ArrayList<LinkedList<ServiceNode>> getSelectedCompositions(ArrayList<String> input, ArrayList<String> output,
           String description) throws Exception{

        ArrayList<LinkedList<ServiceNode>> selectedCompositions = new ArrayList<>();

        HashMap<ServiceNode, Double> inNodesMap = searchByInput(sg, input);

        HashMap<ServiceNode, Double> outNodesMap = searchByOutput(sg, output);

        HashMap<LinkedList<ServiceNode>, Double> compositionsMap = searchServicesCompositions(inNodesMap, outNodesMap);

        selectedCompositions = selectCompostionsByDescription(compositionsMap, description);

        return selectedCompositions;
    }





    public static void main(String [] args) throws Exception {
        ServiceSimilarity ss = new ServiceSimilarity("http://127.0.0.1/domains/1.1/travel/");

/*        ArrayList<String> in = new ArrayList<>();
        in.add("hiking");
        in.add("surfing");

        ArrayList<String> out = new ArrayList<>();
        out.add("accommodation");

        String description = "This service provide the accommodation which has the sports of hiking and surfing.";

        System.out.println(ss.getSelectedCompositions(in, out, description).size());*/


        ArrayList<String> in = new ArrayList<>();
        in.add("Municipal-Unit");
        HashMap<ServiceNode, Double> inNodesMap = ss.searchByInput(ss.sg, in);
        System.out.println("Input match " + inNodesMap.size() + " services.");

        ArrayList<String> out = new ArrayList<>();
        out.add("Drought");
        HashMap<ServiceNode, Double> outNodesMap = ss.searchByOutput(ss.sg, out);
        System.out.println("Output match " + outNodesMap.size() + " services.");

        int i = 0;
        HashMap<LinkedList<ServiceNode>, Double> compositionsMap = ss.searchServicesCompositions(inNodesMap, outNodesMap);
        System.out.println("\n\n\nThere are " + compositionsMap.size() + " compositions.");
        for(Map.Entry<LinkedList<ServiceNode>, Double> entry : compositionsMap.entrySet()){
            LinkedList<ServiceNode> list = entry.getKey();
            System.out.println("\n\n\nService path" + ( ++ i ) );
            for(int j = 0 ; j < list.size() ; j ++)
                System.out.println("Service " + (j + 1) + list.get(j).service.getName() + " " + list.get(j).service.getURI());
        }


        ArrayList<LinkedList<ServiceNode>> selectedCompositions = new ArrayList<>();
        selectedCompositions = ss.selectCompostionsByDescription(compositionsMap,
                "This service informs about drought in a given municipal-unit.");
        System.out.println("\n\n\nThere are " + selectedCompositions.size() + " compositions after been selected.");
        for(int j = 0 ; j < selectedCompositions.size() ; j ++) {
            LinkedList<ServiceNode> list = selectedCompositions.get(j);
            System.out.println("\n\n\nService path" + ( j + 1));
            for (int k = 0; k < list.size(); k++)
                System.out.println("Service " + (k + 1) + list.get(k).service.getName() + " " + list.get(k).service.getURI());
        }
    }
}
