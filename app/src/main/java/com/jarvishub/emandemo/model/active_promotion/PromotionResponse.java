package com.jarvishub.emandemo.model.active_promotion;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lenovo on 11/17/2017.
 */

public class PromotionResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<PromotionData> data;

    public boolean getStatus() {
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

    public List<PromotionData> getData() {
        return data;
    }

    public void setData(List<PromotionData> data) {
        this.data = data;
    }


}
