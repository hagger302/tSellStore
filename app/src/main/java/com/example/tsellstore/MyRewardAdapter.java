package com.example.tsellstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRewardAdapter extends RecyclerView.Adapter<MyRewardAdapter.ViewHolder> {

    private List<MyRewardModel> myRewardModelList;

    public MyRewardAdapter(List<MyRewardModel> myRewardModelList) {
        this.myRewardModelList = myRewardModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = myRewardModelList.get(position).getCoupon_title_reward();
        String validitydate = myRewardModelList.get(position).getCoupon_validitydate_reward();
        String body = myRewardModelList.get(position).getCoupon_body_reward();

        holder.setData(title,validitydate,body);
    }

    @Override
    public int getItemCount() {
        return myRewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView coupontitle;
        private TextView couponvalidityDate;
        private TextView couponbody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            coupontitle = (TextView) itemView.findViewById(R.id.couponTitle_myreward);
            couponvalidityDate = (TextView) itemView.findViewById(R.id.coupon_validity_myreward);
            couponbody = (TextView) itemView.findViewById(R.id.coupon_body_myreward);
        }

        private void setData(String title,String validitydate,String body){
            coupontitle.setText(title);
            couponvalidityDate.setText(validitydate);
            couponbody.setText(body);
        }
    }
}
