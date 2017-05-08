package com.we.ws.common.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-25
 */
public class XmlUtil {
    public static String xml2Json(String xml) throws JSONException{
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();
    }
}
