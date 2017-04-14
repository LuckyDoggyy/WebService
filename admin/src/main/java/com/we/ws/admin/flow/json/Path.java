package com.we.ws.admin.flow.json;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public class Path {
    private String from;
    private String to;
    private Text text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getText() {
        return text.getText();
    }
}
