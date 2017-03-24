package com.we.ws.service.client.mobile.common;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-23
 */
public class GeneralService extends Service {

    public GeneralService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }
}
