package com.we.ws.admin.domain;

/**
 * Created by twogoods on 17/2/19.
 */
public class UserRole {
    private long autoid;
    private long uid;
    private String account;
    private int rid;
    private String rolename;
    private int state;

    public long getAutoid() {
        return autoid;
    }

    public void setAutoid(long autoid) {
        this.autoid = autoid;
    }

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

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "autoid=" + autoid +
                ", uid=" + uid +
                ", account='" + account + '\'' +
                ", rid=" + rid +
                ", rolename='" + rolename + '\'' +
                ", state=" + state +
                '}';
    }
}
