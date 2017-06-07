package com.we.ws.service.client;

import com.squareup.okhttp.*;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.we.ws.common.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static String generateCalloutStr(String originXml, String outConfig, String out) throws Exception {
        Map<String, Object> output = generateCallout(originXml, outConfig, out);
        if (out == null) {
            return "";
        }
        return JsonUtils.jsonFromObject(output);
    }

    public static Map<String, Object> generateCallout(String originXml, String outConfig, String out) throws Exception {
        String[] outputs = out.split(",");
        Map<String, Object> configmap = XmlParser.parseConfig(outConfig);
        Map<String, Object> callResult = XmlParser.parseXMl(configmap, originXml);
        Map<String, Object> valueMap = new HashMap<>();
        for (String param : outputs) {
            Object value = getMapValue(callResult, param);
            if (value != null) {
                valueMap.put(param, value);
            }
        }
        return valueMap;
    }

    public static Object getMapValue(Map<String, Object> map, String param) {
        Object value = map.get(param);
        if (value == null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    value = getMapValue((Map<String, Object>) entry.getValue(), param);
                    break;
                }
            }
        }
        return value;
    }

    public static String call(String url, String targetNamespace, String method, List<RequestParam> requestParams) throws Exception {
        return formatXml(exectueSoap12(url, generateSoap12Message(targetNamespace, method, requestParams)));
    }

    public static String call(String url, String targetNamespace, String method, List<RequestParam> requestParams, String outConfig, String out) throws Exception {
        if (StringUtils.isEmpty(out) || StringUtils.isEmpty(outConfig)) {
            return call(url, targetNamespace, method, requestParams);
        }
        String originXml = exectueSoap12(url, generateSoap12Message(targetNamespace, method, requestParams));
        try {
            return JsonUtils.formatJson(generateCalloutStr(originXml, outConfig, out));
        } catch (Exception e) {
            return formatXml(originXml);
        }
    }

    public static Map<String, Object> callInFlow(String url, String targetNamespace, String method, List<RequestParam> requestParams, String outConfig, String out) throws Exception {
        if (StringUtils.isEmpty(out) || StringUtils.isEmpty(outConfig)) {
            Map<String, Object> result = new HashMap<>();
            result.put("callReturn", call(url, targetNamespace, method, requestParams));
            return result;
        }
        String originXml = exectueSoap12(url, generateSoap12Message(targetNamespace, method, requestParams));
        Map<String, Object> valueMap = generateCallout(originXml, outConfig, out);
        if (valueMap.size() == 0) {
            log.warn("callout generate nothing!");
            Object xml = valueMap.get("callReturn");
            if (xml != null) {
                valueMap.put("callReturn", xml.toString() + "\n\n" + formatXml(originXml));
                return valueMap;
            }
            valueMap.put("callReturn", formatXml(originXml));
        }
        return valueMap;
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
        return response.body().string();
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
