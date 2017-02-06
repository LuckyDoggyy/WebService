package com.we.ws.common.constants;

/**
 * Created by twogoods on 16/10/11.
 */
public enum ResponseCode {
    SUCCESS(200,"ok"),INTERNALERROR(500,"internal error"),
    FAIL(400,"request failed"),UNAUTHORIZED(401,"Unauthorized");


    private int code;
    private String mean;

    ResponseCode(int code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String mean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code=" + code +
                ", mean='" + mean + '\'' +
                '}';
    }
}
