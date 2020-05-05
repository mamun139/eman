package com.jarvishub.emandemo.ui.track;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.model.order_track.TrackData;

import java.util.List;


/**
 * Created by Lenovo on 9/14/2017.
 */


public class TrackRecyclerAdapter extends RecyclerView.Adapter<TrackRecyclerAdapter.MyHolder> {
    private static final String TAG = "TrackRecyleAdapter";
    List<TrackData> list;
    String orderStatus;

    public TrackRecyclerAdapter(Context context, List<TrackData> list) {
        Log.d("Size", String.valueOf(list.size()));
        Log.d(TAG, "list: " + list.get(0).getCollectionTime());
        this.list = list;
        TrackData trackData = list.get(0);
        Log.d(TAG, "TrackRecyclerAdapter:...........................List:" + trackData.getDeliveryZone());
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_track, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TrackData trackData = list.get(position);
        orderStatus = trackData.getStatus();
        Log.d(TAG, "Status................................" + orderStatus);
        holder.collectionZone.setText(trackData.getCollectionZone());
        holder.deliveryZone.setText(trackData.getDeliveryZone());
        holder.collectionTime.setText(trackData.getCollectionTime());

        if(orderStatus.equals("Ready to collection")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);
        }
        if(orderStatus.equals("On the way to collection")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("Arrived at collection point")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("Parcel collected")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("Payment collected")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("On the way to deposit hub")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToDepositHub.setProgress(100);
            holder.btnOnTheWayToDepositHub.setVisibility(View.GONE);
            holder.btnOnTheWayToDepositHubConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("Parcel deposited")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToDepositHub.setProgress(100);
            holder.btnOnTheWayToDepositHub.setVisibility(View.GONE);
            holder.btnOnTheWayToDepositHubConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelDeposited.setProgress(100);
            holder.btnParcelDeposited.setVisibility(View.GONE);
            holder.btnParcelDepositedConfirm.setVisibility(View.VISIBLE);


        }
        if(orderStatus.equals("In transit")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToDepositHub.setProgress(100);
            holder.btnOnTheWayToDepositHub.setVisibility(View.GONE);
            holder.btnOnTheWayToDepositHubConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelDeposited.setProgress(100);
            holder.btnParcelDeposited.setVisibility(View.GONE);
            holder.btnParcelDepositedConfirm.setVisibility(View.VISIBLE);

            holder.pbInTransit.setProgress(100);
            holder.btnInTransit.setVisibility(View.GONE);
            holder.btnInTransitConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("Picked up for delivery")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToDepositHub.setProgress(100);
            holder.btnOnTheWayToDepositHub.setVisibility(View.GONE);
            holder.btnOnTheWayToDepositHubConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelDeposited.setProgress(100);
            holder.btnParcelDeposited.setVisibility(View.GONE);
            holder.btnParcelDepositedConfirm.setVisibility(View.VISIBLE);

            holder.pbInTransit.setProgress(100);
            holder.btnInTransit.setVisibility(View.GONE);
            holder.btnInTransitConfirm.setVisibility(View.VISIBLE);

            holder.pbPickedUpForDelivery.setProgress(100);
            holder.btnPickedUpForDelivery.setVisibility(View.GONE);
            holder.btnPickedUpForDeliveryConfirm.setVisibility(View.VISIBLE);


        }
        if(orderStatus.equals("Arrived at delivery")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToDepositHub.setProgress(100);
            holder.btnOnTheWayToDepositHub.setVisibility(View.GONE);
            holder.btnOnTheWayToDepositHubConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelDeposited.setProgress(100);
            holder.btnParcelDeposited.setVisibility(View.GONE);
            holder.btnParcelDepositedConfirm.setVisibility(View.VISIBLE);

            holder.pbInTransit.setProgress(100);
            holder.btnInTransit.setVisibility(View.GONE);
            holder.btnInTransitConfirm.setVisibility(View.VISIBLE);

            holder.pbPickedUpForDelivery.setProgress(100);
            holder.btnPickedUpForDelivery.setVisibility(View.GONE);
            holder.btnPickedUpForDeliveryConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtDelivery.setProgress(100);
            holder.btnArrivedAtDelivery.setVisibility(View.GONE);
            holder.btnArrivedAtDeliveryConfirm.setVisibility(View.VISIBLE);

        }
        if(orderStatus.equals("Delivered")){
            holder.btnReadyToCollection.setVisibility(View.GONE);
            holder.btnReadyToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToCollection.setProgress(100);
            holder.btnOnTheWayToCollection.setVisibility(View.GONE);
            holder.btnOnTheWayToCollectionConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtCollectionPoint.setProgress(100);
            holder.btnArrivedAtCollectionPoint.setVisibility(View.GONE);
            holder.btnArrivedAtCollectionPointConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelCollected.setProgress(100);
            holder.btnParcelCollected.setVisibility(View.GONE);
            holder.btnParcelCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbPaymentCollected.setProgress(100);
            holder.btnPaymentCollected.setVisibility(View.GONE);
            holder.btnPaymentCollectedConfirm.setVisibility(View.VISIBLE);

            holder.pbOnTheWayToDepositHub.setProgress(100);
            holder.btnOnTheWayToDepositHub.setVisibility(View.GONE);
            holder.btnOnTheWayToDepositHubConfirm.setVisibility(View.VISIBLE);

            holder.pbParcelDeposited.setProgress(100);
            holder.btnParcelDeposited.setVisibility(View.GONE);
            holder.btnParcelDepositedConfirm.setVisibility(View.VISIBLE);

            holder.pbInTransit.setProgress(100);
            holder.btnInTransit.setVisibility(View.GONE);
            holder.btnInTransitConfirm.setVisibility(View.VISIBLE);

            holder.pbPickedUpForDelivery.setProgress(100);
            holder.btnPickedUpForDelivery.setVisibility(View.GONE);
            holder.btnPickedUpForDeliveryConfirm.setVisibility(View.VISIBLE);

            holder.pbArrivedAtDelivery.setProgress(100);
            holder.btnArrivedAtDelivery.setVisibility(View.GONE);
            holder.btnArrivedAtDeliveryConfirm.setVisibility(View.VISIBLE);

            holder.pbDelivered.setProgress(100);
            holder.btnDelivered.setVisibility(View.GONE);
            holder.btnDeliveredConfirm.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView collectionZone, deliveryZone, collectionTime;
        Button btnReadyToCollection,btnOnTheWayToCollection,btnArrivedAtCollectionPoint,btnParcelCollected,btnPaymentCollected,btnOnTheWayToDepositHub,btnParcelDeposited, btnInTransit,btnPickedUpForDelivery,btnArrivedAtDelivery,btnDelivered;
        Button btnReadyToCollectionConfirm,btnOnTheWayToCollectionConfirm,btnArrivedAtCollectionPointConfirm,btnParcelCollectedConfirm,btnPaymentCollectedConfirm,btnOnTheWayToDepositHubConfirm,btnParcelDepositedConfirm, btnInTransitConfirm,btnPickedUpForDeliveryConfirm,btnArrivedAtDeliveryConfirm,btnDeliveredConfirm;
        ProgressBar pbOnTheWayToCollection,pbArrivedAtCollectionPoint,pbParcelCollected,pbPaymentCollected,pbOnTheWayToDepositHub,pbParcelDeposited,pbInTransit,pbPickedUpForDelivery,pbArrivedAtDelivery,pbPaymentReceived,pbDelivered;

        public MyHolder(View itemView) {

            super(itemView);
            collectionZone = itemView.findViewById(R.id.tvCollectionZone);
            deliveryZone = itemView.findViewById(R.id.tvDeliveryZone);
            collectionTime = itemView.findViewById(R.id.tvCollectionDate);

            btnReadyToCollection=itemView.findViewById(R.id.btnReadytoCollection1);
            btnOnTheWayToCollection=itemView.findViewById(R.id.btnOntheWaytoCollection1);
            btnArrivedAtCollectionPoint=itemView.findViewById(R.id.btnArrivedatCollectionPoint1);
            btnParcelCollected=itemView.findViewById(R.id.btnParcelCollected1);
            btnPaymentCollected=itemView.findViewById(R.id.btnPaymentCollected1);
            btnOnTheWayToDepositHub=itemView.findViewById(R.id.btnOnTheWaytoDepositHub1);
            btnParcelDeposited=itemView.findViewById(R.id.btnParcelDeposited1);
            btnInTransit=itemView.findViewById(R.id.btnInTransit1);
            btnPickedUpForDelivery=itemView.findViewById(R.id.btnPickedupForDelivery1);
            btnArrivedAtDelivery=itemView.findViewById(R.id.btnArrivedatdelivery1);
            btnDelivered=itemView.findViewById(R.id.btnDelivered1);

            btnReadyToCollectionConfirm=itemView.findViewById(R.id.btnReadytoCollection);
            btnOnTheWayToCollectionConfirm=itemView.findViewById(R.id.btnOntheWaytoCollection);
            btnArrivedAtCollectionPointConfirm=itemView.findViewById(R.id.btnArrivedatCollectionPoint);
            btnParcelCollectedConfirm=itemView.findViewById(R.id.btnParcelCollected);
            btnPaymentCollectedConfirm=itemView.findViewById(R.id.btnPaymentCollected);
            btnOnTheWayToDepositHubConfirm=itemView.findViewById(R.id.btnOnTheWaytoDepositHub);
            btnParcelDepositedConfirm=itemView.findViewById(R.id.btnParcelDeposited);
            btnInTransitConfirm=itemView.findViewById(R.id.btnInTransit);
            btnPickedUpForDeliveryConfirm=itemView.findViewById(R.id.btnPickedupForDelivery);
            btnArrivedAtDeliveryConfirm=itemView.findViewById(R.id.btnArrivedatdelivery);
            btnDeliveredConfirm=itemView.findViewById(R.id.btnDelivered);

            pbOnTheWayToCollection = itemView.findViewById(R.id.pbOntheWaytoCollection);
            pbArrivedAtCollectionPoint = itemView.findViewById(R.id.pbArrivedatCollectionPoint);
            pbParcelCollected = itemView.findViewById(R.id.pbParcelCollected);
            pbPaymentCollected = itemView.findViewById(R.id.pbPaymentCollected);
            pbOnTheWayToDepositHub = itemView.findViewById(R.id.pbOnTheWaytoDepositHub);
            pbParcelDeposited = itemView.findViewById(R.id.pbParcelDeposited);
            pbInTransit = itemView.findViewById(R.id.pbInTransit);
            pbPickedUpForDelivery = itemView.findViewById(R.id.pbPickedupForDelivery);
            pbArrivedAtDelivery = itemView.findViewById(R.id.pbArrivedatdelivery);
            pbDelivered = itemView.findViewById(R.id.pbDelivered);

        }
    }
}
