package com.jarvishub.emandemo.model.verification;

import com.google.gson.annotations.SerializedName;

/**
 * Created with love by mrrobot on 11/24/17.
 */

public class VerificationData {
    @SerializedName("status")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
