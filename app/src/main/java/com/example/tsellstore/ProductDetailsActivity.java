package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImageViewPager;
    private TabLayout ViewPagerIndicator;
    private LinearLayout ratingContainer;

    private FloatingActionButton addToWishListBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST =false;
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
        List<Integer> productImage = new ArrayList<>();
        productImage.add(R.drawable.dress_one);
        productImage.add(R.drawable.dress_one);
        productImage.add(R.drawable.dress_one);

        Product_Images_Adapter productImagesAdapter = new Product_Images_Adapter(productImage);
        productImageViewPager.setAdapter(productImagesAdapter);
        //add the tab layout with the viewPager
        ViewPagerIndicator.setupWithViewPager(productImageViewPager,true);

        /////////////--------->>>>>>>>FLOATING ACTION BUTTON
        addToWishListBtn = (FloatingActionButton) findViewById(R.id.add_to_wishlist);
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ALREADY_ADDED_TO_WISHLIST){
                    ALREADY_ADDED_TO_WISHLIST =false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                }else {
                    ALREADY_ADDED_TO_WISHLIST =true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.tStore_red));
                }
            }
        });

        ////////////////------------>>>>>>>>Personal Rating Layout
        ratingContainer = (LinearLayout) findViewById(R.id.rate_now_container) ;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.main_search:
                break;

            case R.id.main_cart:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}