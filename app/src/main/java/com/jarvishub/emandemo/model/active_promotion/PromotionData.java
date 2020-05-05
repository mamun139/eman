package com.jarvishub.emandemo.model.active_promotion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 11/17/2017.
 */

public class PromotionData {
    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private String code;
    @SerializedName("discount")
    private String discount;
    @SerializedName("max_use")
    private int maxUse;
    @SerializedName("max_discount")
    private String maxDiscount;
    @SerializedName("description")
    private String description;
    @SerializedName("service_id")
    private int serviceId;
    @SerializedName("expires_at")
    private String expiresAt;
    @SerializedName("client_id")
    private int clientId;
    @SerializedName("used_time")
    private int usedTime;
    @SerializedName("added_on")
    private String addedOn;
    @SerializedName("left_use_time")
    private int promoLeft;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getMaxUse() {
        return maxUse;
    }

    public void setMaxUse(int maxUse) {
        this.maxUse = maxUse;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(int usedTime) {
        this.usedTime = usedTime;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public int getPromoLeft() {
        return promoLeft;
    }

    public void setPromoLeft(int promoLeft) {
        this.promoLeft = promoLeft;
    }
}
