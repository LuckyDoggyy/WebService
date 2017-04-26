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

    private static String ttt = "<DataSet xmlns=\"http://WebXml.com.cn/\">\n" +
            "<xs:schema xmlns=\"\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\" id=\"Airlines\">\n" +
            "<xs:element name=\"Airlines\" msdata:IsDataSet=\"true\" msdata:UseCurrentLocale=\"true\">\n" +
            "<xs:complexType>\n" +
            "<xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n" +
            "<xs:element name=\"AirlinesTime\">\n" +
            "<xs:complexType>\n" +
            "<xs:sequence>\n" +
            "<xs:element name=\"Company\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"AirlineCode\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"StartDrome\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"ArriveDrome\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"StartTime\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"ArriveTime\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"Mode\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"AirlineStop\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "<xs:element name=\"Week\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "</xs:sequence>\n" +
            "</xs:complexType>\n" +
            "</xs:element>\n" +
            "</xs:choice>\n" +
            "</xs:complexType>\n" +
            "</xs:element>\n" +
            "</xs:schema>\n" +
            "<diffgr:diffgram xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\" xmlns:diffgr=\"urn:schemas-microsoft-com:xml-diffgram-v1\">\n" +
            "<Airlines xmlns=\"\">\n" +
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
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime3\" msdata:rowOrder=\"2\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1858</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>07:55</StartTime>\n" +
            "<ArriveTime>10:10</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12346</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime4\" msdata:rowOrder=\"3\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1858</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>07:55</StartTime>\n" +
            "<ArriveTime>10:10</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12346</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime5\" msdata:rowOrder=\"4\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5101</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:00</StartTime>\n" +
            "<ArriveTime>10:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime6\" msdata:rowOrder=\"5\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9889</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:00</StartTime>\n" +
            "<ArriveTime>10:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime7\" msdata:rowOrder=\"6\">\n" +
            "<Company>海南航空</Company>\n" +
            "<AirlineCode>HU7604</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:25</StartTime>\n" +
            "<ArriveTime>10:50</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>13456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime8\" msdata:rowOrder=\"7\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5145</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:30</StartTime>\n" +
            "<ArriveTime>10:40</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime9\" msdata:rowOrder=\"8\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9920</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:30</StartTime>\n" +
            "<ArriveTime>10:40</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime10\" msdata:rowOrder=\"9\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1590</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:55</StartTime>\n" +
            "<ArriveTime>11:15</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>13456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime11\" msdata:rowOrder=\"10\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1590</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>08:55</StartTime>\n" +
            "<ArriveTime>11:15</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>13456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime12\" msdata:rowOrder=\"11\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5103</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>09:00</StartTime>\n" +
            "<ArriveTime>11:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime13\" msdata:rowOrder=\"12\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9890</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>09:00</StartTime>\n" +
            "<ArriveTime>11:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime14\" msdata:rowOrder=\"13\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5129</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>09:20</StartTime>\n" +
            "<ArriveTime>11:55</ArriveTime>\n" +
            "<Mode>325</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime15\" msdata:rowOrder=\"14\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9883</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>09:20</StartTime>\n" +
            "<ArriveTime>11:55</ArriveTime>\n" +
            "<Mode>325</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime16\" msdata:rowOrder=\"15\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5139</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>09:30</StartTime>\n" +
            "<ArriveTime>11:55</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>2346日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime17\" msdata:rowOrder=\"16\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9918</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>09:30</StartTime>\n" +
            "<ArriveTime>11:55</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>2346日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime18\" msdata:rowOrder=\"17\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5105</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>10:00</StartTime>\n" +
            "<ArriveTime>12:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime19\" msdata:rowOrder=\"18\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1721</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>10:00</StartTime>\n" +
            "<ArriveTime>12:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime20\" msdata:rowOrder=\"19\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9892</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>10:00</StartTime>\n" +
            "<ArriveTime>12:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime21\" msdata:rowOrder=\"20\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1832</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>10:55</StartTime>\n" +
            "<ArriveTime>13:15</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>23</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime22\" msdata:rowOrder=\"21\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1832</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>10:55</StartTime>\n" +
            "<ArriveTime>13:15</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>23</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime23\" msdata:rowOrder=\"22\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5107</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:00</StartTime>\n" +
            "<ArriveTime>13:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime24\" msdata:rowOrder=\"23\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9894</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:00</StartTime>\n" +
            "<ArriveTime>13:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime25\" msdata:rowOrder=\"24\">\n" +
            "<Company>海南航空</Company>\n" +
            "<AirlineCode>HU7606</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:25</StartTime>\n" +
            "<ArriveTime>13:45</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime26\" msdata:rowOrder=\"25\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1836</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:30</StartTime>\n" +
            "<ArriveTime>13:55</ArriveTime>\n" +
            "<Mode>33A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime27\" msdata:rowOrder=\"26\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1836</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:30</StartTime>\n" +
            "<ArriveTime>13:55</ArriveTime>\n" +
            "<Mode>33A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime28\" msdata:rowOrder=\"27\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1902</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:55</StartTime>\n" +
            "<ArriveTime>14:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>23456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime29\" msdata:rowOrder=\"28\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1502</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:55</StartTime>\n" +
            "<ArriveTime>14:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>23456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime30\" msdata:rowOrder=\"29\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1502</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>11:55</StartTime>\n" +
            "<ArriveTime>14:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>23456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime31\" msdata:rowOrder=\"30\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU3185</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:00</StartTime>\n" +
            "<ArriveTime>14:20</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime32\" msdata:rowOrder=\"31\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5109</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:00</StartTime>\n" +
            "<ArriveTime>14:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime33\" msdata:rowOrder=\"32\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ3908</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:00</StartTime>\n" +
            "<ArriveTime>14:20</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime34\" msdata:rowOrder=\"33\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9896</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:00</StartTime>\n" +
            "<ArriveTime>14:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime35\" msdata:rowOrder=\"34\">\n" +
            "<Company>厦门航空</Company>\n" +
            "<AirlineCode>MF1764</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:00</StartTime>\n" +
            "<ArriveTime>14:20</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime36\" msdata:rowOrder=\"35\">\n" +
            "<Company>海南航空</Company>\n" +
            "<AirlineCode>HU7608</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:35</StartTime>\n" +
            "<ArriveTime>14:55</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>1345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime37\" msdata:rowOrder=\"36\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU564</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:45</StartTime>\n" +
            "<ArriveTime>15:10</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>235</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime38\" msdata:rowOrder=\"37\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1520</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:55</StartTime>\n" +
            "<ArriveTime>15:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>2356</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime39\" msdata:rowOrder=\"38\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1520</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>12:55</StartTime>\n" +
            "<ArriveTime>15:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>2356</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime40\" msdata:rowOrder=\"39\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5111</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>13:00</StartTime>\n" +
            "<ArriveTime>15:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime41\" msdata:rowOrder=\"40\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9898</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>13:00</StartTime>\n" +
            "<ArriveTime>15:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime42\" msdata:rowOrder=\"41\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU9103</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>13:30</StartTime>\n" +
            "<ArriveTime>16:00</ArriveTime>\n" +
            "<Mode>75B</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12356日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime43\" msdata:rowOrder=\"42\">\n" +
            "<Company>上海航空</Company>\n" +
            "<AirlineCode>FM9103</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>13:30</StartTime>\n" +
            "<ArriveTime>16:00</ArriveTime>\n" +
            "<Mode>75B</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12356日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime44\" msdata:rowOrder=\"43\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1532</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>13:55</StartTime>\n" +
            "<ArriveTime>16:15</ArriveTime>\n" +
            "<Mode>744</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime45\" msdata:rowOrder=\"44\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1532</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>13:55</StartTime>\n" +
            "<ArriveTime>16:15</ArriveTime>\n" +
            "<Mode>744</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime46\" msdata:rowOrder=\"45\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5113</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:00</StartTime>\n" +
            "<ArriveTime>16:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime47\" msdata:rowOrder=\"46\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9900</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:00</StartTime>\n" +
            "<ArriveTime>16:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime48\" msdata:rowOrder=\"47\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU272</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:10</StartTime>\n" +
            "<ArriveTime>16:40</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime49\" msdata:rowOrder=\"48\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5147</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:30</StartTime>\n" +
            "<ArriveTime>16:50</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime50\" msdata:rowOrder=\"49\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9922</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:30</StartTime>\n" +
            "<ArriveTime>16:50</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime51\" msdata:rowOrder=\"50\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1349</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:40</StartTime>\n" +
            "<ArriveTime>17:05</ArriveTime>\n" +
            "<Mode>320</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime52\" msdata:rowOrder=\"51\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1558</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:55</StartTime>\n" +
            "<ArriveTime>17:15</ArriveTime>\n" +
            "<Mode>773</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>13</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime53\" msdata:rowOrder=\"52\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1558</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>14:55</StartTime>\n" +
            "<ArriveTime>17:15</ArriveTime>\n" +
            "<Mode>773</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>13</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime54\" msdata:rowOrder=\"53\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5115</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>15:00</StartTime>\n" +
            "<ArriveTime>17:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime55\" msdata:rowOrder=\"54\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9902</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>15:00</StartTime>\n" +
            "<ArriveTime>17:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime56\" msdata:rowOrder=\"55\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5117</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:00</StartTime>\n" +
            "<ArriveTime>18:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime57\" msdata:rowOrder=\"56\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9904</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:00</StartTime>\n" +
            "<ArriveTime>18:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime58\" msdata:rowOrder=\"57\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1884</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:15</StartTime>\n" +
            "<ArriveTime>18:40</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime59\" msdata:rowOrder=\"58\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1884</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:15</StartTime>\n" +
            "<ArriveTime>18:40</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime60\" msdata:rowOrder=\"59\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1904</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:55</StartTime>\n" +
            "<ArriveTime>19:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime61\" msdata:rowOrder=\"60\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1518</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:55</StartTime>\n" +
            "<ArriveTime>19:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime62\" msdata:rowOrder=\"61\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1518</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>16:55</StartTime>\n" +
            "<ArriveTime>19:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime63\" msdata:rowOrder=\"62\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5119</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:00</StartTime>\n" +
            "<ArriveTime>19:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime64\" msdata:rowOrder=\"63\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9906</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:00</StartTime>\n" +
            "<ArriveTime>19:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime65\" msdata:rowOrder=\"64\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU3929</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:20</StartTime>\n" +
            "<ArriveTime>19:35</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime66\" msdata:rowOrder=\"65\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU3929</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:20</StartTime>\n" +
            "<ArriveTime>19:35</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime67\" msdata:rowOrder=\"66\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1253</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:20</StartTime>\n" +
            "<ArriveTime>19:35</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime68\" msdata:rowOrder=\"67\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1253</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:20</StartTime>\n" +
            "<ArriveTime>19:35</ArriveTime>\n" +
            "<Mode>321</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime69\" msdata:rowOrder=\"68\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5181</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:40</StartTime>\n" +
            "<ArriveTime>19:50</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime70\" msdata:rowOrder=\"69\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9924</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:40</StartTime>\n" +
            "<ArriveTime>19:50</ArriveTime>\n" +
            "<Mode>76A</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime71\" msdata:rowOrder=\"70\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1522</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:55</StartTime>\n" +
            "<ArriveTime>20:20</ArriveTime>\n" +
            "<Mode>773</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime72\" msdata:rowOrder=\"71\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1522</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>17:55</StartTime>\n" +
            "<ArriveTime>20:20</ArriveTime>\n" +
            "<Mode>773</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime73\" msdata:rowOrder=\"72\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5121</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>18:00</StartTime>\n" +
            "<ArriveTime>20:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime74\" msdata:rowOrder=\"73\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9908</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>18:00</StartTime>\n" +
            "<ArriveTime>20:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime75\" msdata:rowOrder=\"74\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU3844</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京南苑机场</ArriveDrome>\n" +
            "<StartTime>18:30</StartTime>\n" +
            "<ArriveTime>20:35</ArriveTime>\n" +
            "<Mode>737</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime76\" msdata:rowOrder=\"75\">\n" +
            "<Company>中国联航</Company>\n" +
            "<AirlineCode>KN5956</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京南苑机场</ArriveDrome>\n" +
            "<StartTime>18:30</StartTime>\n" +
            "<ArriveTime>20:35</ArriveTime>\n" +
            "<Mode>737</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime77\" msdata:rowOrder=\"76\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1516</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>18:55</StartTime>\n" +
            "<ArriveTime>21:20</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime78\" msdata:rowOrder=\"77\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH4906</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>18:55</StartTime>\n" +
            "<ArriveTime>21:35</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime79\" msdata:rowOrder=\"78\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1516</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>18:55</StartTime>\n" +
            "<ArriveTime>21:20</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime80\" msdata:rowOrder=\"79\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA156</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>18:55</StartTime>\n" +
            "<ArriveTime>21:35</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime81\" msdata:rowOrder=\"80\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5123</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>19:00</StartTime>\n" +
            "<ArriveTime>21:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime82\" msdata:rowOrder=\"81\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5197</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>19:00</StartTime>\n" +
            "<ArriveTime>21:15</ArriveTime>\n" +
            "<Mode>325</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12356</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime83\" msdata:rowOrder=\"82\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9887</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>19:00</StartTime>\n" +
            "<ArriveTime>21:15</ArriveTime>\n" +
            "<Mode>325</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12356</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime84\" msdata:rowOrder=\"83\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9910</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>19:00</StartTime>\n" +
            "<ArriveTime>21:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime85\" msdata:rowOrder=\"84\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1550</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>19:55</StartTime>\n" +
            "<ArriveTime>22:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>2356日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime86\" msdata:rowOrder=\"85\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1550</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>19:55</StartTime>\n" +
            "<ArriveTime>22:15</ArriveTime>\n" +
            "<Mode>JET</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>2356日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime87\" msdata:rowOrder=\"86\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5125</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:00</StartTime>\n" +
            "<ArriveTime>22:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime88\" msdata:rowOrder=\"87\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9912</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:00</StartTime>\n" +
            "<ArriveTime>22:25</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime89\" msdata:rowOrder=\"88\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5199</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:30</StartTime>\n" +
            "<ArriveTime>23:00</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime90\" msdata:rowOrder=\"89\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9926</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:30</StartTime>\n" +
            "<ArriveTime>23:00</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime91\" msdata:rowOrder=\"90\">\n" +
            "<Company>海南航空</Company>\n" +
            "<AirlineCode>HU7602</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:40</StartTime>\n" +
            "<ArriveTime>23:10</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime92\" msdata:rowOrder=\"91\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH4910</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:50</StartTime>\n" +
            "<ArriveTime>23:05</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>135日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime93\" msdata:rowOrder=\"92\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA176</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:50</StartTime>\n" +
            "<ArriveTime>23:05</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>135日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime94\" msdata:rowOrder=\"93\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1856</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:55</StartTime>\n" +
            "<ArriveTime>23:15</ArriveTime>\n" +
            "<Mode>744</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime95\" msdata:rowOrder=\"94\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA1856</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>20:55</StartTime>\n" +
            "<ArriveTime>23:15</ArriveTime>\n" +
            "<Mode>744</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>3</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime96\" msdata:rowOrder=\"95\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5127</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:00</StartTime>\n" +
            "<ArriveTime>23:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>345日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime97\" msdata:rowOrder=\"96\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9914</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:00</StartTime>\n" +
            "<ArriveTime>23:20</ArriveTime>\n" +
            "<Mode>333</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>12345</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime98\" msdata:rowOrder=\"97\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU5186</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:25</StartTime>\n" +
            "<ArriveTime>23:40</ArriveTime>\n" +
            "<Mode>320</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>135</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime99\" msdata:rowOrder=\"98\">\n" +
            "<Company>南方航空</Company>\n" +
            "<AirlineCode>CZ9886</AirlineCode>\n" +
            "<StartDrome>上海浦东国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:25</StartTime>\n" +
            "<ArriveTime>23:40</ArriveTime>\n" +
            "<Mode>320</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>135</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime100\" msdata:rowOrder=\"99\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU9107</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:30</StartTime>\n" +
            "<ArriveTime>23:45</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime101\" msdata:rowOrder=\"100\">\n" +
            "<Company>上海航空</Company>\n" +
            "<AirlineCode>FM9107</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:30</StartTime>\n" +
            "<ArriveTime>23:45</ArriveTime>\n" +
            "<Mode>738</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime102\" msdata:rowOrder=\"101\">\n" +
            "<Company>东方航空</Company>\n" +
            "<AirlineCode>MU3927</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:50</StartTime>\n" +
            "<ArriveTime>00:20</ArriveTime>\n" +
            "<Mode>320</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime103\" msdata:rowOrder=\"102\">\n" +
            "<Company>吉祥航空</Company>\n" +
            "<AirlineCode>HO1251</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:50</StartTime>\n" +
            "<ArriveTime>00:20</ArriveTime>\n" +
            "<Mode>320</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime104\" msdata:rowOrder=\"103\">\n" +
            "<Company>中国国航</Company>\n" +
            "<AirlineCode>CA5902</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:50</StartTime>\n" +
            "<ArriveTime>00:20</ArriveTime>\n" +
            "<Mode>320</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>123456日</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime105\" msdata:rowOrder=\"104\">\n" +
            "<Company>深圳航空</Company>\n" +
            "<AirlineCode>ZH1886</AirlineCode>\n" +
            "<StartDrome>上海虹桥国际机场</StartDrome>\n" +
            "<ArriveDrome>北京首都国际机场</ArriveDrome>\n" +
            "<StartTime>21:55</StartTime>\n" +
            "<ArriveTime>00:20</ArriveTime>\n" +
            "<Mode>330</Mode>\n" +
            "<AirlineStop>0</AirlineStop>\n" +
            "<Week>1234</Week>\n" +
            "</AirlinesTime>\n" +
            "<AirlinesTime diffgr:id=\"AirlinesTime106\" msdata:rowOrder=\"105\">\n" +
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
            "</Airlines>\n" +
            "</diffgr:diffgram>\n" +
            "</DataSet>";


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


    private static String tttconfig = "root.DataSet.diffgr:diffgram\n" +
            "diffgr:diffgram.Airlines.AirlinesTime\n" +
            "AirlinesTime.Company.string\n" +
            "AirlinesTime.AirlineCode.string\n";


    private static String testconfig = "root.Airlines(air).AirlinesTime\n" +
            "AirlinesTime.Company.string\n" +
            "AirlinesTime.AirlineCode.string\n";

    private static String name = "<string xmlns=\"http://WebXml.com.cn/\">1575175601：江苏 扬州 江苏移动全球通卡</string>";
    private static String nameconfig = "root.string.string\n";


    private static String array = "<ArrayOfString xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://WebXml.com.cn/\">\n" +
            "<string>直辖市 上海</string>\n" +
            "<string>上海</string>\n" +
            "<string>2013</string>\n" +
            "<string>2017/04/26 13:49:15</string>\n" +
            "<string>今日天气实况：气温：19℃；风向/风力：西北风 2级；湿度：69%</string>\n" +
            "<string>紫外线强度：最弱。空气质量：良。</string>\n" +
            "<string>\n" +
            "紫外线指数：最弱，辐射弱，涂擦SPF8-12防晒护肤品。 感冒指数：易发，大幅度降温，湿度大，易感冒。 穿衣指数：较舒适，建议穿薄外套或牛仔裤等服装。 洗车指数：不宜，有雨，雨水和泥水会弄脏爱车。 运动指数：较不宜，有降水，推荐您在室内进行休闲运动。 空气污染指数：良，气象条件有利于空气污染物扩散。\n" +
            "</string>\n" +
            "<string>4月26日 小雨转阴</string>\n" +
            "<string>14℃/20℃</string>\n" +
            "<string>东北风3-4级转北风微风</string>\n" +
            "<string>7.gif</string>\n" +
            "<string>2.gif</string>\n" +
            "<string>4月27日 多云转晴</string>\n" +
            "<string>14℃/22℃</string>\n" +
            "<string>北风转西风微风</string>\n" +
            "<string>1.gif</string>\n" +
            "<string>0.gif</string>\n" +
            "<string>4月28日 晴</string>\n" +
            "<string>16℃/27℃</string>\n" +
            "<string>西风转西南风微风</string>\n" +
            "<string>0.gif</string>\n" +
            "<string>0.gif</string>\n" +
            "<string>4月29日 多云</string>\n" +
            "<string>17℃/27℃</string>\n" +
            "<string>西南风微风</string>\n" +
            "<string>1.gif</string>\n" +
            "<string>1.gif</string>\n" +
            "<string>4月30日 多云转阴</string>\n" +
            "<string>18℃/28℃</string>\n" +
            "<string>南风转东北风微风</string>\n" +
            "<string>1.gif</string>\n" +
            "<string>2.gif</string>\n" +
            "</ArrayOfString>";
    //private static String arrayconfig = "root.ArrayOfString.list(string)\n";
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
                //TODO bug
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
        String palce = "{mobile}: {p} {city} {property}";
        Pattern pattern = Pattern.compile("([0-9]+): ([^ ]+) ([^ ]+) ([^ ]+)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.start());
        }
        sql = matcher.replaceAll("your_table");
        System.out.println(sql);
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> configmap = praseConfig(tttconfig);
        System.out.println(praseXMl(configmap, ttt));

//        String json = XmlUtil.xml2Json(name);
//        System.out.println(JsonUtils.toMap(json));


    }
}
