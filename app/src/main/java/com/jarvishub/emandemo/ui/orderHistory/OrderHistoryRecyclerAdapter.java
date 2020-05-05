package com.jarvishub.emandemo.ui.orderHistory;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.model.OrderInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 9/14/2017.
 */

public class OrderHistoryRecyclerAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerAdapter.MyHolder> {
    private final List<OrderInfo> list;
    private final OnItemClickListener listener;
    private CardView cardView;
    public interface OnItemClickListener {
        void onItemClick(OrderInfo order);
    }

    public OrderHistoryRecyclerAdapter(List<OrderInfo> list,OnItemClickListener listener ) {
        Log.e("Size", String.valueOf(list.size()));
        this.list = list;
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_history, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvOrderStatus, tvPrice, tvPickupAddress, tvDeliveredAddress;

        public MyHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardOrder);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvOrderStatus = (TextView) itemView.findViewById(R.id.tvOrderStatus);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvPickupAddress = (TextView) itemView.findViewById(R.id.tvPickupAddress);
            tvDeliveredAddress = (TextView) itemView.findViewById(R.id.tvDeliveredAddress);
        }
        public void bind(final OrderInfo order, final OnItemClickListener listener) {
            tvDate.setText(order.getOrder_date());
           tvOrderStatus.setText(order.getParcel_status());
           tvPrice.setText(order.getPrice()+"TK");
           tvPickupAddress.setText(order.getPickup_address());
           tvDeliveredAddress.setText(order.getDelivered_address());
           cardView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(order);
                    Log.d("OrderHistoryAdapter", "onClick:............................................. "+order.getOrder_ref());
                }
            });
        }
    }
}
