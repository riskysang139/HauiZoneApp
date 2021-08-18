package com.example.hauizone.entryDeclaration;

public class EntryDeclaration {
    private int id;
    private String gate;
    private String name;
    private String dateOfBirth;
    private String sex;
    private String nationality;
    private String date;
    private String contactCity;
    private String contactDistrict;
    private String contactTown;
    private String contactAddress;
    private String phoneNumber;
    private int idUser;

    public EntryDeclaration() {
    }

    public EntryDeclaration(int id, String gate, String name, String dateOfBirth, String sex, String nationality, String date, String contactCity, String contactDistrict, String contactTown, String contactAddress, String phoneNumber, int idUser) {
        this.id = id;
        this.gate = gate;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.nationality = nationality;
        this.date = date;
        this.contactCity = contactCity;
        this.contactDistrict = contactDistrict;
        this.contactTown = contactTown;
        this.contactAddress = contactAddress;
        this.phoneNumber = phoneNumber;
        this.idUser = idUser;
    }

    public EntryDeclaration(String gate, String name, String dateOfBirth, String sex, String nationality, String date, String contactCity, String contactDistrict, String contactTown, String contactAddress, String phoneNumber, int idUser) {
        this.gate = gate;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.nationality = nationality;
        this.date = date;
        this.contactCity = contactCity;
        this.contactDistrict = contactDistrict;
        this.contactTown = contactTown;
        this.contactAddress = contactAddress;
        this.phoneNumber = phoneNumber;
        this.idUser = idUser;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContactCity() {
        return contactCity;
    }

    public void setContactCity(String contactCity) {
        this.contactCity = contactCity;
    }

    public String getContactDistrict() {
        return contactDistrict;
    }

    public void setContactDistrict(String contactDistrict) {
        this.contactDistrict = contactDistrict;
    }

    public String getContactTown() {
        return contactTown;
    }

    public void setContactTown(String contactTown) {
        this.contactTown = contactTown;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
