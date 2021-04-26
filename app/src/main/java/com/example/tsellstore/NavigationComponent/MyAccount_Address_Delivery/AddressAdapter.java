package com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.R;

import java.util.List;

import static com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.DeliveryActivity.DELIVERY_ADDRESS;
import static com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.MyAddressActivity.refreshItemLayotData;
import static com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.MyAccountFragment.MANAGE_ADDRESS;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<AddressModel> addressModelList;
    private int MODE;
    private int preSelectedPosition = -1;
    private LinearLayout optionContainer;

    public AddressAdapter(List<AddressModel> addressModelList,int MODE) {
        this.addressModelList = addressModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adresses_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = addressModelList.get(position).getFull_name();
        String address = addressModelList.get(position).getAddress();
        String pincode = addressModelList.get(position).getPincode();
        Boolean selected = addressModelList.get(position).getSelected();

        holder.setData(name,address,pincode,selected,position);
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name  = (TextView) itemView.findViewById(R.id.name_item_address);
            address  = (TextView) itemView.findViewById(R.id.address_address);
            pincode  = (TextView) itemView.findViewById(R.id.pincode_address);
            icon = (ImageView) itemView.findViewById(R.id.icon_view);
            optionContainer = (LinearLayout) itemView.findViewById(R.id.option_container);
        }

        private void setData(String namee, String addresses, String pincodee,Boolean selected,int position){
            name.setText(namee);
            address.setText(addresses);
            pincode.setText(pincodee);

            if(MODE == DELIVERY_ADDRESS){
                icon.setImageResource(R.drawable.ic_select);

                if(selected){
                    icon.setVisibility(View.VISIBLE);
                    //ager theke item view te j item ta show kore sei item ta preSelectedPosition variable r moddhe store kore raklam position r maddhome
                    preSelectedPosition = position;
                }else {
                    icon.setVisibility(View.INVISIBLE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(preSelectedPosition != position) {
                            //setSelected() ei method ta paschi Model class thele
                            addressModelList.get(position).setSelected(true);
                            addressModelList.get(preSelectedPosition).setSelected(false);

                            /**
                             * j dui ta layout e ami data changes korchi sei dui ta layout k refresh
                             * krte hobe My Address Activity te jabo - adapter r notifiedDataSetchange korsi
                             * mane recyclerview te data change hosche emn vabe amra item layout r data o refresh krte pari
                             * public Static method banabo cz eta adapter e call krbo. Static krbo cz ei classs r kno obj
                             * na baniye direct ei method k access krte parbo
                             */
                            refreshItemLayotData(preSelectedPosition, position);
                            preSelectedPosition = position;
                        }
                    }
                });
            }else if(MODE == MANAGE_ADDRESS){
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.ic_more);

                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItemLayotData(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = position;
                    }
                });
                //jkn e user kno itemview te click korbe tkn e Edit r Remove r option ta gone hoye jabe
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //at first chek krbo recyclerview r kon item ta prothom theke select ase - r eta pabo preSelectedPosition ekn theke
                        refreshItemLayotData(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });
            }
        }
    }
}
