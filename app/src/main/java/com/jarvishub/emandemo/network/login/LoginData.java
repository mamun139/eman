package com.jarvishub.emandemo.network.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrrobot on 10/5/17.
 */

public class LoginData {
    @SerializedName("token")
    String token;


    @SerializedName("userInfo")
    private UserInfo userInfo;


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
}
