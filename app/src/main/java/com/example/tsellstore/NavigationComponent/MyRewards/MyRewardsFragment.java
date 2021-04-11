package com.example.tsellstore.NavigationComponent.MyRewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.MyRewardAdapter;
import com.example.tsellstore.MyRewardModel;
import com.example.tsellstore.R;

import java.util.ArrayList;
import java.util.List;

public class MyRewardsFragment extends Fragment {

    private RecyclerView myRewardRecyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_rewards, container, false);

        myRewardRecyclerView = (RecyclerView) root.findViewById(R.id.my_reward_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRewardRecyclerView.setLayoutManager(layoutManager);

        List<MyRewardModel> myRewardModelList = new ArrayList<>();
        myRewardModelList.add(new MyRewardModel("Discount for Sweetie","20th April,2021","Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));
        myRewardModelList.add(new MyRewardModel("Cash back","21th April,2021","Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));
        myRewardModelList.add(new MyRewardModel("Buy 2 Get 1 Free","22th April,2021","Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));

        MyRewardAdapter adapter = new MyRewardAdapter(myRewardModelList);
        myRewardRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }
}