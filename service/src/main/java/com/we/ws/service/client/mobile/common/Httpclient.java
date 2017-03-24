package com.we.ws.service.client.mobile.common;

import com.squareup.okhttp.*;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-23
 */
public class Httpclient {
    public static void main(String[] args) throws Exception {
        String bodyStr = generateSoapMessage();
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:9000/helloWorld";
        MediaType soap = MediaType.parse("application/soap+xml; charset=utf-8");
        /*
        RequestBody body = RequestBody.create(soap,
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>" +
                        "    <sayHello xmlns=\"http://services.ws.com\">\n" +
                        "      <myname>都是成都市</myname>\n" +
                        "    </sayHello>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>");
        */
        RequestBody body = RequestBody.create(soap,bodyStr);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        System.out.println(res);

    }


    public static String generateSoapMessage() throws Exception {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPPart part = message.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        SOAPBody body = envelope.getBody();
        QName qname = new QName("http://services.ws.com", "sayHello");
        SOAPBodyElement ele = body.addBodyElement(qname);
        ele.addChildElement("myname").setValue("twogoods");
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        message.writeTo(byteOutputStream);
        return byteOutputStream.toString();
    }
}
