package com.we.ws.service.server;

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

    @Test
    public void test(){
        System.out.println("Starting Server");
        HelloPortImpl implementor = new HelloPortImpl();
        String address = "http://localhost:9000/helloWorld";
        Endpoint.publish(address, implementor);
    }

}