package com.we.ws.service.axis2;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.junit.Test;

import javax.xml.namespace.QName;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-23
 */
public class ClientTest {
    @Test
    public void test() throws AxisFault {
        RPCServiceClient client = new RPCServiceClient();
        Options options = client.getOptions();
        String url = "http://ws.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl";
        EndpointReference end = new EndpointReference(url);
        options.setTo(end);
        options.setProperty(HTTPConstants.CHUNKED, "false");
        options.setAction("http://WebXml.com.cn/getDomesticAirlinesTime");
        Object[] obj = new Object[]{"上海"};
        Class<?>[] classes = new Class[] { String.class };
        QName qname = new QName("http://WebXml.com.cn/", "getDomesticAirlinesTime");
        Object[] result = client.invokeBlocking(qname, obj,classes);
        System.out.println(result[0]);
    }
}
