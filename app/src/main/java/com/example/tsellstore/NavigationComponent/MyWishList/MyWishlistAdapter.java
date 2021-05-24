package com.example.tsellstore.NavigationComponent.MyWishList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tsellstore.ProductDetailsActivity;
import com.example.tsellstore.R;

import java.util.List;

public class MyWishlistAdapter extends RecyclerView.Adapter<MyWishlistAdapter.ViewHolder> {

    private List<MyWishListModel> myWishListModelList;
    private Boolean wishlist;

    public MyWishlistAdapter(List<MyWishListModel> myWishListModelList,Boolean wishlist) {
        this.myWishListModelList = myWishListModelList;
        this.wishlist= wishlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String resource = myWishListModelList.get(position).getProductImage();
        String title = myWishListModelList.get(position).getProductTitle();
        long freeCupons = myWishListModelList.get(position).getFreeCupon();
        String rating = myWishListModelList.get(position).getRating();
        long totalRating = myWishListModelList.get(position).getTotalRating();
        String productPrice = myWishListModelList.get(position).getProductPrice();
        String cuttedPrice = myWishListModelList.get(position).getCuttedtPrice();
        boolean paymentmethod = myWishListModelList.get(position).getCOD();

        holder.setData(resource,title,freeCupons,rating,totalRating,productPrice,cuttedPrice,paymentmethod);
    }

    @Override
    public int getItemCount() {
        return myWishListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private ImageView freeCuponIcon;
        private TextView freeCupons;
        private TextView rating;
        private TextView totalRatings;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.product_image_my_wisthlist);
            productTitle = (TextView) itemView.findViewById(R.id.product_title_my_wisthlist);
            freeCuponIcon = (ImageView) itemView.findViewById(R.id.cupon_icon);
            freeCupons = (TextView) itemView.findViewById(R.id.free_cupon_my_wisthlist);
            rating = (TextView) itemView.findViewById(R.id.tv_product_rating_miniview);
            totalRatings = (TextView) itemView.findViewById(R.id.total_ratings_my_wishlist);
            priceCut = (View) itemView.findViewById(R.id.price_cut_my_wishlist);
            productPrice = (TextView) itemView.findViewById(R.id.product_price_mywishlist);
            cuttedPrice = (TextView) itemView.findViewById(R.id.cutted_price_mywishlist);
            paymentMethod = (TextView) itemView.findViewById(R.id.payment_method_my_wishlist);
            delete = (ImageView) itemView.findViewById(R.id.delete_icon_my_wishlist);

        }

        private void setData(String image, String title, long freeCuponsNo, String averageRate, long totalRatingsNo, String price, String cuttedprice, boolean paymentmethod){
            Glide.with(itemView.getContext()).load(image).apply(new RequestOptions().placeholder(R.drawable.ic_placeholder)).into(productImage);
            productTitle.setText(title);

            if(freeCuponsNo !=0){
                freeCuponIcon.setVisibility(View.VISIBLE);
                if(freeCuponsNo == 1) {
                    freeCupons.setText("free " + freeCuponsNo + " coupon");
                }else {
                    freeCupons.setText("free " + freeCuponsNo + " coupons");
                }
            }else {
                freeCuponIcon.setVisibility(View.INVISIBLE);
                freeCupons.setVisibility(View.INVISIBLE);
            }

            rating.setText(averageRate);
            totalRatings.setText("("+totalRatingsNo+")ratings");
            productPrice.setText("Bdt. "+price+" /-");
            cuttedPrice.setText("Bdt. "+cuttedprice+" /-");
            if(paymentmethod){
                paymentMethod.setVisibility(View.VISIBLE);
            }else {
                paymentMethod.setVisibility(View.INVISIBLE);
            }


            if(wishlist){
                delete.setVisibility(View.VISIBLE);
            }else {
                delete.setVisibility(View.GONE);
            }
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Valobasi tw Sweetie", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
