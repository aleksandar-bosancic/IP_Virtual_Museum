package com.museum.admin.Admin_App.beans;

import java.io.Serializable;

public class MediaBean implements Serializable {
    private static final long serialVersionUID = 6092597366098029519L;

    private int id;
    private int museumId;
    private String path;
    private boolean isVideo;

    @Override
    public String toString() {
        return "MediaBean{" +
                "museumId=" + museumId +
                ", path='" + path + '\'' +
                ", isVideo=" + isVideo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMuseumId() {
        return museumId;
    }

    public void setMuseumId(int museumId) {
        this.museumId = museumId;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
