package com.jarvishub.emandemo.ui.createOrder;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.create_order.CreateOrderResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.home.IMapView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mrrobot on 10/2/17.
 */

public class CreateOrderPresenter implements ICreateOrderPresenter {
    private IMapView mapView;
    //private ICreateOrderView createOrderView;
    @Inject
    DataManager mDataManager;
    APIService apiInterface;
    Context context;

    private final String TAG = "CreateOrderPresenter";

    public CreateOrderPresenter(Context context) {
        ((EmanApp) context).getApplicationComponent().inject(this);
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
    }

    @Override
    public void setView(IMapView view) {
        this.mapView = view;
    }

    @Override
    public void sendOrderDataToServer(CreateOrderInfo createOrderInfo) {
        Gson g = new Gson();
        Log.d(TAG, "sendOrderDataToServer: ......................................... " + g.toJson(createOrderInfo));

        Call<CreateOrderResponse> call = apiInterface.createOrder(
                createOrderInfo);
        //calling the api
        call.enqueue(new Callback<CreateOrderResponse>() {
            @Override
            public void onResponse(Call<CreateOrderResponse> call, Response<CreateOrderResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()){
                        Log.d(TAG, "onResponse: ................................................. huuuurrr");
                        setStatus("success");
                    }else {
                        setStatus("failed");
                    }
                }else {
                    setStatus("failed");
                }
            }

            @Override
            public void onFailure(Call<CreateOrderResponse> call, Throwable throwable) {
                setStatus("failed");
                Log.e(TAG, "onFailure: .................................  "+throwable.toString() );
            }
        });
    }

    public void setStatus(String createOrderStatus){
        mapView.createOrderStatus(createOrderStatus);
    }
}
