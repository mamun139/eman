package com.jarvishub.emandemo.ui.track;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.order_track.TrackData;
import com.jarvishub.emandemo.model.order_track.TrackInfo;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderTrack extends AppCompatActivity {


    String status;
    String TAG = "OrderTrack";
    RecyclerView mRecyclerView;
    List<TrackData> listing;
    @Inject
    DataManager mDataManager;
    private APIService apiInterface;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_recyleview);
        mRecyclerView = (RecyclerView) findViewById(R.id.orderTrcakRecycleview);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        getTrackOrderDetails();
    }

    public void getTrackOrderDetails() {
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);

        Call<TrackInfo> call = apiInterface.getTrackOrderDetails();

        call.enqueue(new Callback<TrackInfo>() {
            @Override
            public void onResponse(Call<TrackInfo> call, Response<TrackInfo> response) {
                if (response.isSuccessful()) {
                    TrackInfo orderData = response.body();
                    status = orderData.getStatus();
                    Log.d(TAG, "Status:......................................... " + status);
                    listing = response.body().getData();
                    TrackRecyclerAdapter trackRecyclerAdapter = new TrackRecyclerAdapter(getApplicationContext(), listing);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(OrderTrack.this);
                    mRecyclerView.setHasFixedSize(false);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(trackRecyclerAdapter);
                    trackRecyclerAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "You Do not have any Order in Progress", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TrackInfo> call, Throwable throwable) {
                Log.d("Times", "onFailure:" + throwable);

            }
        });
    }
}
