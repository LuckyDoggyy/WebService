package com.we.ws.service.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
@WebService(name = "hello",targetNamespace= "http://services.ws.com")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public interface Hello {
    @WebMethod()
    @WebResult(name = "myname",targetNamespace= "http://services.ws.com")
    String sayHello(@WebParam(name = "myname",targetNamespace= "http://services.ws.com") String myname);
}
