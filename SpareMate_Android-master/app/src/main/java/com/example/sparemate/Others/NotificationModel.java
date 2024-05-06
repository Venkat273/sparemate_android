package com.example.sparemate.Others;

public class NotificationModel {
    private String name;
    private String days;
    private int imageId;

    public NotificationModel(String name, String days, int imageId) {
        this.name = name;
        this.days = days;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getDays() {
        return days;
    }

    public int getImageId() {
        return imageId;
    }

}

