package com.jarvishub.emandemo.ui.signUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.SignUpData;
import com.jarvishub.emandemo.network.SignUpResponse;
import com.jarvishub.emandemo.utils.APIUrl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jarvishub.emandemo.utils.SHA1Hex.makeSHA1Hash;

/**
 * Created by mrrobot on 8/23/17.
 */

public class SignUpPresenter implements ISignUpPresenter {

    private String TAG = "SignUpPresenter";
    private ISignUpView signUpView;


    @Inject
    DataManager mDataManager;
    Context context;

    public SignUpPresenter(Context context) {
        this.context = context;
        ((EmanApp) context).getApplicationComponent().inject(this);
    }

    @Override
    public void setView(ISignUpView view) {
        this.signUpView = view;
    }

    @Override
    public void attemptSignUp(User user) {

        String generatedDeviceId = null;
        mDataManager.setMobileNumber(user.getMobile());

        //generate 40 bit hash device id
        try {
            generatedDeviceId = makeSHA1Hash(user.getMobile());
            mDataManager.setDeviceId(generatedDeviceId);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //defining retrofit api apiService
        APIService apiService = retrofit.create(APIService.class);

        //define call
        Call<SignUpResponse> call = apiService.createUser(
                generatedDeviceId,
                user.getFirstName(),
                user.getSurName(),
                user.getMobile(),
                user.getEmail(),
                user.getPassword(),
                user.getDob(),
                user.getGender(),
                user.getType()
        );

        //calling the api
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                //String authToken = response.body().getSignUpData().;
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: .................... success.  client_id: " + response.body().getSignUpData() + " auth_token: " + response.body().getAuthToken() + "device id :" + response.body().getDeviceId());
                    onSuccess();
                }else {
                    /*SignUpData data;
                    data = response.body().getSignUpData();
                    String tempRes = String.valueOf(data.getFailedMobile()+" "+data.getFailedEmail());
                    Toast.makeText(context,tempRes,Toast.LENGTH_LONG).show();*/
                    onError();
                    Toast.makeText(context,"Phone Or Email Already Used!!",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: ................................ sign up api not responding");
                }


            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: .................... failed " + throwable.getMessage());


            }
        });

    }


    public void onError() {
        signUpView.signUpFailed();
    }

    public void onSuccess() {
        signUpView.navigateToVerificationActivity();
    }

}
