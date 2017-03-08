package com.we.ws.admin.domain;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-02
 */
public class User {
    private long uid;
    private String account;
    private String password;
    private String nickName;
    private String remarks;
    private int state;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", state=" + state +
                '}';
    }
}
