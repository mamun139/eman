package com.jarvishub.emandemo.model;

import java.util.ArrayList;

/**
 * Created by Lenovo on 10/4/2017.
 */

public class TrackOrder {
    private String status;
    private String type;
    private String message;
    private String datetime;


    private ArrayList<TrackInfo> data = new ArrayList<>();


    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<TrackInfo> getData() {
        return data;
    }

    public void setData(ArrayList<TrackInfo> data) {
        this.data = data;
    }

    public class TrackInfo {
        private String status;
        private String datetime;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }

}

