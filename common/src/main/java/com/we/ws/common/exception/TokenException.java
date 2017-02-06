package com.we.ws.common.exception;

/**
 * Created by twogoods on 16/10/11.
 */
public class TokenException extends Exception {
    public TokenException(String message){
        super(message);
    }
    public TokenException(String message,Throwable e){
        super(message,e);
    }
}
