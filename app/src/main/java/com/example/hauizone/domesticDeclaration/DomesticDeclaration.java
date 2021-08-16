package com.example.hauizone.domesticDeclaration;

public class DomesticDeclaration {
    private int id;
    private String ckKH;
    private String vehicle;
    private String departure;
    private String destination;
    private String name;
    private String dateOfBirth;
    private String numberPassport;
    private String sex;
    private String contactCity;
    private String contactDistrict;
    private String contactTown;
    private String contactAddress;
    private String numberPhone;
    private String sympton;
    private String covidContact;
    private int idUsername;

    public DomesticDeclaration(int id, String ckKH, String vehicle, String departure, String destination, String name, String dateOfBirth, String numberPassport, String sex, String contactCity, String contactDistrict, String contactTown, String contactAddress, String numberPhone, String sympton, String covidContact, int idUsername) {
        this.id = id;
        this.ckKH = ckKH;
        this.vehicle = vehicle;
        this.departure = departure;
        this.destination = destination;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.numberPassport = numberPassport;
        this.sex = sex;
        this.contactCity = contactCity;
        this.contactDistrict = contactDistrict;
        this.contactTown = contactTown;
        this.contactAddress = contactAddress;
        this.numberPhone = numberPhone;
        this.sympton = sympton;
        this.covidContact = covidContact;
        this.idUsername = idUsername;
    }

    public DomesticDeclaration(String ckKH, String vehicle, String departure, String destination, String name, String dateOfBirth, String numberPassport, String sex, String contactCity, String contactDistrict, String contactTown, String contactAddress, String numberPhone, String sympton, String covidContact, int idUsername) {
        this.ckKH = ckKH;
        this.vehicle = vehicle;
        this.departure = departure;
        this.destination = destination;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.numberPassport = numberPassport;
        this.sex = sex;
        this.contactCity = contactCity;
        this.contactDistrict = contactDistrict;
        this.contactTown = contactTown;
        this.contactAddress = contactAddress;
        this.numberPhone = numberPhone;
        this.sympton = sympton;
        this.covidContact = covidContact;
        this.idUsername = idUsername;
    }

    public String getCkKH() {
        return ckKH;
    }

    public void setCkKH(String ckKH) {
        this.ckKH = ckKH;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getSympton() {
        return sympton;
    }

    public void setSympton(String sympton) {
        this.sympton = sympton;
    }

    public String getCovidContact() {
        return covidContact;
    }

    public void setCovidContact(String covidContact) {
        this.covidContact = covidContact;
    }

    public int getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(int idUsername) {
        this.idUsername = idUsername;
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

    @Override
    public String toString() {
        return "DomesticDeclaration{" +
                "id=" + id +
                ", vehicle='" + vehicle + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", numberPassport='" + numberPassport + '\'' +
                ", sex='" + sex + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", sympton='" + sympton + '\'' +
                ", covidContact='" + covidContact + '\'' +
                ", idUsername=" + idUsername +
                '}';
    }
}
