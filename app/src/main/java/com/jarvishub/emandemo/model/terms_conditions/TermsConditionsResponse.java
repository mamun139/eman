package com.jarvishub.emandemo.model.terms_conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 11/15/2017.
 */

public class TermsConditionsResponse {
    @SerializedName("status")
    private Boolean tcStatus;
    @SerializedName("type")
    private String tcType;
    @SerializedName("message")
    private String tcMessage;
    @SerializedName("data")
    private  TermsConditionsData tcData;

    public Boolean getTcStatus() {
        return tcStatus;
    }

    public void setTcStatus(Boolean tcStatus) {
        this.tcStatus = tcStatus;
    }

    public String getTcType() {
        return tcType;
    }

    public void setTcType(String tcType) {
        this.tcType = tcType;
    }

    public String getTcMessage() {
        return tcMessage;
    }

    public void setTcMessage(String tcMessage) {
        this.tcMessage = tcMessage;
    }

    public TermsConditionsData getTcData() {
        return tcData;
    }

    public void setTcData(TermsConditionsData tcData) {
        this.tcData = tcData;
    }





}
