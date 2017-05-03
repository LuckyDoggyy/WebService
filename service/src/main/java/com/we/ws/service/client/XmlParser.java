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

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-24
 */
public class XmlParser {
    public static BeanMap parseXMl(Map<String, Object> configMap, String xml) throws Exception {
        Element root = preParser(xml);
        BeanMap beanMap = doParse(configMap, root);
        return beanMap;
    }

    private static Element preParser(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
        return doc.getDocumentElement();
    }

    private static <T> void setMapValue(String property, String alies, String typeWithRule, T value, Class<T> clazz, BeanGenerator generator, Map<String, Object> valueMap) {
        generator.addProperty(property, clazz);
        valueMap.put(property, value);
        if (!StringUtils.isEmpty(alies)) {
            generator.addProperty(alies, clazz);
            valueMap.put(alies, value);
        }
        if (!StringUtils.isEmpty(typeWithRule)) {
            String[] token = typeWithRule.split("\\|");
            if (token.length == 2) {
                Map<String, String> values = new HashMap<>();
                parseplaceholder((String) value, token[1], values);
                for (Map.Entry<String, String> entry : values.entrySet()) {
                    generator.addProperty(entry.getKey(), String.class);
                    valueMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private static BeanMap doParse(Map<String, Object> configMap, Element root) throws Exception {
        BeanGenerator generator = new BeanGenerator();
        Map<String, Object> valueMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            String[] properties = entry.getKey().split("\\|");
            String property = null;
            String alies = null;
            if (properties.length == 2) {
                property = properties[0];
                alies = properties[1];
            } else {
                property = entry.getKey();
            }
            if (ConfigConstant.MAPROOT.equals(property)) continue;
            if (root.getTagName().equals(property)) {
                Object type = entry.getValue();
                if (type instanceof String) {
                    setMapValue(property, alies, (String) type, root.getTextContent(), String.class, generator, valueMap);
                } else {
                    Map<String, Object> config = (Map<String, Object>) type;
                    String typeStr = config.get(ConfigConstant.MAPROOT).toString();
                    if (ConfigConstant.ARRAY.equals(typeStr)) {
                        List<String> list = new ArrayList<>();
                        String tagname = null;
                        for (Map.Entry<String, Object> listentry : config.entrySet()) {
                            tagname = listentry.getKey();
                            if (!ConfigConstant.MAPROOT.equals(tagname)) {
                                break;
                            }
                        }
                        NodeList nodeList = root.getElementsByTagName(tagname);
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);
                            list.add(node.getTextContent());
                        }
                        setMapValue(property, alies, null, list, List.class, generator, valueMap);
                    } else {
                        //上面是处理string和list,此处是有类嵌套结构的解析
                        BeanMap beanMap = doParseXml((Map<String, Object>) type, root.getElementsByTagName(typeStr));
                        setMapValue(property, alies, null, beanMap, BeanMap.class, generator, valueMap);
                    }
                }
            } else {
                //TODO 这里程序不可达,TagName和设置的property要一样
                System.out.println("TagName和设置的property的不一样!!!");
//                NodeList list = root.getElementsByTagName(root.getTagName());
//                BeanMap beanMap = doParseXml(configMap, list);
//                valueMap.put(property, beanMap);
            }
        }
        BeanMap beanMap = BeanMap.create(generator.create());
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            beanMap.put(WordUtils.uncapitalize(entry.getKey()), entry.getValue());
        }
        return beanMap;
    }

