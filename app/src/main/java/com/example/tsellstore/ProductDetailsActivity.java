package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tsellstore.NavigationComponent.MainActivity;
import com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.DeliveryActivity;
import com.example.tsellstore.NavigationComponent.MyRewards.MyRewardAdapter;
import com.example.tsellstore.NavigationComponent.MyRewards.MyRewardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.tsellstore.NavigationComponent.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImageViewPager;
    private TabLayout ViewPagerIndicator;
    private LinearLayout ratingContainer;
    private Button quopon_redemption_btn;

    private Button buy_now_btn;

    private FloatingActionButton addToWishListBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;

    /////--------->>>coupon Dialog
    public static TextView couponTitle;
    public static TextView couponExperityDate;
    public static TextView couponBody;
    private static RecyclerView couponRecyclerview;
    private static LinearLayout selectedCoupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        productImageViewPager = (ViewPager) findViewById(R.id.product_images_viewPager);
        ViewPagerIndicator = (TabLayout) findViewById(R.id.view_pagger_indicator);
        buy_now_btn = (Button) findViewById(R.id.buy_now_btn);
        quopon_redemption_btn = (Button) findViewById(R.id.cuppon_redemption_btn);

        ////////----------->>>>>>>>>coupons Dialog
        Dialog couponDialog = new Dialog(ProductDetailsActivity.this);
        couponDialog.setContentView(R.layout.copun_redem_dialog);
        couponDialog.setCancelable(true);
        couponDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerview = couponDialog.findViewById(R.id.toggle_recycle);
        couponRecyclerview = couponDialog.findViewById(R.id.coupons_recyclerview);
        selectedCoupons = couponDialog.findViewById(R.id.selected_coupon);
        couponTitle = couponDialog.findViewById(R.id.couponTitle_myreward);
        couponExperityDate = couponDialog.findViewById(R.id.coupon_validity_myreward);
        couponBody = couponDialog.findViewById(R.id.coupon_body_myreward);

        TextView originalPrice = couponDialog.findViewById(R.id.original_price);
        TextView discountedPrice = couponDialog.findViewById(R.id.discounted_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        couponRecyclerview.setLayoutManager(layoutManager);

        List<MyRewardModel> myRewardModelList = new ArrayList<>();
        myRewardModelList.add(new MyRewardModel("Discount for Sweetie", "20th April,2021", "Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));
        myRewardModelList.add(new MyRewardModel("Cash back", "21th April,2021", "Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));
        myRewardModelList.add(new MyRewardModel("Buy 2 Get 1 Free", "22th April,2021", "Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));

        MyRewardAdapter adapter = new MyRewardAdapter(myRewardModelList, true); //false coming from MyRewardFragment theke
        couponRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        toggleRecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogRecyclerView();
            }
        });
        ////////////////////--->Coupons Dialog
        quopon_redemption_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                couponDialog.show();
            }
        });

        buy_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
                startActivity(intent);
            }
        });
        List<Integer> productImage = new ArrayList<>();
        productImage.add(R.drawable.dress_one);
        productImage.add(R.drawable.dress_one);
        productImage.add(R.drawable.dress_one);

        Product_Images_Adapter productImagesAdapter = new Product_Images_Adapter(productImage);
        productImageViewPager.setAdapter(productImagesAdapter);
        //add the tab layout with the viewPager
        ViewPagerIndicator.setupWithViewPager(productImageViewPager, true);

        /////////////--------->>>>>>>>FLOATING ACTION BUTTON
        addToWishListBtn = (FloatingActionButton) findViewById(R.id.add_to_wishlist);
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.tStore_red));
                }
            }
        });

        ////////////////------------>>>>>>>>Personal Rating Layout
        ratingContainer = (LinearLayout) findViewById(R.id.rate_now_container);

        for (int x = 0; x < ratingContainer.getChildCount(); x++) {
            final int starPosition = x;
            ratingContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startRating(starPosition);
                }
            });
        }
    }

    public static void showDialogRecyclerView() {
        if (couponRecyclerview.getVisibility() == View.GONE) {
            couponRecyclerview.setVisibility(View.VISIBLE);
            selectedCoupons.setVisibility(View.GONE); //at a time ekta layout niye kj krbo - tai ei plan korchi
        } else {
            couponRecyclerview.setVisibility(View.GONE);
            selectedCoupons.setVisibility(View.VISIBLE);
        }
    }

    ////////////////------------>>>>>>>>Personal Rating Layout
    private void startRating(int starPosition) {
        //at first for loop sob star r color grey kore dibe
        //then if statement satify hole j koita star e press kora hbe segula r clr cng hbe
        for (int x = 0; x < ratingContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) ratingContainer.getChildAt(x); //joi starPosition eta dei tahole ekta imageview show korbe;sob koita star tw cole asche x r moddhe oi jnne x pass korbo
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

            if (x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#28E4F4")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.main_search:
                break;

            case R.id.main_cart:
                Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
                showCart = true;
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}