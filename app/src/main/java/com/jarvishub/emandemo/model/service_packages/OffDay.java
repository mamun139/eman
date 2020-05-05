package com.jarvishub.emandemo.model.service_packages;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created with love by mrrobot on 11/15/17.
 */

public class OffDay {
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("category_description")
    private String categoryDescription;
    @SerializedName("data")
    private ServicesData data;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public ServicesData getData() {
        return data;
    }

    public void setData(ServicesData data) {
        this.data = data;
    }
}
