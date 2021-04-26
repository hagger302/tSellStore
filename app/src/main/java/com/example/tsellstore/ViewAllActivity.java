package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.tsellstore.NavigationComponent.Dashbord.GridProduct.GridProductAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.MyWishList.MyWishListModel;
import com.example.tsellstore.NavigationComponent.MyWishList.MyWishlistAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView viewAllRecyclerView;
    private GridView viewAllGridView;
    int layout_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar() .setTitle("Deals of the day");


        viewAllRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_viewall);
        viewAllGridView = (GridView) findViewById(R.id.grid_view_viewall);

         layout_code = getIntent().getIntExtra("layout_code", -1);

        if (layout_code == 0) {   //layout_code asche DashBord Adapter theke
            viewAllRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewAllRecyclerView.setLayoutManager(layoutManager);

            List<MyWishListModel> myWishListModelList = new ArrayList<>();
            myWishListModelList.add(new MyWishListModel(R.drawable.dress_one, "Sweetie Dress", 1, "4", 3, "Bdt. 1000/-", "Bdt. 1200/-", "cash on delivery available"));
            myWishListModelList.add(new MyWishListModel(R.drawable.dress_one, "Sweetie Dress", 2, "3", 2, "Bdt. 1000/-", "Bdt. 1200/-", "cash on delivery available"));
            myWishListModelList.add(new MyWishListModel(R.drawable.dress_one, "Sweetie Dress", 3, "2", 0, "Bdt. 1000/-", "Bdt. 1200/-", "cash on delivery available"));
            myWishListModelList.add(new MyWishListModel(R.drawable.dress_one, "Sweetie Dress", 2, "1", 1, "Bdt. 1000/-", "Bdt. 1200/-", "cash on delivery available"));
            myWishListModelList.add(new MyWishListModel(R.drawable.dress_one, "Sweetie Dress", 2, "1", 1, "Bdt. 1000/-", "Bdt. 1200/-", "cash on delivery available"));
            myWishListModelList.add(new MyWishListModel(R.drawable.dress_one, "Sweetie Dress", 2, "1", 1, "Bdt. 1000/-", "Bdt. 1200/-", "cash on delivery available"));

            MyWishlistAdapter adapter = new MyWishlistAdapter(myWishListModelList,false);
            viewAllRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else if (layout_code == 1) {
            viewAllGridView.setVisibility(View.VISIBLE);

            List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));
            horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one, "Gawn", "Girl's Dress", "bdt. 12000"));


            GridProductAdapter adapter1 = new GridProductAdapter(horizontalScrollProductModelList);
            viewAllGridView.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}