package com.example.tsellstore.NavigationComponent.Myorders;

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

public class MyOrdersFragment extends Fragment {

    private RecyclerView orderRecyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_myorders, container, false);
        orderRecyclerview = (RecyclerView) root.findViewById(R.id.my_orders_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderRecyclerview.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.dress_one,3,"Sweetiee dress","delivered on Mon,2 May 2021"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.dress_one,2,"Sweetiee dress","delivered on Mon,2 May 2021"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.dress_one,0,"Sweetiee dress","Canceled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.dress_one,4,"Sweetiee dress","delivered on Mon,2 May 2021"));

        MyOrderAdapter adapter = new MyOrderAdapter(myOrderItemModelList);
        orderRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }
}