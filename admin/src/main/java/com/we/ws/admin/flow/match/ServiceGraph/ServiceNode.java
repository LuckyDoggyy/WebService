package com.we.ws.admin.flow.match.ServiceGraph;

import com.we.ws.admin.flow.match.OwlsHandle.ParseOWLS;

import org.mindswap.owl.OWLIndividualList;
import org.mindswap.owls.grounding.WSDLAtomicGrounding;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.variable.Input;
import org.mindswap.owls.process.variable.Output;
import org.mindswap.owls.service.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by xuxyu on 2017/8/9.
 */
public class ServiceNode {

    Service service;
    ArrayList<Input> inputs;
    ArrayList<Output> outputs;
    String textDescription;
    WSDLAtomicGrounding wsdlAtomicGrounding;
    URI WSDL;
    URI inputMessage;
    URI outputMessage;
    URI operation = null;
    URI portType;
    ArrayList<String> inputsParameters;
    ArrayList<String> outputsParameters;
    LinkedList<ServiceNode> nodeEdges;


    int distance;   //最短路径距离
    ServiceNode previous;   //最短路径前一节点
    boolean visited;    //是否遍历过

    public ServiceNode(Service service){
        this.service = service;
        setInputs();
        setOutputs();
        wsdlAtomicGrounding = (WSDLAtomicGrounding)service.getGrounding().getAtomicGrounding((AtomicProcess)service.getProcess());
        setOperation();
        setInputMessage();
        setOutputMessage();
        setPortType();
        setTextDescription();
        setWSDL();
        setInputsParmeters();
        setOutputsParmeters();
        nodeEdges = new LinkedList<>();

        previous = null;
        visited = false;
        distance = Integer.MAX_VALUE;

    }

    public void setInputs(){
        ArrayList<Input> inputList = new ArrayList();
        OWLIndividualList oil = service.getProcess().getInputs();
        for(Object obj : oil) {
            Input oii = (Input)obj;
            inputList.add(oii);
        }
        inputs = inputList;
    }

    public void setOutputs(){
        ArrayList<Output> outputList = new ArrayList();
        OWLIndividualList oil = service.getProcess().getOutputs();
        for(Object obj : oil) {
            Output oii = (Output)obj;
            outputList.add(oii);
        }
        outputs = outputList;
    }

    public String getServiceName(){
        return service.getName();
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public ArrayList<Output> getOutputs() {
        return outputs;
    }

    public URI getWSDL(){
        return WSDL;
    }

    public URI getInputMessage(){
        return inputMessage;
    }

    public URI getOutputMessage(){
        return outputMessage;
    }

    public URI getOperation(){
        return operation;
    }

    public URI getPortType() {
        return portType;
    }

    public String getTtextDescription(){
        return textDescription;
    }

    public void setTextDescription() {
        this.textDescription = service.getProfile().getTextDescription();
    }

    public void setWSDL() {
        this.WSDL = wsdlAtomicGrounding.getWSDL();
    }

    public void setInputMessage() {
        this.inputMessage = wsdlAtomicGrounding.getInputMessage();
    }

    public void setOutputMessage() {
        this.outputMessage = wsdlAtomicGrounding.getOutputMessage();
    }

    public void setOperation(){

    }

    public void setPortType() {
        this.portType = wsdlAtomicGrounding.getPortType();
    }

    public LinkedList<ServiceNode> getNodeEdges() {
        return nodeEdges;
    }

    public void addEdgeEndNode(ServiceNode endNode) {
        nodeEdges.add(endNode);
    }

    public void reset(){
        previous = null;
        distance = Integer.MAX_VALUE;
        visited = false;
    }

    public ArrayList<String> getInputsParmeters(){
        return inputsParameters;
    }

    public void setInputsParmeters(){
        inputsParameters = new ArrayList<>();
        String parameterType;
        String parameter;
        for(int i = 0 ; i < inputs.size() ; i ++){
            parameterType = inputs.get(i).toString();
            parameter = parameterType.substring(parameterType.indexOf("#") + 1,parameterType.lastIndexOf(" http"));
            inputsParameters.add(parameter);
        }
    }

    public ArrayList<String> getOutputsParmeters(){
        return outputsParameters;
    }

    public void setOutputsParmeters(){
        outputsParameters = new ArrayList<>();
        String parameterType;
        String parameter;
        for(int i = 0 ; i < outputs.size() ; i ++){
            parameterType = outputs.get(i).toString();
            parameter = parameterType.substring(parameterType.indexOf("#") + 1,parameterType.lastIndexOf(" http"));
            outputsParameters.add(parameter);
        }
    }


    public static void main(String [] args) throws Exception{
        ParseOWLS powls = new ParseOWLS();
        powls.addUri("http://127.0.0.1/domains/1.1/economy/EBookOrder2.owls");
        HashMap<String, Service> map = powls.getServiceMap();
        Service service = map.get("EBookOrder2");
        ServiceNode serviceNode = new ServiceNode(service);
        ArrayList<String> list = serviceNode.getInputsParmeters();
        for(int i = 0 ; i < list.size() ; i ++){
            System.out.println(serviceNode.getInputs().get(i));
            System.out.println(list.get(i));
        }

    }
}
