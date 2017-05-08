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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * <p>
 * <p>
 * <p>
 * soap:Envelope.soap:Envelope.soap:Body
 * soap:Body.soap:Body.getMobileCodeInfoResponse
 * getMobileCodeInfoResponse.getMobileCodeInfoResult.string
 * <p>
 * <p>
 * <p>
 * <p>
 * root.soap:Envelope.soap:Envelope
 * soap:Envelope.soap:Body.soap:Body
 * soap:Body.getMobileCodeInfoResponse.getMobileCodeInfoResponse
 * getMobileCodeInfoResponse.getMobileCodeInfoResult.string
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-24
 */
public class XmlParser {
    public static Map<String, Object> parseXMl(Map<String, Object> configMap, String xml) throws Exception {
        Element root = preParser(xml);
        Map<String, Object> beanMap = doParseXml(configMap, root);
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

    private static Map<String, Object> doParse(Map<String, Object> configMap, Element root) throws Exception {
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


            Object type = entry.getValue();
            if (type instanceof String) {
                NodeList propertyNode = root.getElementsByTagName(property);
                if (propertyNode.getLength() == 1) {
                    String value = propertyNode.item(0).getTextContent();
                    valueMap.put(property, value);
                } else {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < propertyNode.getLength(); i++) {
                        list.add(propertyNode.item(i).getTextContent());
                    }
                    valueMap.put(property, list);
                }
            } else {
                Map<String, Object> config = (Map<String, Object>) type;
                //上面是处理string和list,此处是有类嵌套结构的解析
                Map<String, Object> map = doParseXml(config, root);
                valueMap.put(property, map);
            }
        }
        return valueMap;
    }

    private static Map<String, Object> doParseXml(Map<String, Object> configMap, Element root) throws Exception {
        Element originRoot = root;
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
            boolean rootChanged = false;
            if (!root.getTagName().equals(property)) {
                NodeList nodeList = root.getElementsByTagName(property);
                if (nodeList.getLength() == 1) {
                    root = (Element) nodeList.item(0);
                    rootChanged = true;
                } else {
                    if (entry.getValue() instanceof Map) {
                        List<Map> list = new ArrayList<>();
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            list.add(doParseXml((Map<String, Object>) entry.getValue(), (Element) nodeList.item(i)));
                        }
                        setValue(property, alies, list, valueMap, null);
                        continue;
                    } else {
                        //字符串数组里各字符串的rule切割,先不考虑
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            list.add(nodeList.item(i).getTextContent());
                        }
                        setValue(property, alies, list, valueMap, null);
                        continue;
                    }
                }
            }

            Object type = entry.getValue();
            if (type instanceof String) {
                setValue(property, alies, root.getTextContent(), valueMap, (String) type);
            } else {
                Map<String, Object> config = (Map<String, Object>) type;
                //上面是处理string和list,此处是有类嵌套结构的解析
                Map<String, Object> map = doParseXml(config, root);
                setValue(property, alies, map, valueMap, null);
            }
            if (rootChanged) {
                root = originRoot;
            }
        }
        return valueMap;
    }

    private static void setValue(String property, String alies, Object value, Map<String, Object> valueMap, String typeWithRule) {
        valueMap.put(property, value);
        if (!StringUtils.isEmpty(alies)) {
            valueMap.put(alies, value);
        }
        if (!StringUtils.isEmpty(typeWithRule)) {
            String[] token = typeWithRule.split("\\|");
            if (token.length == 2) {
                Map<String, String> values = new HashMap<>();
                if(value instanceof String){
                    parseplaceholder((String) value, token[1], values);
                    for (Map.Entry<String, String> entry : values.entrySet()) {
                        valueMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
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
