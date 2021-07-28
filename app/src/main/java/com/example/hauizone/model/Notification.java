package com.example.hauizone.model;

public class Notification {
    private String type;
    private String content;
    private String date;
    private String time;
    private int imageNotification;

    public Notification(String type, String content, String date, String time, int imageNotification) {
        this.type = type;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageNotification = imageNotification;
    }

    public Notification() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageNotification() {
        return imageNotification;
    }

    public void setImageNotification(int imageNotification) {
        this.imageNotification = imageNotification;
    }
}
