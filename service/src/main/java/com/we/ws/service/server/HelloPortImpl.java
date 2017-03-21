package com.we.ws.service.server;


import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
@Service
@WebService(serviceName = "HelloService",endpointInterface = "com.we.ws.service.server.Hello")
public class HelloPortImpl implements Hello {
    public String sayHello(java.lang.String myname) {
        return myname;
    }
}