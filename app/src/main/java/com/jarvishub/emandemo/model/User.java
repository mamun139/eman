package com.jarvishub.emandemo.model;


import java.io.Serializable;

public class User implements Serializable {

    private String deviceId;
    private String firstName;
    private String surName;
    private String email;
    private String password;
    private String created_at;
    private String newPassword;
    private String token;
    private String mobile;
    private String address;
    private String gender;
    private String dob;
    private String type;

    public User(String mobile, String password) {
        this.password = password;
        this.mobile = mobile;
    }

    public User() {
    }

    public User(String firstName, String email, String password, String mobile, String address) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
    }

    public User(String firstName, String surName, String email, String password, String mobile, String gender, String dob, String type) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.dob = dob;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.firstName = name;
    }
}
