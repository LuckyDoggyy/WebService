package com.we.ws.admin.flow.match.ReadWSDL;

import com.ibm.wsdl.BindingOperationImpl;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import java.util.List;
import java.util.Map;

/**
 * Created by xuxyu on 2017/8/23.
 */
public class WsdlBean {

    Definition definition;
    String wsdlUrl;
    String targetNS;
    String serviceName;
    String remark;
    String method;
    String output;

    public WsdlBean(String wsdlUrl) throws Exception{
        this.wsdlUrl = wsdlUrl;
        setDefinition();
        setWsdlUrl();
        setTargetNS();
        setServiceName();
        setRemark();
        setMethod();

    }

    public Definition getDefinition(){
        return this.definition;
    }

    public void setDefinition() throws Exception{
        WSDLFactory factory = WSDLFactory.newInstance();
        WSDLReader reader = factory.newWSDLReader();
        reader.setFeature("javax.wsdl.verbose", true);
        reader.setFeature("javax.wsdl.importDocuments", true);
        this.definition = reader.readWSDL(wsdlUrl);

    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName() {
        String temp = definition.getServices().toString();
        int start = temp.indexOf('}') + 1;
        int end = temp.indexOf("=Service: name");
        this.serviceName = temp.substring(start,end);

        /*for(Map.Entry<String, Service> entry : services.entrySet()) {
            this.serviceName = entry.getValue().getServiceName().toString();
            break;
        */
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark() {
        this.remark = this.serviceName;
    }

    public String getWsdlUrl() {
        return wsdlUrl;
    }

    public void setWsdlUrl() {
        this.wsdlUrl = definition.getDocumentBaseURI();
    }

    public String getTargetNS() {
        return targetNS;
    }

    public void setTargetNS() {
        this.targetNS = definition.getTargetNamespace();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod() {
        Map<String, javax.wsdl.Service> services = definition.getServices();
        for(Map.Entry<String, javax.wsdl.Service> serviceEntry : services.entrySet()){

            javax.wsdl.Service service = serviceEntry.getValue();
            Map<String, Port> ports = service.getPorts();
            for(Map.Entry<String, Port> portEntry : ports.entrySet()){

                Port port = portEntry.getValue();
                Binding binding = port.getBinding();
                binding.getPortType();
                List<BindingOperationImpl> operationsList = binding.getBindingOperations();
                this.method = operationsList.get(0).getName();
                break;
            }
            break;

        }
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public static void main(String [] args) throws Exception {
        WsdlBean wb = new WsdlBean("http://127.0.0.1/wsdl/1personbicyclecar_price_TheBestservice.wsdl");
        System.out.println(wb.serviceName + "\n" + wb.targetNS + "\n" +wb.wsdlUrl + "\n" +wb.method + "\n" +wb.remark);

    }

}
