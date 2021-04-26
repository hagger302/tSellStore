package com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.AddressAdapter;
import com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.AddressModel;
import com.example.tsellstore.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.DeliveryActivity.DELIVERY_ADDRESS;

public class MyAddressActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView address_recyclerview;
    private Button deliverHereBtn;

    private static AddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        address_recyclerview = (RecyclerView) findViewById(R.id.address_recyclerview);
        deliverHereBtn = (Button) findViewById(R.id.deliver_here_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        address_recyclerview.setLayoutManager(layoutManager);

        int mode = getIntent().getIntExtra("MODE",-1);

        if(mode == DELIVERY_ADDRESS){
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else {
            deliverHereBtn.setVisibility(View.GONE);
        }
        List<AddressModel> addressModelList = new ArrayList<>();
        addressModelList.add(new AddressModel("Tabassum","Paradise of waterfalls","558",true));
        addressModelList.add(new AddressModel("Tabassum","Paradise of waterfalls","558",false));
         adapter = new AddressAdapter(addressModelList,mode);

        address_recyclerview.setAdapter(adapter);
        //foreground r moddhe ripple effect disi tai by default recyclerview te j animation ase seta tule dibo - fade animation
        ((SimpleItemAnimator)address_recyclerview.getItemAnimator()).setSupportsChangeAnimations(false);
        adapter.notifyDataSetChanged();

    }

    public static void refreshItemLayotData(int dSelect,int select){
        adapter.notifyItemChanged(dSelect); //ager theke j layout select ase tar jonno
        adapter.notifyItemChanged(select); //notun kore user j layout select korche seta //notifyItemChanged() ei method r maddhome layout k change korabo
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
             return true;
        }

        return super.onOptionsItemSelected(item);
    }
}