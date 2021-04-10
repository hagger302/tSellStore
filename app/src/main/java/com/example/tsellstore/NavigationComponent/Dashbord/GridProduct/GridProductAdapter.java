package com.example.tsellstore.NavigationComponent.Dashbord.GridProduct;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.ProductDetailsActivity;
import com.example.tsellstore.R;

import java.util.List;


import static com.example.tsellstore.R.color.white;

public class GridProductAdapter  extends BaseAdapter {

    List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public GridProductAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
           view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.horontal_scroll_recyclerview_item_layout,null);
           //view.setBackground(Color.parseColor(String.valueOf(white)));
            //view.setElevation(0); //for shadow effect

            //////////////////------------>>>>>>>>>goto personal rating
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = (ImageView) view.findViewById(R.id.h_s_product_image);
            TextView productTitle = (TextView) view.findViewById(R.id.h_s_product_title);
            TextView productDescription = (TextView) view.findViewById(R.id.h_s_product_description);
            TextView  productPrice = (TextView) view.findViewById(R.id.h_s_product_price);

            /**
             * next -> simplely we set this (productImage,productTitle,productDescription,productPrice,) through the constructor
             */
            productImage.setImageResource(horizontalScrollProductModelList.get(position).getProductImage());
            productTitle.setText(horizontalScrollProductModelList.get(position).getProductTitle());
            productDescription.setText(horizontalScrollProductModelList.get(position).getProductDescription());
            productPrice.setText(horizontalScrollProductModelList.get(position).getProductPrice());
        }else{
            view = convertView;
        }
        return view;
    }
}
