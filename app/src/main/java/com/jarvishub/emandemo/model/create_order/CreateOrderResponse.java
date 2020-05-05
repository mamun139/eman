package com.jarvishub.emandemo.model.create_order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrrobot on 10/15/17.
 */

public class CreateOrderResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private CreateOrderData data;

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

    public CreateOrderData getData() {
        return data;
    }

    public void setData(CreateOrderData data) {
        this.data = data;
    }
}
