package com.jarvishub.emandemo.ui.splash;

import android.content.Context;
import android.util.Log;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.network.login.LoginResponse;
import com.jarvishub.emandemo.utils.InternetChecker;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mrrobot on 8/21/17.
 */

public class SplashPresenter implements ISplashPresenter {
    private SplashView view;
    private Context context;
    private final String TAG = "Splash Presenter";
    private APIService apiInterface;
    @Inject
    DataManager mDataManager;

    public SplashPresenter(Context context) {
        ((EmanApp) context).getApplicationComponent().inject(this);
        apiInterface = new ApiClient().getClient("").create(APIService.class);
    }
    @Override
    public void setView(SplashView view,Context context) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void doBackGroundTasks() {
        if (InternetChecker.isInternetAvailable(context)) {
            Log.d(TAG, "onCreate: ....................... internet connected");
            if (mDataManager.isLoggedIn() && mDataManager.getMobileNumber() != null && mDataManager.getUserPassword() != null) {
                User user = new User(mDataManager.getMobileNumber(), mDataManager.getUserPassword());
                doLogin(user);
            }else {
                //view.discardSplash();
                view.gotoLoginActivity();
            }
        } else {
            showNoInternetDialogue();
            Log.d(TAG, "onCreate: ....................... No network connection");
        }
    }


    private void doLogin(User user) {
        Call<LoginResponse> call = apiInterface.createUser(
                user);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        String authToken = response.body().getData().getToken();
                        String userMobile = response.body().getData().getUserInfo().getUserMobile();// String userName = response.body().getSignUpData().getUserInfo().getUserName();
                        String fullName = response.body().getData().getUserInfo().getFullName();
                        mDataManager.setAuthToken(authToken);
                        mDataManager.setMobileNumber(userMobile);
                        mDataManager.setUserName(fullName);
                        mDataManager.setLoginStatus(true);
                        Log.d(TAG, "onResponse: ....................... login success");
                        onSuccess();
                    } else {
                        onError(response.body().getMessage());
                    }

                } else {
                    view.gotoLoginActivity();
                    onError("No Response From Remote Server");
                    Log.e(TAG, "onResponse: ......................login data ..... server down");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: .................... failed " + throwable.getMessage());
            }
        });

    }

    public void showNoInternetDialogue() {
        view.showAlert("No Internet Connection!!");
    }

    public void onError(String message) {
        view.showAlert(message);
    }

    public void onSuccess() {
        //view.discardSplash();
        view.gotoHomeActivity();
    }
}
