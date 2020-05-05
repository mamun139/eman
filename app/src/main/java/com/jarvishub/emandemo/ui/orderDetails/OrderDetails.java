package com.jarvishub.emandemo.ui.orderDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.order_details.OrderDetailsData;
import com.jarvishub.emandemo.model.order_details.OrderDetailsResponse;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.orderHistory.OrderHistory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 11/9/2017.
 */

public class OrderDetails extends AppCompatActivity {

    //Order Track=Order Ref

    @BindView(R.id.tvOrderTrack)
    TextView tvOrderTrack;
    @BindView(R.id.tvOrderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tvOrderPrice)
    TextView tvOrderPrice;

    @BindView(R.id.tvCollectionName)
    TextView tvCollectionName;
    @BindView(R.id.tvCollectionAddress)
    TextView tvCollectionAddress;
    @BindView(R.id.tvCollectionContact)
    TextView tvCollectionContact;
    @BindView(R.id.tvCollectionDate)
    TextView tvCollectionDate;
    @BindView(R.id.tvDeliveryName)
    TextView tvDeliveryName;
    @BindView(R.id.tvDeliverAddress)
    TextView tvDeliverAddress;
    @BindView(R.id.tvDeliveryContact)
    TextView tvDeliveryContact;
    @BindView(R.id.tvDeliveryDate)
    TextView tvDeliveryDate;

    @BindView(R.id.tvDiscount)
    TextView tvDiscount;
    @BindView(R.id.tvPaymentAmount)
    TextView tvPaymentAmount;
    @BindView(R.id.tvPaymentMethod)
    TextView tvPaymentMethod;
    String orderRef;
    String TAG="OrderDetails";
    @Inject
    DataManager mDataManager;
    private APIService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        orderRef= intent.getStringExtra(OrderHistory.ORDER_REF);
        Log.d(TAG, "onCreate: ................................................"+orderRef);
        getOrderDetails();
    }

    private void getOrderDetails() {
        apiInterface = new ApiClient().getClient(mDataManager.getAuthToken()).create(APIService.class);
        Call<OrderDetailsResponse> call=apiInterface.getOrderDetails(orderRef);
        call.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                if (response.isSuccessful()){
                    List<OrderDetailsData> orderDetailsData = response.body().getData();
                    String msg=orderDetailsData.get(0).getOrderRef();
                    tvOrderTrack.setText(orderDetailsData.get(0).getOrderRef());
                    tvOrderStatus.setText(orderDetailsData.get(0).getParcelStatus());
                    tvOrderPrice.setText(orderDetailsData.get(0).getPrice());
                    tvDiscount.setText(orderDetailsData.get(0).getDiscountValue());
                    tvPaymentAmount.setText(orderDetailsData.get(0).getPaymentAmount());
                    tvPaymentMethod.setText(orderDetailsData.get(0).getPaymentMethod());

                    tvCollectionName.setText(orderDetailsData.get(0).getCollectionContactName());
                    tvCollectionAddress.setText(orderDetailsData.get(0).getCollectionAddress());
                    tvCollectionContact.setText(orderDetailsData.get(0).getCollectionContactNumber());
                    tvCollectionDate.setText(orderDetailsData.get(0).getCollectionDatetime());

                    tvDeliveryName.setText(orderDetailsData.get(0).getDeliveryContactName());
                    tvDeliverAddress.setText(orderDetailsData.get(0).getDeliveryAddress());
                    tvDeliveryContact.setText(orderDetailsData.get(0).getDeliveryContactNumber());
                    tvDeliveryDate.setText(orderDetailsData.get(0).getDeliveryDatetime());

                    Log.d("OrderDetails", "onResponse: ..................."+msg);
                }
                else {
                    Toast.makeText(OrderDetails.this, "Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure:............................................" + t);
                Toast.makeText(OrderDetails.this, "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
