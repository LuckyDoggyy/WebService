package com.we.ws.service.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
@WebService(name = "hello")
public interface Hello {

    @WebMethod()
    String sayHello(@WebParam(name = "myname", targetNamespace = "") String myname);
}
