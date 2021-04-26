package com.example.tsellstore.NavigationComponent.MyCart;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMMOUNT;
            default:
                return -1;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new cartItemViewHolder(view);
            case CartItemModel.TOTAL_AMMOUNT:
                View totalAmountView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new cartTotalAmountViewHolder(totalAmountView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupons = cartItemModelList.get(position).getFreeCoupons();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPrice();
                int offersApplied = cartItemModelList.get(position).getOfferApplied();

                ((cartItemViewHolder)holder).setItemDetails(resource,title,freeCoupons,productPrice,cuttedPrice,offersApplied);

                break;
            case CartItemModel.TOTAL_AMMOUNT:
                String totalItems = cartItemModelList.get(position).getTotalItems();
                String totalItemPrice = cartItemModelList.get(position).getTotalItemAmount();
                String deliveryCharge = cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount = cartItemModelList.get(position).getTotalAmount();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();

                ((cartTotalAmountViewHolder)holder).saveTotalAmount(totalItems,totalItemPrice,deliveryCharge,totalAmount,totalAmount);
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }


    class cartItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private ImageView freeCouponsIcon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView couponsApplied;
        private TextView productQuantity;

        public cartItemViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.product_image_cart);
            freeCouponsIcon = (ImageView) itemView.findViewById(R.id.free_cupon_icon);
            productTitle = (TextView) itemView.findViewById(R.id.product_title_cart);
            freeCoupons = (TextView) itemView.findViewById(R.id.tv_free_cupon);
            productPrice = (TextView) itemView.findViewById(R.id.product_price_cart);
            cuttedPrice = (TextView) itemView.findViewById(R.id.cutted_price_cart);
            offersApplied = (TextView) itemView.findViewById(R.id.offers_apply);
            couponsApplied = (TextView) itemView.findViewById(R.id.cupons_apply);
            productQuantity = (TextView) itemView.findViewById(R.id.product_quentity);
        }

        private void setItemDetails(int resource, String title, int freeCouponNo,
                                    String productPriceText, String cttedPriceText, int offersAppliedNo) {

            productImage.setImageResource(resource);
            productTitle.setText(title);

            if (freeCouponNo > 0) {
                freeCouponsIcon.setVisibility(View.VISIBLE);
                freeCoupons.setVisibility(View.VISIBLE);
                if (freeCouponNo == 1) {
                    freeCoupons.setText("free " + freeCouponNo + " coupon");
                } else {
                    freeCoupons.setText("free " + freeCouponNo + " coupons");
                }
            } else {
                freeCouponsIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cttedPriceText);
            if (offersAppliedNo > 0) {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo + " Offers Applied");
            } else {
                offersApplied.setVisibility(View.INVISIBLE);
            }
            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog quentity_dialog = new Dialog(itemView.getContext());
                    quentity_dialog.setContentView(R.layout.quantity_dialog);
                    quentity_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    quentity_dialog.setCancelable(false);

                    EditText quantity_edittext = quentity_dialog.findViewById(R.id.quantity_edtxt);
                    Button cancel_Btn =  quentity_dialog.findViewById(R.id.cancel_btn_quantitydialog);
                    Button ok_Btn = quentity_dialog.findViewById(R.id.ok_btn_quantitydialog);

                    cancel_Btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quentity_dialog.dismiss();
                        }
                    });

                    ok_Btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            productQuantity.setText("Qty: " + quantity_edittext.getText().toString());
                            quentity_dialog.dismiss();
                        }
                    });
                    quentity_dialog.show();
                }
            });

        }
    }

    class cartTotalAmountViewHolder extends RecyclerView.ViewHolder {

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public cartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);

            totalItems = (TextView) itemView.findViewById(R.id.total_items);
            totalItemPrice = (TextView) itemView.findViewById(R.id.total_items_price);
            deliveryPrice = (TextView) itemView.findViewById(R.id.delivary_charge);
            totalAmount = (TextView) itemView.findViewById(R.id.totalPriceTkTvId);
            savedAmount = (TextView) itemView.findViewById(R.id.saved_price);
        }

        private void saveTotalAmount(String totalItemText, String totalItemPriceText, String deliveryPriceText,
                                     String totalAmountText, String savedAmountText) {
            totalItems.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
