package com.jarvishub.emandemo.data.prefs;

import android.content.SharedPreferences;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mrrobot on 8/24/17.
 */

@Singleton
public class PreferencesHelper implements IPreferencesHelper {

    // Shared preferences file name
    public static final String PREF_NAME = "EMANPREFS";
    public static final String DEFAULT_RETURN_VALUE = "0";

    // All Shared Preferences Keys
    public static final String PREF_KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
    public static final String PREF_KEY_MOBILE_NUMBER = "mobile_number";
    public static final String PREF_KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String PREF_KEY_USER_NAME = "name";
    public static final String PREF_KEY_USER_EMAIL = "email";
    public static final String PREF_KEY_USER_MOBILE = "mobile";
    public static final String PREF_KEY_USER_PASSWORD = "password";
    public static final String PREF_KEY_DEVICE_ID = "generatedDeviceId";
    public static final String PREF_KEY_SERVICE_PACKAGE = "servicePackageData";
    public static final String PREF_KEY_COLLECTION_ADDRESS = "collectionAddress";
    public static final String PREF_KEY_DELIVERY_ADDRESS = "deliveryAddress";
    public static final String PREF_KEY_CREATE_ORDER_STATUS = "createOderStatus";
    public static final String PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN";

    public static final String MY_CART_LIST_LOCAL = "MyCartItems";

    private final SharedPreferences mPreferenceHelper;

    @Inject
    public PreferencesHelper(SharedPreferences sharedPreferences) {
        mPreferenceHelper = sharedPreferences;
    }

    @Override
    public void setIsWaitingForSms(boolean isWaiting) {
        mPreferenceHelper.edit().putBoolean(PREF_KEY_IS_WAITING_FOR_SMS, isWaiting).apply();
    }

    @Override
    public boolean isWaitingForSms() {
        return mPreferenceHelper.getBoolean(PREF_KEY_IS_WAITING_FOR_SMS, false);
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        mPreferenceHelper.edit().putString(PREF_KEY_MOBILE_NUMBER, mobileNumber).apply();
    }

    @Override
    public String getMobileNumber() {
        return mPreferenceHelper.getString(PREF_KEY_MOBILE_NUMBER, null);
    }

    @Override
    public void setUserName(String userName) {
        mPreferenceHelper.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }

    @Override
    public String getUserName(){
        return mPreferenceHelper.getString(PREF_KEY_USER_NAME,null);
    }

    @Override
    public void setUserPassword(String userPassword) {
        mPreferenceHelper.edit().putString(PREF_KEY_USER_PASSWORD, userPassword).apply();

    }

    @Override
    public String getUserPassword() {
        return mPreferenceHelper.getString(PREF_KEY_USER_PASSWORD,null);
    }


    @Override
    public void createLogin(String name, String email, String mobile) {
        mPreferenceHelper.edit().putString(PREF_KEY_USER_NAME, name).apply();
        mPreferenceHelper.edit().putString(PREF_KEY_USER_EMAIL, email).apply();
        mPreferenceHelper.edit().putString(PREF_KEY_USER_MOBILE, mobile).apply();
    }

    @Override
    public boolean isLoggedIn() {
        return mPreferenceHelper.getBoolean(PREF_KEY_IS_LOGGED_IN, false);
    }

    @Override
    public void setLoginStatus(Boolean status) {
        mPreferenceHelper.edit().putBoolean(PREF_KEY_IS_LOGGED_IN,status).apply();
    }

    @Override
    public void clearSession() {
        mPreferenceHelper.edit().clear();
    }

    @Override
    public void setDeviceId(String deviceId) {
        mPreferenceHelper.edit().putString(PREF_KEY_DEVICE_ID, deviceId).apply();
    }

    @Override
    public void setAuthToken(String authToken) {
        mPreferenceHelper.edit().putString(PREF_KEY_AUTH_TOKEN, authToken).apply();
    }

    @Override
    public void saveServicePackagesData(String servicePackageData) {
        mPreferenceHelper.edit().putString(PREF_KEY_SERVICE_PACKAGE, servicePackageData).apply();
    }

    @Override
    public void saveCollectionAddress(String collectionAddress) {
        mPreferenceHelper.edit().putString(PREF_KEY_COLLECTION_ADDRESS, collectionAddress).apply();
    }

    @Override
    public void saveDeliveryAddress(String deliveryAddress) {
        mPreferenceHelper.edit().putString(PREF_KEY_DELIVERY_ADDRESS, deliveryAddress).apply();
    }

    @Override
    public void saveCreateOrderStatus(String status) {
        mPreferenceHelper.edit().putString(PREF_KEY_CREATE_ORDER_STATUS, status).apply();
    }

    @Override
    public String getCreateOrderStatus() {
        return mPreferenceHelper.getString(PREF_KEY_CREATE_ORDER_STATUS, DEFAULT_RETURN_VALUE);

    }

    @Override
    public String getCollectionAddress() {
        return mPreferenceHelper.getString(PREF_KEY_COLLECTION_ADDRESS, DEFAULT_RETURN_VALUE);
    }

    @Override
    public String getDeliveryAddress() {
        return mPreferenceHelper.getString(PREF_KEY_DELIVERY_ADDRESS, DEFAULT_RETURN_VALUE);
    }

    @Override
    public String getServicePackagesData() {
        return mPreferenceHelper.getString(PREF_KEY_SERVICE_PACKAGE, DEFAULT_RETURN_VALUE);
    }

    @Override
    public String getAuthToken() {
        return mPreferenceHelper.getString(PREF_KEY_AUTH_TOKEN, DEFAULT_RETURN_VALUE);
    }

    @Override
    public String getDeviceId() {
        return mPreferenceHelper.getString(PREF_KEY_DEVICE_ID, DEFAULT_RETURN_VALUE);
    }

    @Override
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
        profile.put(PREF_KEY_USER_NAME, mPreferenceHelper.getString(PREF_KEY_USER_NAME, null));
        profile.put(PREF_KEY_USER_EMAIL, mPreferenceHelper.getString(PREF_KEY_USER_EMAIL, null));
        profile.put(PREF_KEY_USER_MOBILE, mPreferenceHelper.getString(PREF_KEY_USER_MOBILE, null));
        return profile;
    }
}
