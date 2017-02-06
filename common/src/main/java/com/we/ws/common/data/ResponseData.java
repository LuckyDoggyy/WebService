package com.we.ws.common.data;

/**
 * Created by twogoods on 16/10/12.
 */
public class ResponseData<T> {
    private int code;
    private String msg;
    private T result;

    public ResponseData() {
    }

    public ResponseData(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ResponseData(int code, T result) {
        this.code = code;
        this.result = result;
    }

    public ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
