package com.we.ws.admin.domain;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-26
 */
public class Menu {

    private int autoid;
    private String mid;
    private String menuname;
    private String pid;
    private int order;

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
