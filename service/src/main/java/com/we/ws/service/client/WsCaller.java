package com.we.ws.service.client;

import com.squareup.okhttp.*;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-23
 */
public class WsCaller {
    private static final Logger log = LoggerFactory.getLogger(WsCaller.class);

    private static OkHttpClient client = new OkHttpClient();

    public static String call(String url, String targetNamespace, String method, List<RequestParam> requestParams) throws Exception {
        return exectueSoap12(url, generateSoap12Message(targetNamespace, method, requestParams));
    }

    private static String generateSoap12Message(String targetNamespace, String method, List<RequestParam> requestParams) throws Exception {
        return generateSoapMessage(SOAPConstants.SOAP_1_2_PROTOCOL, targetNamespace, method, requestParams);
    }

    private static String generateSoap11Message(String targetNamespace, String method, List<RequestParam> requestParams) throws Exception {
        return generateSoapMessage(SOAPConstants.SOAP_1_1_PROTOCOL, targetNamespace, method, requestParams);
    }

    private static String generateSoapMessage(String protocol, String targetNamespace, String method, List<RequestParam> requestParams) throws Exception {
        MessageFactory factory = MessageFactory.newInstance(protocol);
        SOAPMessage message = factory.createMessage();
        SOAPPart part = message.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        SOAPBody body = envelope.getBody();
        QName qname = new QName(targetNamespace, method);
        SOAPBodyElement ele = body.addBodyElement(qname);
        for (RequestParam requestParam : requestParams) {
            ele.addChildElement(requestParam.getName()).setValue(requestParam.getValue());
        }
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        message.writeTo(byteOutputStream);
        return byteOutputStream.toString();
    }

    private static String exectueSoap11(String url, String soapStr) throws Exception {
        MediaType soapMedia = MediaType.parse("text/xml; charset=utf-8");
        return exectue(url, soapStr, soapMedia);
    }

    private static String exectueSoap12(String url, String soapStr) throws Exception {
        MediaType soapMedia = MediaType.parse("application/soap+xml; charset=utf-8");
        return exectue(url, soapStr, soapMedia);

    }

    private static String exectue(String url, String soapStr, MediaType mediaType) throws Exception {
        RequestBody body = RequestBody.create(mediaType, soapStr);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        return formatXml(res);
    }

    private static String formatXml(String xml) {
        try {
            SAXReader reader = new SAXReader();
            StringReader in = new StringReader(xml);
            Document doc = reader.read(in);
            OutputFormat formater = OutputFormat.createPrettyPrint();
            formater.setEncoding("utf-8");
            StringWriter out = new StringWriter();
            XMLWriter writer = new XMLWriter(out, formater);
            writer.write(doc);
            writer.close();
            return out.toString();
        } catch (Exception e) {
            log.error("xml parse error :{}", e);
            return xml;
        }
    }
}
