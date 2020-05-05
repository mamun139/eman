package com.jarvishub.emandemo.network;

import com.google.gson.annotations.SerializedName;


public class SignUpResponse {

    @SerializedName("status")
    private String clientId;

    @SerializedName("type")
    private String authToken;

    @SerializedName("message")
    private String deviceId;

    @SerializedName("signUpData")
    private SignUpData signUpData;


    public SignUpResponse(String clientId, String authToken, String deviceId) {
        this.clientId = clientId;
        this.authToken = authToken;
        this.deviceId = deviceId;
    }

    public SignUpData getSignUpData() {
        return signUpData;
    }

    public void setSignUpData(SignUpData signUpData) {
        this.signUpData = signUpData;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
