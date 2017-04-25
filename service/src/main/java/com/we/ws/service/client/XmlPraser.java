package com.we.ws.service.client;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.*;
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


    private static String testconfig = "root.Airlines(air).AirlinesTime\n" +
            "AirlinesTime.Company.string\n" +
            "AirlinesTime.AirlineCode.string\n";

    private static String name = "<string xmlns=\"http://WebXml.com.cn/\">1575175601：江苏 扬州 江苏移动全球通卡</string>";
    private static String nameconfig = "root.string.string\n";


    private static String array = "<ArrayOfString><string>aa</string><string>bb</string><string>cc</string></ArrayOfString>";
    private static String arrayconfig = "root.ArrayOfString.string\n";

    public static BeanMap praseXMl(Map<String, Object> configMap, String xml) throws Exception {
        Element root = prePraser(xml);
        BeanMap beanMap = doPraseXml(configMap, root);
        return beanMap;
    }

    private static Element prePraser(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
        return doc.getDocumentElement();
    }

    private static BeanMap doPraseXml(Map<String, Object> configMap, Element root) throws Exception {
        BeanGenerator generator = new BeanGenerator();
        Map<String, Object> valueMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            String property = entry.getKey();
            if (ConfigConstant.MAPROOT.equals(property)) continue;
            if (root.getTagName().equals(property)) {
                Object type = entry.getValue();
                if (type instanceof String && property.equals("ArrayOfString")) {
                    List<String> list = new ArrayList<>();
                    NodeList nodeList = root.getElementsByTagName(type.toString());
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        list.add(node.getTextContent());
                    }
                    generator.addProperty(property, List.class);
                    valueMap.put(property, list);
                } else if (type instanceof String) {
                    generator.addProperty(property, String.class);
                    valueMap.put(property, root.getTextContent());
                } else {
                    BeanMap beanMap = doPraseXml((Map<String, Object>) type, root.getElementsByTagName(((Map<String, Object>) type).get(ConfigConstant.MAPROOT).toString()));
                    generator.addProperty(property, BeanMap.class);
                    valueMap.put(property, beanMap);
                }
            } else {
                //TODO
                NodeList list = root.getElementsByTagName(root.getTagName());
                BeanMap beanMap = doPraseXml(configMap, list);
                valueMap.put(property, beanMap);
            }
        }
        BeanMap beanMap = BeanMap.create(generator.create());
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            beanMap.put(WordUtils.uncapitalize(entry.getKey()), entry.getValue());
        }
        return beanMap;
    }

    private static BeanMap doPraseXml(Map<String, Object> configMap, NodeList elementList) {
        BeanMap beanMap = null;
        if (elementList.getLength() > 1) {
            //list
            BeanGenerator generator = new BeanGenerator();
            String propertyList = configMap.get(ConfigConstant.MAPROOT).toString();
            generator.addProperty(propertyList, List.class);
            List<BeanMap> beanMaps = new ArrayList<>(elementList.getLength());
            for (int i = 0; i < elementList.getLength(); i++) {
                Node node = elementList.item(i);
                if (node instanceof Element) {
                    beanMaps.add(praseXmlItem(configMap, (Element) node));
                }
            }
            beanMap = BeanMap.create(generator.create());
            beanMap.put(WordUtils.uncapitalize(propertyList), beanMaps);
        } else if (elementList.getLength() == 1) {
            //单独的
            BeanGenerator generator = new BeanGenerator();
            String property = configMap.get(ConfigConstant.MAPROOT).toString();
            generator.addProperty(property, BeanMap.class);
            Node node = elementList.item(0);
            BeanMap bean = null;
            if (node instanceof Element) {
                bean = praseXmlItem(configMap, (Element) node);
            }
            beanMap = BeanMap.create(generator.create());
            beanMap.put(WordUtils.uncapitalize(property), bean);
        }
        return beanMap;
    }

    private static BeanMap praseXmlItem(Map<String, Object> configMap, Element ele) {
        BeanGenerator generator = new BeanGenerator();
        Map<String, Object> valueMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            String property = entry.getKey();
            if (ConfigConstant.MAPROOT.equals(property)) continue;
            Object type = entry.getValue();
            if (type instanceof String && property.equals("ArrayOfString")) {
                List<String> list = new ArrayList<>();
                NodeList nodeList = ele.getElementsByTagName(type.toString());
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    list.add(node.getTextContent());
                }
                generator.addProperty(property, List.class);
                valueMap.put(property, list);
            } else if (type instanceof String) {
                String value = ele.getElementsByTagName(property).item(0).getTextContent();
                generator.addProperty(property, String.class);
                valueMap.put(property, value);
            } else {
                BeanMap beanMap = doPraseXml((Map<String, Object>) type, ele.getElementsByTagName(((Map<String, Object>) type).get(ConfigConstant.MAPROOT).toString()));
                generator.addProperty(property, BeanMap.class);
                valueMap.put(property, beanMap);
            }
        }
        BeanMap beanMap = BeanMap.create(generator.create());
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            beanMap.put(WordUtils.uncapitalize(entry.getKey()), entry.getValue());
        }
        return beanMap;
    }

    public static Map<String, Object> praseConfig(String config) {
        List<ConfigLine> configs = loadConfig(config);
        Map<String, Object> map = doPraseConfig(configs, ConfigConstant.ROOT);
        System.out.println(map);
        return map;
    }

    private static List<ConfigLine> loadConfig(String config) {
        String[] lines = config.split("\n");
        List<ConfigLine> configLines = new ArrayList<>(lines.length);
        for (String line : lines) {
            ConfigLine configLine = ConfigLine.of(line);
            if (config == null) continue;
            configLines.add(configLine);
        }
        return configLines;
    }

    private static Map<String, Object> doPraseConfig(List<ConfigLine> configLines, String objName) {
        Map<String, Object> map = new HashMap<>();
        map.put(ConfigConstant.MAPROOT, objName);
        for (ConfigLine configLine : configLines) {
            if (objName.equals(configLine.getObjName())) {
                if (configLine.typeIsBasic()) {
                    configToMap(map, configLine, configLine.getType());
                } else {
                    Map<String, Object> beanMap = doPraseConfig(configLines, configLine.getType());
                    configToMap(map, configLine, beanMap);
                }
            }
        }
        return map;
    }

    private static void configToMap(Map<String, Object> map, ConfigLine configLine, Object config) {
        map.put(configLine.getProperty(), config);
        if (!StringUtils.isEmpty(configLine.getAlies())) {
            map.put(configLine.getAlies(), config);
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
        Map<String, Object> configmap = praseConfig(arrayconfig);
        System.out.println(praseXMl(configmap, array));

//        String json = XmlUtil.xml2Json(name);
//        System.out.println(JsonUtils.toMap(json));


    }
}
