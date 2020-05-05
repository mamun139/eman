package com.jarvishub.emandemo.model.apply_promo;

import com.google.gson.annotations.SerializedName;

/**
 * Created with love by mrrobot on 11/9/17.
 */

public class PromoCodeResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")

    private String message;
    @SerializedName("data")
    private PromoCodeData data;

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

    public PromoCodeData getData() {
        return data;
    }

    public void setData(PromoCodeData data) {
        this.data = data;
    }

}
