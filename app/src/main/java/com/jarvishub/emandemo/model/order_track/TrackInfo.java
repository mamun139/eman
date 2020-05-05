package com.jarvishub.emandemo.model.order_track;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 10/4/2017.
 */

public class TrackInfo {
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<TrackData> data = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public List<TrackData> getData() {
        return data;
    }

    public void setData(List<TrackData> data) {
        this.data = data;
    }


}


