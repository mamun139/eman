package com.jarvishub.emandemo.model.service_packages;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ruhul on 11/17/17.
 */

public class PackageResponse {
    @SerializedName("weight")
    private String weight;
    @SerializedName("service")
    private List<Packages> packageList = null;
    @SerializedName("size")
    private Integer size;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<Packages> getService() {
        return packageList;
    }

    public void setService(List<Packages> service) {
        this.packageList = service;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
