package com.example.hauizone.Account;

public class User {
    int userId;
    String userName;
    String password;
    String name;
    String dateOfBirth;
    String gender;
    String userProvince;
    String userDistrict;
    String userWard;
    String userStreet;
    String phoneNumber;
    String email;
    String epidemic;
    int flag;

    public User() {
    }

    public User(int userId, String userName, String password, String name, String dateOfBirth, String gender, String userProvince, String userDistrict, String userWard, String userStreet, String phoneNumber, String email, String epidemic, int flag) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.userProvince = userProvince;
        this.userDistrict = userDistrict;
        this.userWard = userWard;
        this.userStreet = userStreet;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.epidemic = epidemic;
        this.flag = flag;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserDistrict() {
        return userDistrict;
    }

    public void setUserDistrict(String userDistrict) {
        this.userDistrict = userDistrict;
    }

    public String getUserWard() {
        return userWard;
    }

    public void setUserWard(String userWard) {
        this.userWard = userWard;
    }

    public String getUserStreet() {
        return userStreet;
    }

    public void setUserStreet(String userStreet) {
        this.userStreet = userStreet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEpidemic() {
        return epidemic;
    }

    public void setEpidemic(String epidemic) {
        this.epidemic = epidemic;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
