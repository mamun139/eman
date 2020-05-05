package com.jarvishub.emandemo.ui.termsConditions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.terms_conditions.TermsConditionsResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import javax.inject.Inject;

/**
 * Created by Lenovo on 11/15/2017.
 */

public class TermsConditions extends AppCompatActivity {
    @BindView(R.id.tvPopUpTermsCondition)
    TextView tvTermsCondition;
   @Inject
    DataManager mDataManager;
    private APIService apiInterface;
    String TAG="TermsConditions";
    public static String TERMS="TermsCondition";
   String termsCondition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        ButterKnife.bind(this);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        getTermsConditions();
    }

    public void getTermsConditions() {
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
        Call<TermsConditionsResponse> call=apiInterface.getTermsCondition();
        call.enqueue(new Callback<TermsConditionsResponse>() {
            @Override
            public void onResponse(Call<TermsConditionsResponse> call, Response<TermsConditionsResponse> response) {
                if(response.isSuccessful()){
                    termsCondition=response.body().getTcData().getTcContents();
                    Log.d(TAG, "onResponse: ..................."+termsCondition);
                   tvTermsCondition.setText(termsCondition);
                   tvTermsCondition.setText(Html.fromHtml(termsCondition));
                }
                else {
                    Toast.makeText(TermsConditions.this, "Try Again Please!", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<TermsConditionsResponse> call, Throwable t) {
                Toast.makeText(TermsConditions.this, "Try Again Please!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
