package com.museum.admin.application.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class SessionBean implements Serializable {
    private static final long serialVersionUID = 7747601247352671198L;

    private int id;
    private String username;
    private String sessionKey;
    private Timestamp validUntil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }
}
