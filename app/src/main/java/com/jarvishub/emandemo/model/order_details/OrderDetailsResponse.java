package com.jarvishub.emandemo.model.order_details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lenovo on 11/9/2017.
 */

public class OrderDetailsResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<OrderDetailsData> data;
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

    public List<OrderDetailsData> getData() {
        return data;
    }

    public void setData(List<OrderDetailsData> data) {
        this.data = data;
    }




}
