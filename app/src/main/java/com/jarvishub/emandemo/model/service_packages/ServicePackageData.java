package com.jarvishub.emandemo.model.service_packages;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mrrobot on 10/12/17.
 */

public class ServicePackageData implements Serializable {
    @SerializedName("standard")
    private GenericCategory standard;
    @SerializedName("emergency")
    private GenericCategory emergency;
    @SerializedName("prime")
    private GenericCategory prime;
    @SerializedName("off_day")
    private GenericCategory offDay;
    @SerializedName("value")
    private GenericCategory value;

    public GenericCategory getStandard() {
        return standard;
    }

    public void setStandard(GenericCategory standard) {
        this.standard = standard;
    }

    public GenericCategory getEmergency() {
        return emergency;
    }

    public void setEmergency(GenericCategory emergency) {
        this.emergency = emergency;
    }

    public GenericCategory getPrime() {
        return prime;
    }

    public void setPrime(GenericCategory prime) {
        this.prime = prime;
    }

    public GenericCategory getOffDay() {
        return offDay;
    }

    public void setOffDay(GenericCategory offDay) {
        this.offDay = offDay;
    }

    public GenericCategory getValue() {
        return value;
    }

    public void setValue(GenericCategory value) {
        this.value = value;
    }
}
