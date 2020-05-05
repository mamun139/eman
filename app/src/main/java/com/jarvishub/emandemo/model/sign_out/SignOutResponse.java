package com.jarvishub.emandemo.model.sign_out;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 11/16/2017.
 */

public class SignOutResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
