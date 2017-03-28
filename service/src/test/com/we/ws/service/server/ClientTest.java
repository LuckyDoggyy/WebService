package com.we.ws.service.server;

import com.we.ws.service.client.KeyValuePair;
import com.we.ws.service.client.RequestParam;
import com.we.ws.service.client.WsCaller;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
public class ClientTest {
    @Test
    public void local() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(Hello.class);
        factory.setAddress("http://localhost:8080/services/hello");
        Hello client = (Hello) factory.create();
        System.out.println(client.sayHello("HI"));
    }

    @Test
    public void remote() throws Exception {
        DynamicClientFactory factory = DynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = factory.createClient("http://ws.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl");
        Object[] res = client.invoke("getDomesticAirlinesTime", "上海", "123");
        System.out.println("Echo response: " + res[0]);
    }

    @Test
    public void callerLocal() throws Exception {
        List<RequestParam> requestParams = Arrays.asList(new RequestParam("myname", "上海"));
        String res = WsCaller.call("http://localhost:9000/helloWorld", "http://services.ws.com", "sayHello", requestParams);
        System.out.println(res);
    }

    @Test
    public void caller() throws Exception {
        List<RequestParam> requestParams = Arrays.asList(new RequestParam("theCityCode", "上海"));
        String body = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <sayHello xmlns=\"http://services.ws.com\">\n" +
                "        <myname>schema</myname>\n" +
                "    </sayHello>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        String res = WsCaller.call("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx", "http://WebXml.com.cn/", "getWeather", requestParams);
        System.out.println(res);
    }
}