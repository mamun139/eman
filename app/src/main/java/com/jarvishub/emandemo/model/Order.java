package com.jarvishub.emandemo.model;

/**
 * Created by mrrobot on 9/26/17.
 */

public class Order {
    private int orderId;
    private String senderName;
    private String senderMobile;
    private String receiverName;
    private String receiverMobile;
    private String pickUpLocation;
    private String deliveryLocation;
    private String packageName;
    private String packageSize;
    private String maxWeight;
    private String leadTime;
    private String amountPayable;
    private String parcelPickUpTime;
    private String deliveryTime;
    private String paymentPolicy;

    public Order() {
    }

    public Order(int orderId, String senderName, String senderMobile, String receiverName, String receiverMobile, String pickUpLocation, String deliveryLocation, String packageName, String packageSize, String maxWeight, String leadTime, String amountPayable, String parcelPickUpTime, String deliveryTime, String paymentPolicy) {
        this.orderId = orderId;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(String amountPayable) {
        this.amountPayable = amountPayable;
    }

    public String getParcelPickUpTime() {
        return parcelPickUpTime;
    }

    public void setParcelPickUpTime(String parcelPickUpTime) {
        this.parcelPickUpTime = parcelPickUpTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPaymentPolicy() {
        return paymentPolicy;
    }

    public void setPaymentPolicy(String paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
    }
}
