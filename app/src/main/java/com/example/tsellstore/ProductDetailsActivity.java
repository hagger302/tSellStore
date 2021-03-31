package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImageViewPager;
    private TabLayout ViewPagerIndicator;
    private FloatingActionButton addToWishListBtn;
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
        //add the tab layout with the viewPager
        ViewPagerIndicator.setupWithViewPager(productImageViewPager,true);

        addToWishListBtn = (FloatingActionButton) findViewById(R.id.add_to_wishlist);

        List<Integer> productImage = new ArrayList<>();
        productImage.add(R.drawable.dress_one);
        productImage.add(R.drawable.dress_one);
        productImage.add(R.drawable.dress_one);

        Product_Images_Adapter productImagesAdapter = new Product_Images_Adapter(productImage);
        productImageViewPager.setAdapter(productImagesAdapter);
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