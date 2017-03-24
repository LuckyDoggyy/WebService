package com.we.ws.service.server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.Test;

import javax.xml.ws.Endpoint;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
public class ServerTest {

    public static void main(String[] args) {
        System.out.println("Starting Server");
        HelloPortImpl implementor = new HelloPortImpl();
        String address = "http://localhost:9000/helloWorld";
        Endpoint.publish(address, implementor);
    }

    @Test
    public void test2() {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/hello");
        factory.setServiceClass(Hello.class);
        factory.setServiceBean(new HelloPortImpl());
        factory.create();
        System.out.println("soap ws is published");
    }
}