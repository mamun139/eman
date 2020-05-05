package com.jarvishub.emandemo.model.service_packages;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrrobot on 10/9/17.
 */

public class ServicePackagesResponse {

    @SerializedName("status")
    private Boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ServicePackageData data;

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

    public ServicePackageData getData() {
        return data;
    }

    public void setData(ServicePackageData data) {
        this.data = data;
    }
}
