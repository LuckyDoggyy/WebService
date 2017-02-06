package com.we.ws.common.exception;

/**
 * Created by twogoods on 16/10/16.
 */
public class BpTransactionException extends RuntimeException{
    public BpTransactionException(String message){
        super(message);
    }

    public BpTransactionException(String message,Throwable e){
        super(message,e);
    }
}
