package com.example.tsellstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




public class ProductDescriptionFragment extends Fragment {

    public ProductDescriptionFragment() {
        // Required empty public constructor
    }

    //Activity theke access krbo ei fragment
    private TextView product_Body;
    public String body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_description, container, false);
        product_Body = (TextView) view.findViewById(R.id.tv_product_description);
        //Data set krbo Adapter r maddhome --> ProductDetailsViewPagerAdapter
        product_Body.setText(body);

        return view;
    }
}