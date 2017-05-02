package com.we.ws.service.client;

import org.junit.Test;

import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-02
 */
public class XmlParserTest {

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
            "</ArrayOfString>" +
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
            "<ArrayOfString xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://WebXml.com.cn/\">\n" +
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
            "</ArrayOfString>" +
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
            "<ArrayOfString xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://WebXml.com.cn/\">\n" +
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
            "</ArrayOfString>" +
            "</AirlinesTime>\n" +
            "</Airlines>\n" +
            "</diffgr:diffgram>\n" +
            "</DataSet>";

    private static String tttconfig = "root.DataSet.diffgr:diffgram\n" +
            "diffgr:diffgram.Airlines(air).AirlinesTime\n" +
            "AirlinesTime.Company.string\n" +
            "AirlinesTime.ArrayOfString(weather).list\n" +
            "list.string.string\n" +
            "AirlinesTime.AirlineCode.string\n";

    @Test
    public void test1() throws Exception {
        Map<String, Object> configmap = XmlParser.parseConfig(tttconfig);
        System.out.println(configmap);
        System.out.println(XmlParser.parseXMl(configmap, ttt));
    }


    private static String name = "<string xmlns=\"http://WebXml.com.cn/\">1575175601：江苏 扬州 江苏移动全球通卡</string>";
    private static String nameconfig = "root.string.string.[{mobile}：{province} {city} {property}]\n";

    @Test
    public void test2() throws Exception {
        Map<String, Object> configmap = XmlParser.parseConfig(nameconfig);
        System.out.println(configmap);
        System.out.println(XmlParser.parseXMl(configmap, name));
    }

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
    private static String arrayconfig = "root.ArrayOfString(weather).list\nlist.string.string\n";

    @Test
    public void test3() throws Exception {
        Map<String, Object> configmap = XmlParser.parseConfig(arrayconfig);
        System.out.println(configmap);
        System.out.println(XmlParser.parseXMl(configmap, array));
    }

}