package com.example.tsellstore.NavigationComponent.Dashbord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.R;

import java.util.List;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder> {
    private Context mContext;

    private List<CatagoryModel> catagoryModelList;

    public CatagoryAdapter(List<CatagoryModel> catagoryModelList) {
        this.catagoryModelList = catagoryModelList;
    }

    /**
     * we bind all the data that we get from the Category Model in onBindViewHolder mathod
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String icon = catagoryModelList.get(position).getCatagoryLink();
        String name = catagoryModelList.get(position).getCatagoryName();

        holder.setCategoryName(name);
    }

    @Override
    public int getItemCount() {
        return catagoryModelList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private ImageView catagory_image_item;
        private TextView catagory_Name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catagory_image_item = (ImageView) itemView.findViewById(R.id.CatagoryIcon);
            catagory_Name = (TextView) itemView.findViewById(R.id.catagory_Name);
        }

        private void setCategoryIcon(){

        }
        private void setCategoryName(String name){
            catagory_Name.setText(name);
        }
    }
}
