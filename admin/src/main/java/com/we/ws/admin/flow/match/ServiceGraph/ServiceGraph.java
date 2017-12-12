package com.we.ws.admin.flow.match.ServiceGraph;

import com.we.ws.admin.flow.match.OwlHandle.OntologySearch;
import com.we.ws.admin.flow.match.OwlsHandle.ParseOWLS;
import com.we.ws.admin.flow.match.ReadWSDL.WsdlDAO;
import org.mindswap.owls.process.variable.Input;
import org.mindswap.owls.process.variable.Output;
import org.mindswap.owls.service.Service;

import java.util.*;

/**
 * Created by xuxyu on 2017/8/9.
 */

public class ServiceGraph {

    OntologySearch os;
    ParseOWLS powls;
    LinkedList<ServiceNode> serviceNodes;

    public ServiceGraph(String htmlUrl) throws Exception {
        os = new OntologySearch();
        powls = new ParseOWLS();
        serviceNodes = new LinkedList();
        demos(htmlUrl);
    }

    public void setServiceNodes(ArrayList<String> uriStrings) throws Exception {    //读取所有服务，生成节点
        int i = 0;
        powls.setServiceMap(uriStrings);
        HashMap<String, Service> serviceMap = powls.getServiceMap();
        for (Map.Entry<String, Service> entry : serviceMap.entrySet()) {
            ServiceNode serviceNode = new ServiceNode(entry.getValue());
            serviceNodes.add(serviceNode);
        }
        System.out.println(serviceNodes.size());
    }

    public LinkedList<ServiceNode> getServiceNodes() {  //获取所有节点
        return serviceNodes;
    }

    public void addEdge(ServiceNode startNode, ServiceNode endNode) {  //添加一条边
        ArrayList<Output> outputs = startNode.getOutputs();
        ArrayList<Input> inputs = endNode.getInputs();
        Output output = null;
        Input input = null;
        if (outputs.size() == 0 || inputs.size() == 1)
            return;
        for (int i = 0; i < outputs.size(); i++)
            for (int j = 0; j < inputs.size(); j++) {
                output = outputs.get(i);
                input = inputs.get(j);
                String out = output.getParamType().toString();
                String in = input.getParamType().toString();
                if (os.isSub(out, in)) {
                    System.out.println();
                    startNode.nodeEdges.add(endNode);
                }
            }
    }

    public void setEdges() {   //添加所有边
        for (int i = 0; i < serviceNodes.size(); i++) {
            ServiceNode startNode = serviceNodes.get(i);
            for (int j = 0; j < i; j++)
                addEdge(startNode, serviceNodes.get(j));
            for (int j = i + 1; j < serviceNodes.size(); j++)
                addEdge(startNode, serviceNodes.get(j));
        }
    }

    public void resetAll() {   //重置所有节点的最短路径
        for (int i = 0; i < serviceNodes.size(); i++)
            serviceNodes.get(i).reset();
    }


    public void traverseEdges(ServiceNode startNode) {   //从节点startNode出发的所有路径
        resetAll();
        int distance = 0;
        startNode.distance = distance;
        startNode.visited = true;

        Queue<ServiceNode> queue = new LinkedList<ServiceNode>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            ServiceNode nextNode = queue.poll();
            LinkedList<ServiceNode> adjacentNodes = nextNode.getNodeEdges();
            for (int i = 0; i < adjacentNodes.size(); i++) {
                ServiceNode adjacentNode = adjacentNodes.get(i);
                if (!adjacentNode.visited) {
                    adjacentNode.previous = nextNode;
                    adjacentNode.distance = nextNode.distance + 1;
                    adjacentNode.visited = true;
                    queue.add(adjacentNode);
                }
            }
        }
    }

/*    public void searchPath(ServiceNode startNode, ServiceNode endNode){
        if(endNode == startNode){
            targetPath.add(startNode);
            return;
        }
        if(endNode != startNode && endNode.previous != null)
            searchPath(startNode, endNode.previous);
        targetPath.add(endNode);
        if(endNode.previous == null)
            return;
    }*/

    public LinkedList<ServiceNode> searchPath(ServiceNode startNode, ServiceNode endNode) {
        LinkedList<ServiceNode> temp = new LinkedList<>();
        LinkedList<ServiceNode> result = new LinkedList<>();
        if(startNode == endNode){
            temp.add(endNode);
            return temp;
        }
        while(endNode != startNode && endNode.previous != null) {
            temp.add(endNode);
            endNode = endNode.previous;
        }
        temp.add(startNode);
        if(endNode != startNode)
            return null;
        for(int i = temp.size() - 1 ; i >= 0  ; i --)
            result.add(temp.get(i));
        return result;
    }

    public void demos(String htmlUrl) throws Exception {
        long startMili = System.currentTimeMillis();
        GetUrls gu = new GetUrls(htmlUrl);
        ArrayList<String> owlsList = gu.getOwlsList();
        setServiceNodes(owlsList);
        setEdges();
        long endMili = System.currentTimeMillis();
//        System.out.println(("Service graph cost " + (endMili - startMili) / 1000 + "s"));
    }







    public static void main(String[] args) throws Exception {
        ServiceGraph sg = new ServiceGraph("http://127.0.0.1/domains/1.1/travel/");
        LinkedList<ServiceNode> serviceNodes = sg.getServiceNodes();
//        for(ServiceNode serviceNode : serviceNodes){
//            String url = serviceNode.getWSDL().toString();
//            ArrayList<String> inputs = serviceNode.getInputsParmeters();
//            ArrayList<String> outputs = serviceNode.getOutputsParmeters();
//            WsdlDAO.insertParams(url, inputs, "t_serviceparam");
//            WsdlDAO.insertParams(url, outputs, "t_outputserviceparam");
//        }


        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Input the service number: ");
        int serviceNum = scanner.nextInt();
        while (serviceNum != -1) {
            ServiceNode startNode = serviceNodes.get(serviceNum);
            ServiceNode endNode = startNode;
            while (endNode.getNodeEdges().size() != 0) {
                int serviceNo = random.nextInt(endNode.getNodeEdges().size());
                endNode = endNode.getNodeEdges().get(serviceNo);
            }
            sg.traverseEdges(startNode);
            LinkedList<ServiceNode> list = sg.searchPath(startNode, endNode);
            for(int i = 0 ; i < list.size() ; i ++)
                System.out.println(list.get(i).service.getName() + " " +list.get(i).service.getURI());
            System.out.print("\nInput the service number: ");
            serviceNum = scanner.nextInt();
        }
    }
}
