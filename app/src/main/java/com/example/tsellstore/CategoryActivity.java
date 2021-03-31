package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tsellstore.NavigationComponent.Dashbord.CatagoryAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.CatagoryModel;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView category_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);

        category_recyclerview = (RecyclerView) findViewById(R.id.category_recyclerview);
        List<SliderModel> sliderModelList = new ArrayList<>();

        //last dui ta banner
        sliderModelList.add(new SliderModel(R.drawable.ic_facebook, "#ff22")); //index 0
        sliderModelList.add(new SliderModel(R.drawable.ic_gift, "#ff22"));//index 1
        sliderModelList.add(new SliderModel(R.drawable.ic_add_to_basket, "#ff22"));//index 2

        sliderModelList.add(new SliderModel(R.drawable.ic_account, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_ribbon_banner_silhouette, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_round_account_button_with_user_inside, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_contact, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_facebook, "#ff22"));


        //nicher ta
        sliderModelList.add(new SliderModel(R.drawable.ic_gift, "#ff22"));
        //first dui ta banner
        sliderModelList.add(new SliderModel(R.drawable.ic_add_to_basket, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_account, "#ff22"));

        //////////////////////////--------Horizontal product Layout---------->>>>>>>>>>>>>>>>>>>>>
        List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));

        ////////////////////////////--------Main Recycler View ------------------>>>>>>>>>>>>>>>>>>>
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        category_recyclerview.setLayoutManager(testingLayoutManager);

        List<DashbordModel> dashbordModelList = new ArrayList<>();

        dashbordModelList.add(new DashbordModel(0, sliderModelList));
        dashbordModelList.add(new DashbordModel(1, R.drawable.ic_account, "#ff2288"));
        dashbordModelList.add(new DashbordModel(2, "Deals of the Day", horizontalScrollProductModelList));
        dashbordModelList.add(new DashbordModel(3, "Title", horizontalScrollProductModelList));
        dashbordModelList.add(new DashbordModel(0, sliderModelList));
        dashbordModelList.add(new DashbordModel(1, R.drawable.ic_account, "#ff2288"));
        dashbordModelList.add(new DashbordModel(0, sliderModelList));
        dashbordModelList.add(new DashbordModel(1, R.drawable.ic_account, "#ff2288"));

        DashbordAdapter dashbordAdapter = new DashbordAdapter(dashbordModelList);
        category_recyclerview.setAdapter(dashbordAdapter);
        dashbordAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_item, menu);
        return true;
    }

    //autogenerated when we create a NavigationDrawer Activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_search:
                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}