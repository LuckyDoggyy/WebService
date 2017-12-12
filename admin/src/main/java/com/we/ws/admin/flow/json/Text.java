package com.we.ws.admin.flow.json;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class Text {
    private String text;
    private String value;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() { return text; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
