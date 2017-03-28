package com.we.ws.service.client;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-28
 */
public class RequestParam {
    private String name;
    private String value;

    public RequestParam() {
    }

    public RequestParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
