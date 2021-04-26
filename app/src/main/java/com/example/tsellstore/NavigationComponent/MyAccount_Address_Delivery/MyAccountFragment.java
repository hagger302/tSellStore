package com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tsellstore.R;

public class MyAccountFragment extends Fragment {


    public static final int MANAGE_ADDRESS = 1;
    private Button viewAllAddressBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);
        viewAllAddressBtn = (Button) view.findViewById(R.id.view_all_address_btn);
        viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyAddressActivity.class);
                intent.putExtra("MODE",MANAGE_ADDRESS);
                startActivity(intent);
            }
        });
        return view;
    }
}