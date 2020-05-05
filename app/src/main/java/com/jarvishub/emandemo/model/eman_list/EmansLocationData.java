package com.jarvishub.emandemo.model.eman_list;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrrobot on 10/3/17.
 */

public class EmansLocationData {

    @SerializedName("eman_id")
    private int emanId;
    @SerializedName("lat")
    private Double latitute;
    @SerializedName("long")
    private Double longitude;
    @SerializedName("status")
    private String availibilityStatus;

    public int getEmanId() {
        return emanId;
    }

    public void setEmanId(int emanId) {
        this.emanId = emanId;
    }

    public Double getLatitute() {
        return latitute;
    }

    public void setLatitute(Double latitute) {
        this.latitute = latitute;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAvailibilityStatus() {
        return availibilityStatus;
    }

    public void setAvailibilityStatus(String availibilityStatus) {
        this.availibilityStatus = availibilityStatus;
    }
}
