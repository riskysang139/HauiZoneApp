package com.example.hauizone.Notification;

public class Notification {
    private int id;
    private String type;
    private String content;
    private String date;
    private String time;
    private String imageNotification;

    public Notification() {
    }

    public Notification(int id, String type, String date, String time, String content, String imageNotification) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageNotification = imageNotification;
    }
    public Notification(String type, String date, String time, String content, String imageNotification) {
        this.type = type;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageNotification = imageNotification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageNotification() {
        return imageNotification;
    }

    public void setImageNotification(String imageNotification) {
        this.imageNotification = imageNotification;
    }

}
