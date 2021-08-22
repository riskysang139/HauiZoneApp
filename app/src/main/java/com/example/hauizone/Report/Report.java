package com.example.hauizone.Report;

import java.io.Serializable;

public class Report implements Serializable {
    int idReport;
    String timeDetectReport;
    String nameReport;
    String sdtReport;
    String province,district,ward,street;
    String typeReport;
    String contentReport;
    int idUser;

    public Report(int idReport, String timeDetectReport, String nameReport, String sdtReport, String province, String district, String ward, String street, String typeReport, String contentReport,int idUser) {
        this.idReport = idReport;
        this.timeDetectReport = timeDetectReport;
        this.nameReport = nameReport;
        this.sdtReport = sdtReport;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this.typeReport = typeReport;
        this.contentReport = contentReport;
        this.idUser = idUser;
    }

    public Report(String timeDetectReport, String nameReport, String sdtReport, String province, String district, String ward, String street, String typeReport, String contentReport,int idUser) {
        this.timeDetectReport = timeDetectReport;
        this.nameReport = nameReport;
        this.sdtReport = sdtReport;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this.typeReport = typeReport;
        this.contentReport = contentReport;
        this.idUser = idUser;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getTimeDetectReport() {
        return timeDetectReport;
    }

    public void setTimeDetectReport(String timeDetectReport) {
        this.timeDetectReport = timeDetectReport;
    }

    public String getNameReport() {
        return nameReport;
    }

    public void setNameReport(String nameReport) {
        this.nameReport = nameReport;
    }

    public String getSdtReport() {
        return sdtReport;
    }

    public void setSdtReport(String sdtReport) {
        this.sdtReport = sdtReport;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }

    public String getContentReport() {
        return contentReport;
    }

    public void setContentReport(String contentReport) {
        this.contentReport = contentReport;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Report{" +
                "idReport=" + idReport +
                ", timeDetectReport='" + timeDetectReport + '\'' +
                ", nameReport='" + nameReport + '\'' +
                ", sdtReport='" + sdtReport + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", street='" + street + '\'' +
                ", typeReport='" + typeReport + '\'' +
                ", contentReport='" + contentReport + '\'' +
                '}';
    }
}
