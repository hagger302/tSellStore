package com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tsellstore.NavigationComponent.MyCart.CartAdapter;
import com.example.tsellstore.NavigationComponent.MyCart.CartItemModel;
import com.example.tsellstore.R;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView delivery_recyclerview;
    private Button change_or_add_btn;

    public static final int DELIVERY_ADDRESS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");

        delivery_recyclerview = (RecyclerView) findViewById(R.id.delivery_recyclerView);
        change_or_add_btn = (Button) findViewById(R.id.change_or_add_address_btn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        delivery_recyclerview.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        //int,string,int,string,string,int,int,int
        cartItemModelList.add(new CartItemModel(0,R.drawable.dress_one,"Sweetiee Dress",2,"5000","2000",2,2,2));
        cartItemModelList.add(new CartItemModel(0,R.drawable.dress_one,"Sweetiee Dress",2,"5000","2000",2,2,2));
        cartItemModelList.add(new CartItemModel(0,R.drawable.dress_one,"Sweetiee Dress",2,"5000","2000",2,2,2));
        cartItemModelList.add(new CartItemModel(0,R.drawable.dress_one,"Sweetiee Dress",2,"5000","2000",2,2,2));

        cartItemModelList.add(new CartItemModel(1,"total Item (3)","2000","free","300","100"));
        cartItemModelList.add(new CartItemModel(1,"total Item (3)","2000","free","300","100"));
        cartItemModelList.add(new CartItemModel(1,"total Item (3)","2000","free","300","100"));
        cartItemModelList.add(new CartItemModel(1,"total Item (3)","2000","free","300","100"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        delivery_recyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        change_or_add_btn.setVisibility(View.VISIBLE);
        change_or_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAddressActivity.class);
                intent.putExtra("MODE",DELIVERY_ADDRESS);
                startActivity(intent);
            }
        });
    }

    //autogenerated when we create a NavigationDrawer Activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

         int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}