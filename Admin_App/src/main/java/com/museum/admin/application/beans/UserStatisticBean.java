package com.museum.admin.application.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserStatisticBean implements Serializable {
    private static final long serialVersionUID = -2392913830585573606L;

    private int id;
    private Timestamp timeActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(Timestamp timeActive) {
        this.timeActive = timeActive;
    }
}
