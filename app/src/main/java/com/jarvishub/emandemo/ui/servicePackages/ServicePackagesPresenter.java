package com.jarvishub.emandemo.ui.servicePackages;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.Order;
import com.jarvishub.emandemo.model.OrderInfo;
import com.jarvishub.emandemo.model.apply_promo.PromoCodeResponse;
import com.jarvishub.emandemo.model.service_packages.Packages;
import com.jarvishub.emandemo.model.service_packages.ServicePackageData;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.home.IMapView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mrrobot on 9/22/17.
 */

public class ServicePackagesPresenter implements IServicePackagesPresenter {

    private String TAG = "ServicePackagesPresent";
    @Inject
    DataManager mDataManager;
    APIService apiInterface;
    private IMapView mapView;
    private List<Packages> packagesList;

    public ServicePackagesPresenter(Context context) {
        ((EmanApp) context).getApplicationComponent().inject(this);
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
    }

    @Override
    public void setView(IMapView view) {
        this.mapView = view;
    }

    @Override
    public ServicePackageData getServicePackages() {
        String jsonPackage = mDataManager.getServicePackagesData();
        Log.d(TAG, "getServicePackages: .............................. "+jsonPackage);
        Gson gson = new Gson();
        ServicePackageData servicePackageData;
        servicePackageData = gson.fromJson(jsonPackage,ServicePackageData.class);
        return servicePackageData;
    }

    @Override
    public void createDraftOrder(CreateOrderInfo order) {
        /**
         * save oder in loval db
         */
        mapView.gotoCreateOrderFrag(order);
    }

    @Override
    public void applyPromo(String promoCode) {
        Call<PromoCodeResponse> call = apiInterface.applyPromoCode(promoCode);
        call.enqueue(new Callback<PromoCodeResponse>() {
            @Override
            public void onResponse(Call<PromoCodeResponse> call, Response<PromoCodeResponse> response) {
                Log.d(TAG, "onResponse: ....................................... ");
                if (response.isSuccessful()){
                    mapView.onPromoSuccess();
                }else {
                    mapView.onPromoFailed();
                }
            }

            @Override
            public void onFailure(Call<PromoCodeResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ...................................... "+t);
            }
        });


    }
}
