package com.we.ws.service.client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-24
 */
public class XmlPraser {


    private static String test = "<Airlines xmlns=\"\">\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime1\" msdata:rowOrder=\"0\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5137</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>07:00</StartTime>\n" +
            "<ArriveTime>09:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime2\" msdata:rowOrder=\"1\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9916</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>07:00</StartTime>\n" +
            "<ArriveTime>09:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345</Week>\n" +
            "</AirlinesTime><AirlinesTime diffgr:id=\"AirlinesTime3\" msdata:rowOrder=\"3\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1886</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:55</StartTime>\n" +
            "<ArriveTime>00:20</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>1234</Week>\n" +
            "</AirlinesTime>\n" +
            "</Airlines>";

    private static String name = "<string xmlns=\"http://WebXml.com.cn/\">1575175601：江苏 扬州 江苏移动全球通卡</string>";

    private static String config = "root.Airlines.AirlinesTime\nAirlinesTime.Company.string\nAirlinesTime.AirlineCode.string\n";

    public static void loadConfig() {
        String[] lines = config.split("\n");
        for (String line : lines) {

        }

    }


    public static void praser() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(new ByteArrayInputStream(test.getBytes()));
        Element root = doc.getDocumentElement();
        System.out.println(root.getTagName());
        NodeList list = root.getElementsByTagName("AirlinesTime");
        System.out.println("length---" + list.getLength());
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                System.out.println(getSingleValueInEle(ele, "StartDrome"));
                System.out.println(getSingleValueInEle(ele, "ArriveTime"));


            }
        }
    }

    private static String getSingleValueInEle(Element ele, String property) {
        NodeList propertyNode = ele.getElementsByTagName(property);
        return propertyNode.item(0).getTextContent();
    }

    private static void placeholder() {
        String sql = "1575175601: 江苏 扬州 江苏移动全球通卡";
        String palce = "{mobile}: {pr} {city} {property}";
        Pattern pattern = Pattern.compile("?<name>exp)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.start());
        }
        sql = matcher.replaceAll("your_table");
        System.out.println(sql);
    }

    public static void main(String[] args) throws Exception {
        loadConfig();
    }
}
