package com.jarvishub.emandemo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.data.prefs.PreferencesHelper;
import com.jarvishub.emandemo.model.eman_list.EmanListResponse;
import com.jarvishub.emandemo.model.eman_list.EmansLocationData;
import com.jarvishub.emandemo.model.service_packages.ServicePackageData;
import com.jarvishub.emandemo.model.service_packages.ServicePackagesResponse;
import com.jarvishub.emandemo.model.sign_out.SignOutResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.network.CoOrdinates;
import com.jarvishub.emandemo.ui.login.LoginActivity;
import com.jarvishub.emandemo.utils.AppConstants;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mrrobot on 9/20/17.
 */

public class MapPresenter implements IMapPresenter {

    private static final String TAG = "MapPresenter";

    @Inject
    DataManager dataManager;

    private APIService apiInterface ;
    private IMapView mapView;

    public MapPresenter(Context context) {
        ((EmanApp) context).getApplicationComponent().inject(this);
        apiInterface = new ApiClient().getClient(dataManager.getAuthToken()).create(APIService.class);
    }

    @Override
    public void setView(IMapView view) {
        this.mapView = view;
    }

    @Override
    public void sendLocation(HashMap<String, Double> coOrdinates) {
        CoOrdinates coOrdinatesObj = new CoOrdinates();

        HashMap<String,Double> collection = new HashMap<>();
        HashMap<String,Double> delivery = new HashMap<>();
        collection.put(AppConstants.LATITUDE,coOrdinates.get(HomeActivity.PICKUP_LATITUDE));
        collection.put(AppConstants.LONGITUDE,coOrdinates.get(HomeActivity.PICKUP_LATITUDE));
        delivery.put("latitude",coOrdinates.get(HomeActivity.DELIVERY_LATITUDE));
        delivery.put("longitude",coOrdinates.get(HomeActivity.DELIVERY_LONGITUDE));
        coOrdinatesObj.setCollection_address(collection);
        coOrdinatesObj.setDelivery_address(delivery);

        Log.d(TAG, "sendLocation: ............... pickup " + coOrdinates.get(HomeActivity.PICKUP_LATITUDE) + " longitude " + coOrdinates.get(HomeActivity.PICKUP_LONGITUDE));
        Log.d(TAG, "sendLocation: ............... delivery " + coOrdinates.get(HomeActivity.DELIVERY_LATITUDE) + " longitude " + coOrdinates.get(HomeActivity.DELIVERY_LONGITUDE));

        getServicePackages(coOrdinatesObj);
        // Log.d(TAG, "sendLocation: .............................. auth token"+dataManager.getAuthToken());

    }

    /**
     *
     * @param latitude clients latutude
     * @param longitude clients longitude
     *
     *                  sends the co-ordinates to server and retrieve nearest emans location
     *                  and send those to map for showing marker
     */
    @Override
    public void getActiveEmanList(Double latitude, Double longitude) {

        HashMap<String, Double> userCoOrdinate = new HashMap<>();
        userCoOrdinate.put("user_latitude", latitude);
        userCoOrdinate.put("user_longitude", longitude);

        Gson gson = new Gson();
        Log.d(TAG, "getActiveEmanList: .......................... user coOrdinate json " + gson.toJson(userCoOrdinate));

        Call<EmanListResponse> call = apiInterface.emanList(userCoOrdinate);
        call.enqueue(new Callback<EmanListResponse>() {
            @Override
            public void onResponse(Call<EmanListResponse> call, Response<EmanListResponse> response) {
                if (response.isSuccessful()) {
                    List<EmansLocationData> emansLocationList = response.body().getEmansLocationsList();

                    if (!emansLocationList.isEmpty())
                        mapView.showMarkerAt(emansLocationList);
                    Log.d(TAG, "onResponse: ............................. "+ response.body().toString() + response.body().toString());
                } else {
                    Log.e(TAG, "onResponse: ...................................." + "Server Down!!!");
                }
            }

            @Override
            public void onFailure(Call<EmanListResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: ......................... active eman list failed");
            }
        });
    }

    /*
    * requesting web server for service packages
    * */
    @Override
    public void getServicePackages(CoOrdinates coOrdinatesObj) {

        final Gson gson = new Gson();
        Log.d(TAG, "getServicePackages: ..........................  coOrdinate json " + gson.toJson(coOrdinatesObj));

        Call<ServicePackagesResponse> call = apiInterface.getServicePackages(coOrdinatesObj);
        call.enqueue(new Callback<ServicePackagesResponse>() {
            @Override
            public void onResponse(Call<ServicePackagesResponse> call, Response<ServicePackagesResponse> response) {
                ServicePackageData servicePackageData;
                if (response.isSuccessful()){
                    servicePackageData = response.body().getData();
                    String jsonData = gson.toJson(servicePackageData);
                    dataManager.saveServicePackagesData(jsonData);
                    Log.d(TAG, "onResponse: .......................... json res "+jsonData);
                    mapView.gotoServicePackagesFrag();
                }else {
                    Log.e(TAG, "onResponse: ...............................      failed to collect service package data from  server");
                }
            }

            @Override
            public void onFailure(Call<ServicePackagesResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: ....................................... onFailure");
            }
        });
    }

    @Override
    public void doLogOut() {
        apiInterface = new ApiClient().getClient(dataManager.getAuthToken()).create(APIService.class);
        Call<SignOutResponse> call = apiInterface.getSignOut();
        call.enqueue(new Callback<SignOutResponse>() {
            @Override
            public void onResponse(Call<SignOutResponse> call, Response<SignOutResponse> response) {

                if (response.isSuccessful()){
                    dataManager.setAuthToken(PreferencesHelper.DEFAULT_RETURN_VALUE);
                    dataManager.setLoginStatus(false);
                    mapView.afterLogOut();
                }
                else {
                    Log.e(TAG, "onResponse: ........................... logout failed" );
                }
            }
            @Override
            public void onFailure(Call<SignOutResponse> call, Throwable t) {

            }
        });

    }
}
