package com.we.ws.service.client;

import com.we.ws.service.client.mobile.MobileCodeWS;
import com.we.ws.service.client.mobile.MobileCodeWSSoap;
import com.we.ws.service.client.mobile.common.GeneralService;
import org.junit.Test;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
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
    @Test
    public void testGeneral() throws MalformedURLException {
        URL wsdlURL = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        QName SERVICE_NAME = new QName("http://WebXml.com.cn/", "MobileCodeWS");
        GeneralService ss = new GeneralService(wsdlURL, SERVICE_NAME);
    }

}