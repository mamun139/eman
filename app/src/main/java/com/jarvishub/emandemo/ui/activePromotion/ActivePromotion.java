package com.jarvishub.emandemo.ui.activePromotion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.active_promotion.PromotionData;
import com.jarvishub.emandemo.model.active_promotion.PromotionResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.applyPromotion.ApplyPromotion;

import java.util.List;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 11/17/2017.
 */

public class ActivePromotion extends AppCompatActivity{
    RecyclerView recyclerView;
    List<PromotionData> listing;
    String TAG="ActivePromotion";
    @Inject
    DataManager mDataManager;
    private APIService apiInterface ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_promotion_recyleview);
        recyclerView= (RecyclerView) findViewById(R.id.recyleActivePromotion);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        getActivePromotion();
        Button applyPromo= (Button) findViewById(R.id.btnApplyPromoCode);
        applyPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivePromotion.this, ApplyPromotion.class);
                startActivity(i);

            }
        });

    }

    private void getActivePromotion() {
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
        Call<PromotionResponse> call=apiInterface.getActivePromotion();
        call.enqueue(new Callback<PromotionResponse>() {
            @Override
            public void onResponse(Call<PromotionResponse> call, Response<PromotionResponse> response) {
                if (response.isSuccessful()){
                    listing=response.body().getData();
                    ActivePromotionRecyleview activePromotionRecyleview=new ActivePromotionRecyleview(getApplicationContext(),listing);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ActivePromotion.this);
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(activePromotionRecyleview);
                    activePromotionRecyleview.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "You Do not have any Active Promotion.", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<PromotionResponse> call, Throwable t) {
                Log.d(TAG, "onFailure:.........................." + t);
            }
        });

    }
}
