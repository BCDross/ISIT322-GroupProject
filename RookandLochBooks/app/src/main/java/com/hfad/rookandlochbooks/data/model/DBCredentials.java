package com.hfad.rookandlochbooks.data.model;

public class DBCredentials {
    Integer SecurityID;
    String TEXT;
    Integer UserID;

    public DBCredentials(Integer securityID, String TEXT, Integer userID) {
        SecurityID = securityID;
        this.TEXT = TEXT;
        UserID = userID;
    }

    public Integer getSecurityID() {
        return SecurityID;
    }

    public void setSecurityID(Integer securityID) {
        SecurityID = securityID;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }
}