    private static BeanMap doParseXml(Map<String, Object> configMap, NodeList elementList) {
        BeanMap beanMap = null;
        if(configMap.size()==1){

        }
        if (elementList.getLength() > 1) {
            //list
            BeanGenerator generator = new BeanGenerator();
            String propertyList = configMap.get(ConfigConstant.MAPROOT).toString();
            generator.addProperty(propertyList, List.class);
            List<BeanMap> beanMaps = new ArrayList<>(elementList.getLength());
            for (int i = 0; i < elementList.getLength(); i++) {
                Node node = elementList.item(i);
                if (node instanceof Element) {
                    beanMaps.add(parseXmlItem(configMap, (Element) node));
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
                bean = parseXmlItem(configMap, (Element) node);
            }
            beanMap = BeanMap.create(generator.create());
            beanMap.put(WordUtils.uncapitalize(property), bean);
        }
        return beanMap;
    }

    private static BeanMap getInType(Map<String, Object> configMap, NodeList elementList) {

        return null;
    }

    private static BeanMap parseXmlItem(Map<String, Object> configMap, Element ele) {
        BeanGenerator generator = new BeanGenerator();
        Map<String, Object> valueMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            String[] properties;
            if (configMap.size() == 1) {
                properties = entry.getValue().toString().split("\\|");
            } else {
                properties = entry.getKey().split("\\|");
            }
            String property = null;
            String alies = null;
            if (properties.length == 2) {
                property = properties[0];
                alies = properties[1];
            } else {
                property = properties[0];
            }

            if (ConfigConstant.MAPROOT.equals(property)) continue;
            Object type = entry.getValue();
            if (type instanceof String) {
                NodeList propertyNode = ele.getElementsByTagName(property);
                if (propertyNode.getLength() == 1) {
                    String value = propertyNode.item(0).getTextContent();
                    setMapValue(property, alies, (String) null, value, String.class, generator, valueMap);
                } else {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < propertyNode.getLength(); i++) {
                        list.add(propertyNode.item(i).getTextContent());
                    }
                    setMapValue(property, alies, (String) null, list, List.class, generator, valueMap);
                }
            } else {
                Map<String, Object> config = (Map<String, Object>) type;
                String typeStr = config.get(ConfigConstant.MAPROOT).toString();
                if (ConfigConstant.ARRAY.equals(typeStr)) {
                    List<String> list = new ArrayList<>();
                    String tagname = null;
                    for (Map.Entry<String, Object> listentry : config.entrySet()) {
                        tagname = listentry.getKey();
                        if (!ConfigConstant.MAPROOT.equals(tagname)) {
                            break;
                        }
                    }
                    NodeList nodeList = ele.getElementsByTagName(tagname);
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        list.add(node.getTextContent());
                    }
                    setMapValue(property, alies, null, list, List.class, generator, valueMap);
                } else {
                    BeanMap beanMap = doParseXml((Map<String, Object>) type, ele.getElementsByTagName(typeStr));
                    setMapValue(property, alies, null, beanMap, BeanMap.class, generator, valueMap);
                }
            }
        }
        BeanMap beanMap = BeanMap.create(generator.create());
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            beanMap.put(WordUtils.uncapitalize(entry.getKey()), entry.getValue());
        }
        return beanMap;
    }

    public static Map<String, Object> parseConfig(String config) {
        List<ConfigLine> configs = loadConfig(config);
        Map<String, Object> map = doParseConfig(configs, ConfigConstant.ROOT);
        return map;
    }

    private static List<ConfigLine> loadConfig(String config) {
        String[] lines = config.split("\n");
        List<ConfigLine> configLines = new ArrayList<>(lines.length);
        for (String line : lines) {
            ConfigLine configLine = ConfigLine.of(line);
            if (configLine == null) continue;
            configLines.add(configLine);
        }
        return configLines;
    }

    private static Map<String, Object> doParseConfig(List<ConfigLine> configLines, String objName) {
        Map<String, Object> map = new HashMap<>();
        map.put(ConfigConstant.MAPROOT, objName);
        for (ConfigLine configLine : configLines) {
            if (objName.equals(configLine.getObjName())) {
                if (configLine.typeIsDefined()) {
                    configToMap(map, configLine, configLine.getTypeWithRule());
                } else {
                    Map<String, Object> beanMap = doParseConfig(configLines, configLine.getType());
                    configToMap(map, configLine, beanMap);
                }
            }
        }
        return map;
    }

    private static void configToMap(Map<String, Object> map, ConfigLine configLine, Object config) {
        if (!StringUtils.isEmpty(configLine.getAlies())) {
            map.put(configLine.getProperty() + "|" + configLine.getAlies(), config);
            return;
        }
        map.put(configLine.getProperty(), config);
    }

    private static String getSingleValueInEle(Element ele, String property) {
        NodeList propertyNode = ele.getElementsByTagName(property);
        return propertyNode.item(0).getTextContent();
    }

    private static void parseplaceholder(String text, String rule, Map<String, String> map) {
        int start = rule.indexOf("{");
        int end = rule.indexOf("}");
        String property = rule.substring(start + 1, end);
        rule = rule.substring(end + 1);
        if (StringUtils.isEmpty(rule)) {
            map.put(property, text);
            return;
        }
        String split = rule.substring(0, rule.indexOf("{"));
        String[] token = text.split(split);
        map.put(property, token[0]);
        if (token.length > 1) {
            int textIndex = token[0].length() + split.length();
            parseplaceholder(text.substring(textIndex), rule.substring(split.length()), map);
        }
    }
}
