package com.jarvishub.emandemo.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 10/15/2017.
 */

public class SignUpData {
    @SerializedName("token")
    String token;

    @SerializedName("userInfo")
    private UserInfo userInfo;

    @SerializedName("email")
    String failedEmail;

    @SerializedName("mobile")
    String failedMobile;

    @SerializedName("dob")
    String failedDob;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getFailedEmail() {
        return failedEmail;
    }

    public void setFailedEmail(String failedEmail) {
        this.failedEmail = failedEmail;
    }

    public String getFailedMobile() {
        return failedMobile;
    }

    public void setFailedMobile(String failedMobile) {
        this.failedMobile = failedMobile;
    }

    public String getFailedDob() {
        return failedDob;
    }

    public void setFailedDob(String failedDob) {
        this.failedDob = failedDob;
    }
}
