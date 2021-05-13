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

    //Dinamically access korb -> DAtabase theke data retrive krbo -
    //ekhane list initialize krbo na -> initialize krbo tkn jkn user view all btn e press korbe -> goto Dashbord Adapter

    public static  List<HorizontalScrollProductModel> horizontalScrollProductModelList;
    public static  List<MyWishListModel> wishListModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar() .setTitle(getIntent().getStringExtra("title"));


        viewAllRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_viewall);
        viewAllGridView = (GridView) findViewById(R.id.grid_view_viewall);

         layout_code = getIntent().getIntExtra("layout_code", -1);

        if (layout_code == 0) {   //layout_code asche DashBord Adapter theke
            viewAllRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewAllRecyclerView.setLayoutManager(layoutManager);

            MyWishlistAdapter adapter = new MyWishlistAdapter(wishListModelList,false);
            viewAllRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else if (layout_code == 1) {
            viewAllGridView.setVisibility(View.VISIBLE);



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