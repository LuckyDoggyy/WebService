package com.we.ws.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(JsonUtils.class);


    public static String jsonFromObject(Object object) {
        String str;
        try {
            str = JSON.toJSONString(object);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unable to serialize to json: " + object, e);
            return null;
        }
        return str;
    }

    public static String jsonFromObject(Object object, String dateFormat) {
        String str;
        try {
            str = JSON.toJSONStringWithDateFormat(object, dateFormat);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unable to serialize to json: " + object, e);
            return null;
        }
        return str;
    }

    public static <T> T objectFromJson(String json, Class<T> klass) {
        T object;
        try {
            object = JSON.parseObject(json, klass);
        } catch (RuntimeException e) {
            log.error("Runtime exception during deserializing " + klass.getSimpleName() + " from "
                    + StringUtils.abbreviate(json, 80));
            throw e;
        } catch (Exception e) {
            log.error("Exception during deserializing " + klass.getSimpleName() + " from "
                    + StringUtils.abbreviate(json, 80));
            return null;
        }
        return object;
    }

    public static <T> T objectFromJson(String json, TypeReference<T> klass) {
        T object;
        try {// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
            object = JSON.parseObject(json, klass);
        } catch (RuntimeException e) {
            log.error("Runtime exception during deserializing from " + StringUtils.abbreviate(json, 80));
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception during deserializing from " + StringUtils.abbreviate(json, 80));
            return null;
        }
        return object;
    }

    public static <T> List<T> listFromJson(String json, Class<T> klass) {
        List<T> object;
        try {
            object = JSON.parseArray(json, klass);
        } catch (RuntimeException e) {
            log.error("Runtime exception during deserializing from " + StringUtils.abbreviate(json, 80));
            throw e;
        }
        return object;
    }

    public static Map toMap(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    public static void writeValue(Writer writer, Object value) {
        JSON.writeJSONStringTo(value, writer);
    }

    public static void writeValue(HttpServletResponse response,Object value) throws IOException{
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //response.setHeader("Cache-Control", "no-cache");
        JSON.writeJSONStringTo(value, response.getWriter());
    }
}
