package com.jarvishub.emandemo.model;

import java.io.Serializable;

/**
 * Created by mrrobot on 10/15/17.
 */

public class CreateOrderInfo implements Serializable {
    private int category_id;
    private int service_id;
    private int weight_id;
    private String promo_code;
    private String collection_latitude;
    private String collection_longitude;
    private String delivery_latitude;
    private String delivery_longitude;
    private String delivery_datetime;
    private String collection_datetime;
    private String payment_method;
    private String collection_contact_name;
    private String collection_contact_number;
    private String collection_instructions;
    private String delivery_contact_name;
    private String delivery_contact_number;
    private String delivery_instructions;
    private String item_description;
    private String delivery_address;
    private String collection_address;

    private String packagePrice;
    private int packageTime;
    private String packageType;
    private int packageWeight;

    public CreateOrderInfo() {
    }

    public CreateOrderInfo(int service_id, int weight_id, String promo_code, String collection_latitude, String collection_longitude, String delivery_latitude, String delivery_longitude, String delivery_datetime, String collection_datetime, String payment_method, String collection_contact_name, String collection_contact_number, String collection_instructions, String delivery_contact_name, String delivery_contact_number, String delivery_instructions, String item_description) {
        this.service_id = service_id;
        this.weight_id = weight_id;
        this.promo_code = promo_code;
        this.collection_latitude = collection_latitude;
        this.collection_longitude = collection_longitude;
        this.delivery_latitude = delivery_latitude;
        this.delivery_longitude = delivery_longitude;
        this.delivery_datetime = delivery_datetime;
        this.collection_datetime = collection_datetime;
        this.payment_method = payment_method;
        this.collection_contact_name = collection_contact_name;
        this.collection_contact_number = collection_contact_number;
        this.collection_instructions = collection_instructions;
        this.delivery_contact_name = delivery_contact_name;
        this.delivery_contact_number = delivery_contact_number;
        this.delivery_instructions = delivery_instructions;
        this.item_description = item_description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }


    public int getWeight_id() {
        return weight_id;
    }

    public void setWeight_id(int weight_id) {
        this.weight_id = weight_id;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getCollection_latitude() {
        return collection_latitude;
    }

    public void setCollection_latitude(String collection_latitude) {
        this.collection_latitude = collection_latitude;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCollection_longitude() {
        return collection_longitude;
    }

    public void setCollection_longitude(String collection_longitude) {
        this.collection_longitude = collection_longitude;
    }

    public String getDelivery_latitude() {
        return delivery_latitude;
    }

    public void setDelivery_latitude(String delivery_latitude) {
        this.delivery_latitude = delivery_latitude;
    }

    public String getDelivery_longitude() {
        return delivery_longitude;
    }

    public void setDelivery_longitude(String delivery_longitude) {
        this.delivery_longitude = delivery_longitude;
    }

    public String getDelivery_datetime() {
        return delivery_datetime;
    }

    public void setDelivery_datetime(String delivery_datetime) {
        this.delivery_datetime = delivery_datetime;
    }

    public String getCollection_datetime() {
        return collection_datetime;
    }

    public void setCollection_datetime(String collection_datetime) {
        this.collection_datetime = collection_datetime;
    }


    public String getCollection_contact_name() {
        return collection_contact_name;
    }

    public void setCollection_contact_name(String collection_contact_name) {
        this.collection_contact_name = collection_contact_name;
    }

    public String getCollection_contact_number() {
        return collection_contact_number;
    }

    public void setCollection_contact_number(String collection_contact_number) {
        this.collection_contact_number = collection_contact_number;
    }

    public String getCollection_instructions() {
        return collection_instructions;
    }

    public void setCollection_instructions(String collection_instructions) {
        this.collection_instructions = collection_instructions;
    }

    public String getDelivery_contact_name() {
        return delivery_contact_name;
    }

    public void setDelivery_contact_name(String delivery_contact_name) {
        this.delivery_contact_name = delivery_contact_name;
    }

    public String getDelivery_contact_number() {
        return delivery_contact_number;
    }

    public void setDelivery_contact_number(String delivery_contact_number) {
        this.delivery_contact_number = delivery_contact_number;
    }

    public String getDelivery_instructions() {
        return delivery_instructions;
    }

    public void setDelivery_instructions(String delivery_instructions) {
        this.delivery_instructions = delivery_instructions;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public int getPackageTime() {
        return packageTime;
    }

    public void setPackageTime(int packageTime) {
        this.packageTime = packageTime;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getCollection_address() {
        return collection_address;
    }

    public void setCollection_address(String collection_address) {
        this.collection_address = collection_address;
    }
}
