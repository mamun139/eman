package com.jarvishub.emandemo.model.eman_list;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mrrobot on 10/3/17.
 */

public class EmanListResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<EmansLocationData> emansLocationsList;

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

    public List<EmansLocationData> getEmansLocationsList() {
        return emansLocationsList;
    }

    public void setEmansLocationsList(List<EmansLocationData> emansLocationsList) {
        this.emansLocationsList = emansLocationsList;
    }
}
