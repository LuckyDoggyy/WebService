package com.we.ws.admin.controller;

import com.we.ws.common.constants.ResponseCode;
import com.we.ws.common.constants.ResponseKeys;
import com.we.ws.common.data.Pair;
import com.we.ws.common.data.ResponseData;
import com.we.ws.common.exception.TokenException;
import com.we.ws.common.util.JsonUtils;
import com.we.ws.common.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
public class BaseController {
    private Logger log= LoggerFactory.getLogger(BaseController.class);

    @Autowired private Environment env;

    protected long validateOperateAuthoriz(HttpServletRequest request,long requestUid){
        if (!"dev".equals(env.getProperty("profile"))) {
            log.debug("requestUid:{}--dev profile,not check...", requestUid);
            return requestUid;
        }
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return requestUid;
        }
        for(Cookie c:cookies){
            if ("uid".equals(c.getName())) {
                return Long.parseLong(c.getValue());
            }
        }
        return requestUid;
    }
    protected long validateOperateAuthoriz(HttpServletRequest request,String requestUid){
        return validateOperateAuthoriz(request,Long.parseLong(requestUid));
    }

    protected String getUserid(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie c:cookies){
            if ("uid".equals(c.getName())) {
                return c.getValue();
            }
        }
        return "0";
    }

    protected Pair<Boolean, String> checkCoookies(HttpServletRequest request) {
        Cookie[] cookies=request.getCookies();
        String token = "", userid = "";
        for (Cookie c : cookies) {
            if ("token".equals(c.getName())) {
                token = c.getValue();
            }
            if ("uid".equals(c.getName())) {
                userid = c.getValue();
            }
        }
        try {
            return TokenUtils.ValidationToken(userid, token);
        } catch (TokenException e) {
            log.warn("token exception:{}", e);
            return Pair.of(false, e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    protected void handleException(HttpServletRequest request,HttpServletResponse response, Exception e) {
        log.error("异常:{}", e);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put(ResponseKeys.CODE, ResponseCode.INTERNALERROR.code());
        map.put(ResponseKeys.MSG, e.getMessage());
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }
    @ExceptionHandler(NumberFormatException.class)
    protected void handleNumberFormatException(HttpServletRequest request,HttpServletResponse response, Exception e) {
        log.error("异常:{}", e);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put(ResponseKeys.CODE, ResponseCode.FAIL.code());
        map.put(ResponseKeys.MSG, "请检查参数值是否正确");
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }

    protected <T> ResponseEntity<ResponseData<T>> failEntity(String message,T object){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.FAIL.code(),message,object), HttpStatus.OK);
    }
    protected <T> ResponseEntity<ResponseData<T>> failEntity(String message,Class<T> clazz){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.FAIL.code(),message),HttpStatus.OK);
    }
    protected <T> ResponseEntity<ResponseData<T>> frequentControllEntity(Class<T> clazz){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.FAIL.code(),"您的操作太快了~"),HttpStatus.OK);
    }
    protected <T> ResponseEntity<ResponseData<T>> authorizControllEntity(Class<T> clazz){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.FAIL.code(),"无权执行此操作"),HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseData<T>> successEntity(T object){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.SUCCESS.code(),object),HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseData<T>> successEntity(String message,Class<T> clazz){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.SUCCESS.code(),message),HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseData<T>> successEntity(String message,T object){
        return new ResponseEntity<ResponseData<T>>(new ResponseData<T>(ResponseCode.SUCCESS.code(),message,object),HttpStatus.OK);
    }

    protected Map<String,Object> failMap(String message){
        Map<String,Object> map=new HashMap<>();
        map.put(ResponseKeys.CODE, ResponseCode.FAIL.code());
        map.put(ResponseKeys.MSG, message);
        return map;
    }

    protected Map<String,Object> successMap(){
        Map<String,Object> map=new HashMap<>();
        map.put(ResponseKeys.CODE, ResponseCode.SUCCESS.code());
        return map;
    }

    protected Map<String,Object> successMap(String message){
        Map<String,Object> map=successMap();
        map.put(ResponseKeys.MSG, message);
        return map;
    }

    protected void errorJson(HttpServletRequest request,HttpServletResponse response,String msg){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put(ResponseKeys.CODE, ResponseCode.FAIL.code());
        map.put(ResponseKeys.MSG, msg);
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }

    protected void errorJson(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
        map.put(ResponseKeys.CODE, ResponseCode.FAIL.code());
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }

    protected void returnJson(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }

    protected void successJson(HttpServletRequest request,HttpServletResponse response,String msg){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put(ResponseKeys.CODE, ResponseCode.SUCCESS.code());
        map.put(ResponseKeys.MSG, msg);
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }
    protected void successJson(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
        map.put(ResponseKeys.CODE, ResponseCode.SUCCESS.code());
        outJsonP(response, request.getParameter("callback"), JsonUtils.jsonFromObject(map));
    }
    protected void successCROS(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
        map.put(ResponseKeys.CODE, ResponseCode.SUCCESS.code());
        outJsonWithCORS(response,JsonUtils.jsonFromObject(map));
    }

    protected void outJson(HttpServletResponse response, String content) {
        OutputStream os = null;
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            byte[] bs = content.getBytes("utf-8");
            response.setContentLength(bs.length);
            os = response.getOutputStream();
            os.write(bs);
            os.flush();
        } catch (Exception exp) {
            exp.printStackTrace();
            response.setStatus(407);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected void outJsonP(HttpServletResponse response, String callback,
                            String content) {
        OutputStream os = null;
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            if (callback != null && callback.trim().length() > 0) {
                content = callback + "&&" + callback + "(" + content + ");";
            }
            byte[] bs = content.getBytes("utf-8");
            response.setContentLength(bs.length);
            os = response.getOutputStream();
            os.write(bs);
            os.flush();
            os.close();
            os = null;
        } catch (Exception exp) {
            exp.printStackTrace();
            log.error("输出流错误", exp);
            response.setStatus(407);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected void outJsonWithCORS(HttpServletResponse response, String content) {
        OutputStream os = null;
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Access-Control-Allow-Origin","*");
            byte[] bs = content.getBytes("utf-8");
            response.setContentLength(bs.length);
            os = response.getOutputStream();
            os.write(bs);
            os.flush();
            os.close();
            os = null;
        } catch (Exception exp) {
            exp.printStackTrace();
            log.error("输出流错误",exp);
            response.setStatus(407);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected void outText(HttpServletResponse response, String content) {

        PrintWriter pw = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-length", "" + content.getBytes("UTF-8").length);
            pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception exp) {
            exp.printStackTrace();
            response.setStatus(407);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    protected Map<String, Object> parseParams(HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map map = request.getParameterMap();
        for (Object key : map.keySet()) {
            paramsMap.put(key.toString(), request.getParameter(key.toString()));
        }
        return paramsMap;
    }


    protected String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.format(new Date());
        } catch (Exception e) {
        }
        return "";
    }

    protected String formatDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(new Date());
        } catch (Exception e) {
        }
        return "";
    }
}

