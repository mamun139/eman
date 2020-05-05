package com.jarvishub.emandemo.ui.activePromotion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.model.active_promotion.PromotionData;

import java.util.List;

/**
 * Created by Lenovo on 11/17/2017.
 */

public class ActivePromotionRecyleview extends RecyclerView.Adapter<ActivePromotionRecyleview.MyHolder> {
    private static final String TAG = "ActivePromotionRecyle";
    List<PromotionData> list;
    public ActivePromotionRecyleview(Context context, List<PromotionData> list) {
        this.list = list;
    }

    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_active_promotion, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PromotionData promotionData=list.get(position);
        holder.promoDiscount.setText(promotionData.getDiscount()+" % OFF");
        holder.promoExpireDate.setText(promotionData.getExpiresAt());
        holder.promoDescription.setText(promotionData.getDescription());
        holder.promoLeft.setText(promotionData.getPromoLeft()+" Promo Left");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView promoDiscount,promoExpireDate,promoLeft,promoDescription;
        public MyHolder(View itemView) {
            super(itemView);
            promoDiscount=itemView.findViewById(R.id.tvPromoDiscount);
            promoExpireDate=itemView.findViewById(R.id.tvPromoExpireDate);
            promoLeft=itemView.findViewById(R.id.tvPromoLeft);
            promoDescription=itemView.findViewById(R.id.tvPromoDescription);
        }
    }
}
