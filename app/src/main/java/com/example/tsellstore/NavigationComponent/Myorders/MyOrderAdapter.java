package com.example.tsellstore.NavigationComponent.Myorders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.NavigationComponent.OrderDetailsActivity;
import com.example.tsellstore.R;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = myOrderItemModelList.get(position).getProductImage();
        int rating = myOrderItemModelList.get(position).getRating();
        String title= myOrderItemModelList.get(position).getProductTitle();
        String deliveryDate = myOrderItemModelList.get(position).getDeliveryStatus();

        holder.setData(resource,title,deliveryDate,rating);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView deliveryIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;
        private LinearLayout ratingContainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.myOrder_product_image);
            deliveryIndicator = (ImageView) itemView.findViewById(R.id.myOrder_indicator) ;
            productTitle = (TextView) itemView.findViewById(R.id.myOrder_product_title);
            deliveryStatus = (TextView) itemView.findViewById(R.id.tv_myorder_delivered_date);
            ratingContainer = (LinearLayout) itemView.findViewById(R.id.rate_now_container) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent orderDetailsntent = new Intent(itemView.getContext(), OrderDetailsActivity.class);
                    itemView.getContext().startActivity(orderDetailsntent);
                }
            });
        }

        private void setData(int resource,String title,String deliveryDate,int rating){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(deliveryDate.equals("Canceled")){
                deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.tStore_red)));
            }else {
                deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.tStore_green)));
            }
            deliveryStatus.setText(deliveryDate);

            ////////////////------------>>>>>>>>Personal Rating Layout
            startRating(rating);
            for(int x = 0; x<ratingContainer.getChildCount(); x++){
                final int starPosition = x;
                ratingContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startRating(starPosition);
                    }
                });
            }

        }

        ////////////////------------>>>>>>>>Personal Rating Layout
        private void startRating(int starPosition) {
            //at first for loop sob star r color grey kore dibe
            //then if statement satify hole j koita star e press kora hbe segula r clr cng hbe
            for(int x = 0; x<ratingContainer.getChildCount(); x++){
                ImageView starBtn = (ImageView) ratingContainer.getChildAt(x); //joi starPosition eta dei tahole ekta imageview show korbe;sob koita star tw cole asche x r moddhe oi jnne x pass korbo
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

                if(x <= starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#28E4F4")));
                }
            }
        }
    }
}
