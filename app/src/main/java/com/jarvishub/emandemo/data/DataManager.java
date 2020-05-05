package com.jarvishub.emandemo.data;

import android.content.Context;
import android.content.res.Resources;

import com.jarvishub.emandemo.data.db.DbHelper;
import com.jarvishub.emandemo.data.prefs.PreferencesHelper;
import com.jarvishub.emandemo.di.custom_annotation.ApplicationContext;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.model.service_packages.Packages;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mrrobot on 8/28/17.
 */
@Singleton
public class DataManager implements IDataManager{
    private Context mContext;
    private DbHelper mDbHelper;
    private PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       PreferencesHelper preferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public HashMap<String, String> getUserDetails() {
        return null;
    }


    @Override
    public void setIsWaitingForSms(boolean isWaiting) {

    }

    @Override
    public boolean isWaitingForSms() {
        return false;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        mPreferencesHelper.setMobileNumber(mobileNumber);
    }

    public void setUserName(String userName){
        mPreferencesHelper.setUserName(userName);
    }
    public String getUserName(){
        return mPreferencesHelper.getUserName();
    }

    @Override
    public void setUserPassword(String userPassword) {
        mPreferencesHelper.setUserPassword(userPassword);
    }

    @Override
    public String getUserPassword() {
        return mPreferencesHelper.getUserPassword();
    }

    @Override
    public String getMobileNumber() {
        return mPreferencesHelper.getMobileNumber();
    }

    @Override
    public void createLogin(String name, String email, String mobile) {

    }

    @Override
    public boolean isLoggedIn() {
        return mPreferencesHelper.isLoggedIn();
    }

    @Override
    public void setLoginStatus(Boolean status) {
        mPreferencesHelper.setLoginStatus(status);
    }

    @Override
    public void clearSession() {

    }

    @Override
    public void setDeviceId(String deviceId) {
        mPreferencesHelper.setDeviceId(deviceId);
    }

    @Override
    public void setAuthToken(String authToken) {
        mPreferencesHelper.setAuthToken(authToken);
    }

    @Override
    public void saveServicePackagesData(String servicePackageData) {
        mPreferencesHelper.saveServicePackagesData(servicePackageData);
    }

    @Override
    public void saveCollectionAddress(String collectionAddress) {
        mPreferencesHelper.saveCollectionAddress(collectionAddress);
    }

    @Override
    public void saveDeliveryAddress(String deliveryAddress) {
        mPreferencesHelper.saveDeliveryAddress(deliveryAddress);
    }

    @Override
    public void saveCreateOrderStatus(String status) {
        mPreferencesHelper.saveCreateOrderStatus(status);
    }

    @Override
    public String getCreateOrderStatus() {
        return mPreferencesHelper.getCreateOrderStatus();
    }

    @Override
    public String getCollectionAddress() {
        return mPreferencesHelper.getCollectionAddress();
    }

    @Override
    public String getDeliveryAddress() {
        return mPreferencesHelper.getDeliveryAddress();
    }

    @Override
    public String getServicePackagesData() {
        return mPreferencesHelper.getServicePackagesData();
    }

    public String getAuthToken() {
        return mPreferencesHelper.getAuthToken();
    }

    @Override
    public String getDeviceId() {
        return mPreferencesHelper.getDeviceId();
    }


    @Override
    public Long insertUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    @Override
    public User getUser(String mobile) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(mobile);
    }

/*    @Override
    public Long insertServicePackages(Packages packages) throws Exception {
        return null;
    }

    @Override
    public Packages getServicePacksFromLoc() throws Resources.NotFoundException, NullPointerException {
        return null;
    }*/

}
