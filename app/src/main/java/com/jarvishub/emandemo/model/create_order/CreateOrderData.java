package com.jarvishub.emandemo.model.create_order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrrobot on 10/15/17.
 */

public class CreateOrderData {
    @SerializedName("order_id")
    int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
