package com.we.ws.service.client;

import com.we.ws.service.client.mobile.MobileCodeWS;
import com.we.ws.service.client.mobile.MobileCodeWSSoap;
import org.junit.Test;
import javax.xml.namespace.QName;
import java.net.URL;
/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-21
 */
public class ClientTest {
    @Test
    public void testMobile(){
        URL wsdlURL = MobileCodeWS.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://WebXml.com.cn/", "MobileCodeWS");
        MobileCodeWS ss = new MobileCodeWS(wsdlURL, SERVICE_NAME);
        MobileCodeWSSoap soap = ss.getMobileCodeWSSoap();
        String result = soap.getMobileCodeInfo("15757175601", "");
        System.out.println(result);
    }

}