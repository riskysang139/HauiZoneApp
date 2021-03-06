package com.example.hauizone.ScanQR;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataYourRoute implements Serializable {
    private int id;
    private String name;
    private String address;
    private String address_des;
    private String address_go;
    private String day_des;
    private String day_go;
    private int idUsername;

    public DataYourRoute(int id, String name, String address, String address_des, String address_go, String day_des, String day_go, int idUsername) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.address_des = address_des;
        this.address_go = address_go;
        this.day_des = day_des;
        this.day_go = day_go;
        this.idUsername = idUsername;
    }

    public DataYourRoute(int id, String name, String address, String address_des, String address_go, String day_des, String day_go) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.address_des = address_des;
        this.address_go = address_go;
        this.day_des = day_des;
        this.day_go = day_go;
    }

    public DataYourRoute(String name, String address, String address_des, String address_go, String day_des, String day_go) {

        this.name = name;
        this.address = address;
        this.address_des = address_des;
        this.address_go = address_go;
        this.day_des = day_des;
        this.day_go = day_go;
    }

    public DataYourRoute(String name, String address, String address_des, String address_go, String day_des, String day_go, int idUsername) {
        this.name = name;
        this.address = address;
        this.address_des = address_des;
        this.address_go = address_go;
        this.day_des = day_des;
        this.day_go = day_go;
        this.idUsername = idUsername;
    }

    public int getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(int idUsername) {
        this.idUsername = idUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_des() {
        return address_des;
    }

    public void setAddress_des(String address_des) {
        this.address_des = address_des;
    }

    public String getAddress_go() {
        return address_go;
    }

    public void setAddress_go(String address_go) {
        this.address_go = address_go;
    }

    public String getDay_des() {
        return day_des;
    }

    public void setDay_des(String day_des) {
        this.day_des = day_des;
    }

    public String getDay_go() {
        return day_go;
    }

    public void setDay_go(String day_go) {
        this.day_go = day_go;
    }
}
