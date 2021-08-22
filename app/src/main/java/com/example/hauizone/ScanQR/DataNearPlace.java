package com.example.hauizone.ScanQR;

public class DataNearPlace {
    private String imgNearPlace;
    private String nameNearPlace;
    private String distance;

    public DataNearPlace(String imgNearPlace, String nameNearPlace, String distance) {
        this.imgNearPlace = imgNearPlace;
        this.nameNearPlace = nameNearPlace;
        this.distance = distance;
    }

    public String getImgNearPlace() {
        return imgNearPlace;
    }

    public void setImgNearPlace(String imgNearPlace) {
        this.imgNearPlace = imgNearPlace;
    }

    public String getNameNearPlace() {
        return nameNearPlace;
    }

    public void setNameNearPlace(String nameNearPlace) {
        this.nameNearPlace = nameNearPlace;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
