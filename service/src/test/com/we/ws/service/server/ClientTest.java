package com.we.ws.service.server;

import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

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
        String reply = client.sayHello("HI");
        System.out.println("Server said: " + reply);
    }

    @Test
    public void remote() throws Exception {
        DynamicClientFactory factory = DynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = factory.createClient("http://ws.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl");
        Object[] res = client.invoke("getDomesticAirlinesTime", "上海","123");
        System.out.println("Echo response: " + res[0]);
    }
}