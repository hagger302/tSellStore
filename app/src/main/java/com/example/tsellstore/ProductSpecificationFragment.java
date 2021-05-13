package com.example.tsellstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationFragment extends Fragment {

    private RecyclerView recyclerView;
    public List<ProducSpecificationItemModel> specificationItemModelList;

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_specification, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.product_specification_recyclerview);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ProductSpecificationItemAdapter adapter = new ProductSpecificationItemAdapter(specificationItemModelList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}