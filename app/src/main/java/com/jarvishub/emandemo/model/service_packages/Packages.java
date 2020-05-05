package com.jarvishub.emandemo.model.service_packages;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrrobot on 9/25/17.
 */

public class Packages {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("lead_time")
    private Integer leadTime;
    @SerializedName("description")
    private String description;
    @SerializedName("max_size")
    private Object maxSize;
    @SerializedName("max_weight")
    private Object maxWeight;
    @SerializedName("price")
    private Integer price;
    @SerializedName("enable")
    private Integer enable;
    @SerializedName("collection_min_time")
    private Integer collectionMinTime;
    @SerializedName("collection_max_time")
    private Integer collectionMaxTime;
    @SerializedName("delivery_min_time")
    private Integer deliveryMinTime;
    @SerializedName("delivery_max_time")
    private Integer deliveryMaxTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Object maxSize) {
        this.maxSize = maxSize;
    }

    public Object getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Object maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getCollectionMinTime() {
        return collectionMinTime;
    }

    public void setCollectionMinTime(Integer collectionMinTime) {
        this.collectionMinTime = collectionMinTime;
    }

    public Integer getCollectionMaxTime() {
        return collectionMaxTime;
    }

    public void setCollectionMaxTime(Integer collectionMaxTime) {
        this.collectionMaxTime = collectionMaxTime;
    }

    public Integer getDeliveryMinTime() {
        return deliveryMinTime;
    }

    public void setDeliveryMinTime(Integer deliveryMinTime) {
        this.deliveryMinTime = deliveryMinTime;
    }

    public Integer getDeliveryMaxTime() {
        return deliveryMaxTime;
    }

    public void setDeliveryMaxTime(Integer deliveryMaxTime) {
        this.deliveryMaxTime = deliveryMaxTime;
    }

    @Override
    public String toString() {
        return "Packages{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leadTime=" + leadTime +
                ", description='" + description + '\'' +
                ", maxSize=" + maxSize +
                ", maxWeight=" + maxWeight +
                ", price=" + price +
                ", enable=" + enable +
                ", collectionMinTime=" + collectionMinTime +
                ", collectionMaxTime=" + collectionMaxTime +
                ", deliveryMinTime=" + deliveryMinTime +
                ", deliveryMaxTime=" + deliveryMaxTime +
                '}';
    }
}
