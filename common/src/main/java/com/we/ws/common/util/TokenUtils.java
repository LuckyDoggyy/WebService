package com.we.ws.common.util;

import com.we.ws.common.data.Pair;
import com.we.ws.common.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by twogoods on 16/10/12.
 */
public class TokenUtils {
    private static Logger log= LoggerFactory.getLogger(TokenUtils.class);

    private static long limitTime=1000*60*90;

    public static String generateToken(Object key) throws TokenException {
        try {
            return AESUtils.Encrypt("ws-" + System.currentTimeMillis() + "-" + key);
        } catch (Exception e) {
            log.error("生成token异常", e);
            throw new TokenException("生成token异常", e);
        }
    }

    public static Pair<Boolean,String> ValidationToken(Object key,String token) throws TokenException {
        try {
            String original=AESUtils.Decrypt(token);
            String[] arr=original.split("-");
            if(arr.length!=3){
                return Pair.of(false,"token错误");
            }
            log.debug("time:{};id:{}",arr[1],arr[2]);
            if(System.currentTimeMillis()- Long.parseLong(arr[1])>limitTime){
                return Pair.of(false,"token过期");
            }
            if(!arr[2].equals(key.toString())){
                return Pair.of(false,"token无法匹配");
            }
        } catch (NumberFormatException e) {
            throw new TokenException("不是有效token");
        }catch (Exception e){
            throw new TokenException("校验失败");
        }
        return Pair.of(true,"校验成功");
    }
}
