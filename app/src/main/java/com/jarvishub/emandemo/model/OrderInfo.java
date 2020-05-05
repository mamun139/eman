package com.jarvishub.emandemo.model;

/**
 * Created by Lenovo on 10/5/2017.
 */

public class OrderInfo {
    private int order_id;
    private String order_ref;
    private String order_date;
    private String order_status;
    private String parcel_status;
    private String price;
    private String discount_value;
    private String discount_type;
    private String payment_amount;
    private String payment_method;
    private String pickup_address;
    private String delivered_address;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_ref() {
        return order_ref;
    }

    public void setOrder_ref(String order_ref) {
        this.order_ref = order_ref;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getParcel_status() {
        return parcel_status;
    }

    public void setParcel_status(String parcel_status) {
        this.parcel_status = parcel_status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getDelivered_address() {
        return delivered_address;
    }

    public void setDelivered_address(String delivered_address) {
        this.delivered_address = delivered_address;
    }


}
