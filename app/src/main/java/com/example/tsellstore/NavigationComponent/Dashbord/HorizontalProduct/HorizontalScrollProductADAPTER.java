package com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.ProductDetailsActivity;
import com.example.tsellstore.R;

import java.util.List;

public class HorizontalScrollProductADAPTER extends RecyclerView.Adapter<HorizontalScrollProductADAPTER.ViewHolder> {

    private List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public HorizontalScrollProductADAPTER(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horontal_scroll_recyclerview_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = horizontalScrollProductModelList.get(position).getProductImage();
        String title = horizontalScrollProductModelList.get(position).getProductTitle();
        String description = horizontalScrollProductModelList.get(position).getProductDescription();
        String price = horizontalScrollProductModelList.get(position).getProductPrice();

        //bind with the view holder
        holder.setProductImage(resource);
        holder.setProductTitle(title);
        holder.setProductDescription(description);
        holder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        if(horizontalScrollProductModelList.size() > 8){
            return 8;
        }else{
            return horizontalScrollProductModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.h_s_product_image);
            productTitle = (TextView) itemView.findViewById(R.id.h_s_product_title);
            productDescription = (TextView) itemView.findViewById(R.id.h_s_product_description);
            productPrice = (TextView) itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailIntent);
                }
            });
        }

        private void setProductImage(int resource) {
            productImage.setImageResource(resource);
        }

        private void setProductTitle(String title) {
            productTitle.setText(title);
        }

        private void setProductDescription(String description) {
            productDescription.setText(description);
        }

        private void setProductPrice(String price) {
            productPrice.setText(price);
        }

    }
}
