package com.jarvishub.emandemo.data.prefs;

import android.content.SharedPreferences;

import com.jarvishub.emandemo.model.service_packages.ServicePackageData;

import java.util.HashMap;

/**
 * Created by mrrobot on 8/24/17.
 */

public interface IPreferencesHelper {

    void setIsWaitingForSms(boolean isWaiting);
    boolean isWaitingForSms();
    void setMobileNumber(String mobileNumber);
    String getMobileNumber();
    void setUserName(String userName);
    String getUserName();
    void setUserPassword(String userPassword);
    String getUserPassword();
    void createLogin(String name, String email, String mobile);
    boolean isLoggedIn();
    void setLoginStatus(Boolean status);
    void clearSession();
    void setDeviceId(String deviceId);
    void setAuthToken(String authToken);
    void saveServicePackagesData(String servicePackageData);
    void saveCollectionAddress(String collectionAddress);
    void saveDeliveryAddress(String deliveryAddress);
    void saveCreateOrderStatus(String status);
    String getCreateOrderStatus();
    String getCollectionAddress();
    String getDeliveryAddress();
    String getServicePackagesData();
    String getAuthToken();
    String getDeviceId();


    HashMap<String, String> getUserDetails();
}
