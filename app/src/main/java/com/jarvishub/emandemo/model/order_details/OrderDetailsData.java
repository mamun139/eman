package com.jarvishub.emandemo.model.order_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 11/9/2017.
 */

public class OrderDetailsData {
    @SerializedName("order_id")
    private int orderId;
    @SerializedName("order_ref")
    private String orderRef;
    @SerializedName("order_date")
    private String orderDate;
    @SerializedName("order_status")
    private String orderStatus;
    @SerializedName("parcel_status")
    private String parcelStatus;
    @SerializedName("price")
    private String price;
    @SerializedName("discount_value")
    private String discountValue;
    @SerializedName("payment_amount")
    private String paymentAmount;
    @SerializedName("payment_method")
    private String paymentMethod;

    @SerializedName("collection_address")
    private String collectionAddress;
    @SerializedName("collection_contact_name")
    private String collectionContactName;
    @SerializedName("collection_datetime")
    private String collectionDatetime;
    @SerializedName("collection_contact_number")
    private  String collectionContactNumber;
    @SerializedName("delivery_address")
    private String deliveryAddress;
    @SerializedName("delivery_contact_name")
    private String deliveryContactName;
    @SerializedName("delivery_datetime")
    private String deliveryDatetime;
    @SerializedName("delivery_contact_number")
    private String deliveryContactNumber;

    public String getCollectionAddress() {
        return collectionAddress;
    }

    public void setCollectionAddress(String collectionAddress) {
        this.collectionAddress = collectionAddress;
    }

    public String getCollectionContactName() {
        return collectionContactName;
    }

    public void setCollectionContactName(String collectionContactName) {
        this.collectionContactName = collectionContactName;
    }

    public String getCollectionDatetime() {
        return collectionDatetime;
    }

    public void setCollectionDatetime(String collectionDatetime) {
        this.collectionDatetime = collectionDatetime;
    }

    public String getCollectionContactNumber() {
        return collectionContactNumber;
    }

    public void setCollectionContactNumber(String collectionContactNumber) {
        this.collectionContactNumber = collectionContactNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDatetime() {
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(String deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }

    public String getDeliveryContactNumber() {
        return deliveryContactNumber;
    }

    public void setDeliveryContactNumber(String deliveryContactNumber) {
        this.deliveryContactNumber = deliveryContactNumber;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(String parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethos) {
        this.paymentMethod = paymentMethos;
    }



    public String getDeliveryContactName() {
        return deliveryContactName;
    }

    public void setDeliveryContactName(String deliveryContactName) {
        this.deliveryContactName = deliveryContactName;
    }
}
