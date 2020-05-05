package com.jarvishub.emandemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jarvishub.emandemo.model.verification.VerificationResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.ui.verification.VerificationActivity;
import com.jarvishub.emandemo.utils.APIUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruhul on 8/30/17.
 */

public class HttpService extends IntentService {

    private static String TAG = HttpService.class.getSimpleName();

    public HttpService() {
        super(HttpService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String otp = intent.getStringExtra("otp");
            verifyOtp(otp);
        }
    }

    private void verifyOtp(final String otp) {
        // SharedPreferences prefs = this.getSharedPreferences(PreferencesHelper.PREF_NAME, MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        Call<VerificationResponse> call = service.verifyClientMobile(otp);

        //calling the api
        call.enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                Log.d(TAG, "onResponse: .................... success.  Status: " + response.body().getStatus() + " message : " + response.body().getMessage());
                if (response.isSuccessful()) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body().getData().getStatus() == 1) {
                                Intent intent = new Intent(HttpService.this, VerificationActivity.class);
                                intent.putExtra("otp", otp);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        } else {
                            Log.e(TAG, "onResponse: ................ error");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: .................... failed " + throwable.getMessage());
                //listener.onError();
            }
        });

    }

}
