package com.we.ws.service.server;


import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
@Service
@WebService(serviceName = "HelloService",targetNamespace= "http://services.ws.com",endpointInterface = "com.we.ws.service.server.Hello")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class HelloPortImpl implements Hello {
    public String sayHello(@WebParam(name = "myname")String myname) {
        return myname;
    }
}