package com.we.ws.admin.flow.match.OwlsHandle;

import org.mindswap.owl.*;
import org.mindswap.owls.service.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xuxyu on 2017/8/7.
 */
public class ParseOWLS {

    HashMap<String, Service> serviceMap;
    OWLKnowledgeBase kb;

    public ParseOWLS(){
        kb = OWLFactory.createKB();
        serviceMap = new HashMap();
    }

    public void addUri(String uriStr) throws Exception{
        URI uri = URI.create(uriStr);
        Service service = kb.readService(uri);
        String serviceName = service.getName();
        serviceMap.put(serviceName.trim(),service);
    }

    public void setServiceMap(ArrayList<String> uriStrings) throws Exception{
        for(int i = 0 ; i < uriStrings.size() ; i ++)
            addUri(uriStrings.get(i));
    }

    public HashMap<String, Service> getServiceMap(){
        return serviceMap;
    }
/*
    public ArrayList<Input> getInputs() {
        ArrayList<Input> inputList = new ArrayList();
        OWLIndividualList oil = process.getInputs();
        for(Object obj : oil) {
            Input oii = (Input)obj;
            inputList.add(oii);
        }
        return inputList;
    }

    public ArrayList<String> inputOntologySource(ArrayList<Input> oil) {
        String parameterType;
        ArrayList<String> paramList = new ArrayList<>();
        for(int i = 0 ; i < oil.size() ; i++){
            parameterType = oil.get(i).getParamType().toString().trim();
            paramList.add(parameterType);
        }
        return paramList;
    }

    public ArrayList<Output> getOutputs() {
        ArrayList<Output> outputList = new ArrayList();
        OWLIndividualList oil = process.getOutputs();
        for(Object obj : oil) {
            Output output = (Output) obj;
            outputList.add(output);
        }
        return outputList;
    }

    public ArrayList<String> outputOntologySource(ArrayList<Output> oil) {
        String parameterType;
        ArrayList<String> paramList = new ArrayList<>();
        for(int i = 0 ; i < oil.size() ; i++){
            parameterType = oil.get(i).getParamType().toString().trim();
            paramList.add(parameterType);
        }
        return paramList;
    }


    public String getTextDescription() {
        return profile.getTextDescription();
    }

    public WSDLAtomicGrounding getWSDLAtomicGrounding() {
        WSDLAtomicGrounding wsdlAtomicGrounding = (WSDLAtomicGrounding)grounding.getAtomicGrounding((AtomicProcess)this.process);
        return wsdlAtomicGrounding;
    }

    public URI getWSDL(){
        return getWSDLAtomicGrounding().getWSDL();
    }

    public URI getInputMessage(){
        return getWSDLAtomicGrounding().getInputMessage();
    }

    public URI getOutputMessage(){
        return getWSDLAtomicGrounding().getOutputMessage();
    }

    public URI getOperation(){
        return getWSDLAtomicGrounding().getOperation();
    }

    public URI getPortType() {
        return getWSDLAtomicGrounding().getPortType();
    }




    public static void main(String [] args) throws Exception{
        ParseOWLS owls = new ParseOWLS();
        owls.addUri("http://127.0.0.1/domains/1.1/travel/countrycity_sportshotel_service.owls");
        owls.addUri("http://127.0.0.1/domains/1.1/travel/generic-agentsports_destination_service.owls");
        owls.addUri("http://127.0.0.1/domains/1.1/travel/geographical-region_lightning_service.owls");
        owls.addUri("http://127.0.0.1/domains/1.1/travel/_Francemap_service.owls");

        System.out.println(owls.getTextDescription());
        System.out.println(owls.getWSDL());
        System.out.println(owls.getInputMessage());
        System.out.println(owls.getOutputMessage());
        System.out.println(owls.getOperation());
        System.out.println(owls.getPortType()); */
    }
