package com.example.tsellstore.NavigationComponent.MyWishList;

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

public class MyWishListFragment extends Fragment {

    private RecyclerView myWishListRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mywishlist, container, false);

        myWishListRecyclerView = (RecyclerView) root.findViewById(R.id.my_wishList_RecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myWishListRecyclerView.setLayoutManager(layoutManager);

        List<MyWishListModel> myWishListModelList = new ArrayList<>();

        MyWishlistAdapter adapter = new MyWishlistAdapter(myWishListModelList,true);
        myWishListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }
}