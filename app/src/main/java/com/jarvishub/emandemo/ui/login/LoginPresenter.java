package com.jarvishub.emandemo.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.network.login.LoginResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Lenovo on 8/22/2017.
 */

public class LoginPresenter implements ILoginPresenter {
    private final String TAG = "LoginPresenter";
    private ILoginView loginView;

    @Inject
    DataManager mDataManager;
    APIService apiInterface;
    Context context;


    public LoginPresenter(Context context) {
        ((EmanApp) context).getApplicationComponent().inject(this);
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
    }

    @Override
    public void setView(ILoginView view) {
        this.loginView = view;
    }

    @Override
    public void attemptLogin(final User user) {
        Call<LoginResponse> call = apiInterface.createUser(
                user);

        //calling the api
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        String authToken = response.body().getData().getToken();
                        String userMobile = response.body().getData().getUserInfo().getUserMobile();
                        // String userName = response.body().getSignUpData().getUserInfo().getUserName();
                        String fullName = response.body().getData().getUserInfo().getFullName();
                        Log.d(TAG, "Auth Token............................." + authToken);
                        mDataManager.setAuthToken(authToken);
                        mDataManager.setMobileNumber(userMobile);
                        mDataManager.setUserName(fullName);
                        mDataManager.setLoginStatus(true);

                        onSuccess();
                    } else {
                        onError();
                    }

                } else {
                    //loginActivity.loginFailed();
                    onError();
                    Log.e(TAG, "onResponse: ......................login data ..... server down");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: .................... failed " + throwable.getMessage());
            }
        });


    }

    public void onError() {
        loginView.loginFailed();
    }

    public void onSuccess() {
        loginView.navigateToHomeActivity();

    }

}
