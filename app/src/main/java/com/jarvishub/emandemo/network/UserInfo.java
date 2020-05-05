package com.jarvishub.emandemo.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 10/15/2017.
 */

class UserInfo {
    @SerializedName("id")
    private int userId;
    @SerializedName("type")
    private String userType;
    @SerializedName("username")
    private String userName;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("job_title")
    private String jobTititle;
    @SerializedName("email")
    private String UserEmail;
    @SerializedName("mobile")
    private String userMobile;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTititle() {
        return jobTititle;
    }

    public void setJobTititle(String jobTititle) {
        this.jobTititle = jobTititle;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}
