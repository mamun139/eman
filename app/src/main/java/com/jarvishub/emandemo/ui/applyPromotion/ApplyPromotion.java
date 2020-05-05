package com.jarvishub.emandemo.ui.applyPromotion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.apply_promo.PromoCodeResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.activePromotion.ActivePromotion;
import com.jarvishub.emandemo.ui.servicePackages.IServicePackagesPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 11/17/2017.
 */

public class ApplyPromotion extends AppCompatActivity {
    private String TAG = "ApplyPromotion";
    APIService apiInterface;
    @Inject
    DataManager mDataManager;
    @Inject
    IServicePackagesPresenter servicePackagesPresenter;
    String promo;
    int p;
    @BindView(R.id.etPromo)
    EditText etPromo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_promo);
        ButterKnife.bind(this);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);

        Button promoapply = (Button) findViewById(R.id.btnApplyPromotion);
        promoapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyPromoCode(etPromo.getText().toString());
            }
        });

    }

    public void applyPromoCode(String promoCode) {
        String promocode = promoCode;
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
        Call<PromoCodeResponse> call = apiInterface.applyPromoCode(promocode);
        Log.e(TAG, "onCreate: ............................................................" + promocode);
        call.enqueue(new Callback<PromoCodeResponse>() {
            @Override
            public void onResponse(Call<PromoCodeResponse> call, Response<PromoCodeResponse> response) {
                Log.d(TAG, "onResponse: ....................................... " + promo);
                if (response.isSuccessful()) {
                    Intent i = new Intent(ApplyPromotion.this, ActivePromotion.class);
                    startActivity(i);
                } else {
                    Toast.makeText(ApplyPromotion.this, "Invalid Promo Code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PromoCodeResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ...................................... " + t);
            }
        });


    }
}
