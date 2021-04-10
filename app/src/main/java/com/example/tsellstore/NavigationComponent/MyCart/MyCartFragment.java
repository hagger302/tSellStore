package com.example.tsellstore.NavigationComponent.MyCart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.R;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_mycart, container, false);

        cartRecyclerView = (RecyclerView) root.findViewById(R.id.cart_items_recyclerview);
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

        return root;
    }


}