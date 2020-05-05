package com.jarvishub.emandemo.ui.verification;

import android.content.Context;
import android.util.Log;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.model.verification.VerificationResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created with love by mrrobot on 11/29/17.
 */

public class VerificationPresenter implements IVerificationPresenter {
    private String TAG = "VerificationPresenter";
    private APIService apiInterface;
    private IVerificationView verificationView;

    public VerificationPresenter(Context context) {
        ((EmanApp) context).getApplicationComponent().inject(this);
        apiInterface = new ApiClient().getClient("").create(APIService.class);
    }

    @Override
    public void setView(IVerificationView view) {
        this.verificationView = view;
    }

    @Override
    public void authenticateVerificationCode(String verificationCode) {

        Call<VerificationResponse> call = apiInterface.verifyClientMobile(verificationCode);

        //calling the api
        call.enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                Log.d(TAG, "onResponse: .................... success.  Status: " + response.body().getStatus() + " message : " + response.body().getMessage());
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getStatus()) {
                            if (response.body().getData().getStatus() == 1) {
                                Log.d(TAG, "onResponse: ......................... MOBILE NO VERIFIED!");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.e(TAG, "onResponse: .................. response error" );
                }
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: .................... failed " + throwable.getMessage());
            }
        });
        Log.d(TAG, "authenticateVerificationCode: ............................ " + verificationCode);
    }
}
