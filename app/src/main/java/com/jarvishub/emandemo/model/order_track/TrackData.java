package com.jarvishub.emandemo.model.order_track;

/**
 * Created by Lenovo on 10/5/2017.
 */

public class TrackData {
    private int orderId;
    private String collectionZone;
    private String deliveryZone;
    private String collectionTime;
    private String status;
    private String date;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCollectionZone() {
        return collectionZone;
    }

    public void setCollectionZone(String collectionZone) {
        this.collectionZone = collectionZone;
    }

    public String getDeliveryZone() {
        return deliveryZone;
    }

    public void setDeliveryZone(String deliveryZone) {
        this.deliveryZone = deliveryZone;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }






}
