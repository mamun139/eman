package com.jarvishub.emandemo.model.terms_conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 11/15/2017.
 */

public class TermsConditionsData {
    @SerializedName("id")
    private int tcId;
    @SerializedName("title")
    private String tcTitle;
    @SerializedName("slug")
    private String tcSlug;
    @SerializedName("contents")
    private String tcContents;

    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public String getTcTitle() {
        return tcTitle;
    }

    public void setTcTitle(String tcTitle) {
        this.tcTitle = tcTitle;
    }

    public String getTcSlug() {
        return tcSlug;
    }

    public void setTcSlug(String tcSlug) {
        this.tcSlug = tcSlug;
    }

    public String getTcContents() {
        return tcContents;
    }

    public void setTcContents(String tcContents) {
        this.tcContents = tcContents;
    }
}
