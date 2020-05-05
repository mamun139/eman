package com.jarvishub.emandemo.model.service_packages;

import com.google.gson.annotations.SerializedName;

/**
 * Created with love by mrrobot on 11/19/17.
 */

public class ServicesData {
    @SerializedName("below_500")
    private PackageResponse below500;
    @SerializedName("upto_1kg")
    private PackageResponse upto1kg;
    @SerializedName("upto_2kg")
    private PackageResponse upto2kg;

    public PackageResponse getBelow500() {
        return below500;
    }

    public void setBelow500(PackageResponse below500) {
        this.below500 = below500;
    }

    public PackageResponse getUpto1kg() {
        return upto1kg;
    }

    public void setUpto1kg(PackageResponse upto1kg) {
        this.upto1kg = upto1kg;
    }

    public PackageResponse getUpto2kg() {
        return upto2kg;
    }

    public void setUpto2kg(PackageResponse upto2kg) {
        this.upto2kg = upto2kg;
    }

}
