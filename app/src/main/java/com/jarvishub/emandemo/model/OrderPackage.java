package com.jarvishub.emandemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 10/11/2017.
 */

public class OrderPackage {
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;

    @SerializedName("message")
    private String message;
}
