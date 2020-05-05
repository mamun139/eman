package com.jarvishub.emandemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.model.TrackOrder;
import com.jarvishub.emandemo.ui.activePromotion.ActivePromotion;
import com.jarvishub.emandemo.ui.orderHistory.OrderHistory;
import com.jarvishub.emandemo.ui.termsConditions.TermsConditions;
import com.jarvishub.emandemo.ui.track.OrderTrack;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 11/30/2017.
 */

public class DashBoard extends AppCompatActivity {
    @BindView(R.id.cvPlaceOrder)
    CardView cvPlaceOrder;
    @BindView(R.id.cvOrderHistory)
    CardView cvOrderHistory;
    @BindView(R.id.cvTrackOrder)
    CardView cvTrackOrder;
    @BindView(R.id.cvSpecialOffer)
    CardView cvSpecialOffer;
    @BindView(R.id.cvPromotion)
    CardView cvPromotion;
    @BindView(R.id.cvEmanService)
    CardView cvEmanService;
    @BindView(R.id.cvJoinEman)
    CardView cvJoinEman;
    @BindView(R.id.cvProfile)
    CardView cvProfile;
    @BindView(R.id.cvLegal)
    CardView cvLegal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        cvOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this, OrderHistory.class);
                startActivity(intent);

            }
        });

        cvTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this, OrderTrack.class);
                startActivity(intent);
            }
        });

        cvPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this, ActivePromotion.class);
                startActivity(intent);
            }
        });

        cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this, Test.class);
                startActivity(intent);
            }
        });
    }
}
