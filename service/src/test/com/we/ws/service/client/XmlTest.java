package com.we.ws.service.client;

import org.junit.Test;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-03
 */
public class XmlTest {


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
            "<ArrayOfString xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://WebXml.com.cn/\">\n" +
            "<string>直辖市 上海</string>\n" +
            "<string>今日天气实况：气温：19℃；风向/风力：西北风 2级；湿度：69%</string>\n" +
            "</ArrayOfString>" +
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
            "<ArrayOfString xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://WebXml.com.cn/\">\n" +
            "<string>直辖市 上海</string>\n" +
            "<string>今日天气实况：气温：19℃；风向/风力：西北风 2级；湿度：69%</string>\n" +
            "</ArrayOfString>" +
            "</AirlinesTime>\n" +
            "</Airlines>\n" +
            "</diffgr:diffgram>\n" +
            "</DataSet>";

    private static String tttconfig = "root.DataSet.DataSet\n" +
            "DataSet.diffgr:diffgram.diffgr:diffgram\n" +
            "diffgr:diffgram.Airlines(air).Airlines\n" +
            "Airlines.AirlinesTime.AirlinesTime\n" +
            "AirlinesTime.Company.string\n" +
            "AirlinesTime.ArrayOfString.ArrayOfString\n" +
            "ArrayOfString.string(weather).string\n" +
            "AirlinesTime.AirlineCode.string\n";

    @Test
    public void test1() throws Exception {
        Map<String, Object> configmap = Xml.parseConfig(tttconfig);
        System.out.println(configmap);
        Map<String,Object> result=Xml.parseXMl(configmap, ttt);
        System.out.println(result);
    }

    private static String name = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soap:Body>\n" +
            "    <getMobileCodeInfoResponse xmlns=\"http://WebXml.com.cn/\">\n" +
            "      <getMobileCodeInfoResult>1575175601：江苏 扬州 江苏移动全球通卡</getMobileCodeInfoResult>\n" +
            "    </getMobileCodeInfoResponse>\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";
    private static String nameconfig = "root.soap:Envelope.soap:Envelope\n" +
            "soap:Envelope.soap:Body.soap:Body\n" +
            "soap:Body.getMobileCodeInfoResponse.getMobileCodeInfoResponse\n" +
            "getMobileCodeInfoResponse.getMobileCodeInfoResult.string.[{mobile}：{province} {city} {property}]\n";

    @Test
    public void test2() throws Exception {
        Map<String, Object> configmap = Xml.parseConfig(nameconfig);
        System.out.println(configmap);
        System.out.println(Xml.parseXMl(configmap, name));

    }

}