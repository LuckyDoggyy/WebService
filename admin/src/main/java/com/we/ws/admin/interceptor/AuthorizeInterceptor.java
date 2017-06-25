package com.we.ws.admin.interceptor;

import com.we.ws.common.constants.ResponseCode;
import com.we.ws.common.data.Pair;
import com.we.ws.common.data.ResponseData;
import com.we.ws.common.exception.TokenException;
import com.we.ws.common.util.JsonUtils;
import com.we.ws.common.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {
    private Logger log = LoggerFactory.getLogger(AuthorizeInterceptor.class);
    @Autowired
    private Environment env;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----" + env.getProperty("profile") + "----" + request.getRequestURI());
        if ("dev".equals(env.getProperty("profile"))) {
            log.debug("dev profile,not check...");
            return true;
        }
        log.info("do interceptor...");
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            out(request, response, new ResponseData<Object>(ResponseCode.UNAUTHORIZED.code(), "未授权", null));
            return false;
        }
        Pair<Boolean, String> checkResult = checkCoookies(cookies, response);
        if (checkResult.getL()) {
            return true;
        } else {
            out(request, response, new ResponseData<Object>(ResponseCode.UNAUTHORIZED.code(), checkResult.getR(), null));
            return false;
        }
    }

    private Pair<Boolean, String> checkCoookies(Cookie[] cookies, HttpServletResponse response) {
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
            return TokenUtils.ValidationTokenAndRefresh(userid, token, response);
        } catch (TokenException e) {
            log.warn("token exception:{}", e);
            return Pair.of(false, e.getMessage());
        }
    }

    private void outJsonP(HttpServletRequest request, HttpServletResponse response, Object obj) {
        String content = JsonUtils.jsonFromObject(obj);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");
        try {
            String callback = request.getParameter("callback").toString();
            if (callback != null && callback.trim().length() > 0) {
                content = callback + "&&" + callback + "(" + content + ");";
            }
        } catch (Exception e) {
            log.error("error:{}", e);
        }
        try {
            response.getWriter().write(content);
        } catch (IOException e) {
            log.error("error:{}", e);
        }
    }

    private void out(HttpServletRequest request, HttpServletResponse response, Object obj) {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("utf-8");
        try {
            JsonUtils.writeValue(response.getWriter(), obj);
        } catch (IOException e) {
            log.error("io异常", e);
        }
    }
}

