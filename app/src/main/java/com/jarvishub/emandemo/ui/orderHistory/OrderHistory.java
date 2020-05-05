package com.jarvishub.emandemo.ui.orderHistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.OrderData;
import com.jarvishub.emandemo.model.OrderInfo;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.orderDetails.OrderDetails;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistory extends AppCompatActivity  {
    public static String ORDER_ID = "OrderId";
    public static String ORDER_REF = "OrderRef";
    private String TAG = "OrderHistory";
    RecyclerView recyclerView;
    List<OrderInfo> listing;
    @Inject
    DataManager mDataManager;
    private APIService apiInterface;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_recyleview);
        recyclerView = (RecyclerView) findViewById(R.id.orderhistoryrecycleview);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        getOrderHistory();
    }

    public void getOrderHistory() {
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
        Call<OrderData> call = apiInterface.getOrderHistory();
        call.enqueue(new Callback<OrderData>() {
            @Override
            public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                if (response.isSuccessful()) {
                    OrderData orderData = response.body();
                    listing = response.body().getData();
                    Log.d("Response", orderData.getMessage().toString());
                    Log.d("onResponse: ", String.valueOf(listing));

                    recyclerView.setAdapter(new OrderHistoryRecyclerAdapter(listing, new OrderHistoryRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(OrderInfo order) {
                            Intent intent = new Intent(OrderHistory.this, OrderDetails.class);
                            intent.putExtra(ORDER_REF, order.getOrder_ref());
                            startActivity(intent);
                            Log.d(TAG, "onTaskItemClick: ....................................... " + order.getOrder_ref());
                        }
                    }));
                    LinearLayoutManager recyce = new LinearLayoutManager(OrderHistory.this);
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(recyce);
                } else {
                    Toast.makeText(getApplicationContext(), "You Do not have any Order History", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<OrderData> call, Throwable throwable) {
                Log.d("Times", "onFailure:" + throwable);
            }
        });
    }


}
