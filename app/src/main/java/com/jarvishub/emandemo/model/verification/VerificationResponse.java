package com.jarvishub.emandemo.model.verification;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ruhul on 8/31/17.
 */

public class VerificationResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private VerificationData data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public VerificationData getData() {
        return data;
    }

    public void setData(VerificationData data) {
        this.data = data;
    }
}
