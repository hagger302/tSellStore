package com.example.tsellstore.NavigationComponent.MyCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.AddingAddressActivity;
import com.example.tsellstore.R;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private Button continue_btn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_mycart, container, false);

        cartRecyclerView = (RecyclerView) root.findViewById(R.id.cart_items_recyclerview);
        continue_btn = (Button) root.findViewById(R.id.cart_continue_btn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(linearLayoutManager);

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
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddingAddressActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return root;
    }


}