package com.jarvishub.emandemo.network;

import java.util.HashMap;

/**
 * Created by mrrobot on 10/2/17.
 */

public class CoOrdinates {
    private HashMap<String, Double> collection_address;
    private HashMap<String, Double> delivery_address;

    public HashMap<String, Double> getCollection_address() {
        return collection_address;
    }

    public void setCollection_address(HashMap<String, Double> collection_address) {
        this.collection_address = collection_address;
    }

    public HashMap<String, Double> getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(HashMap<String, Double> delivery_address) {
        this.delivery_address = delivery_address;
    }
}
