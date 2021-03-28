package com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsellstore.R;

import java.util.List;

public class DashbordAdapter extends RecyclerView.Adapter {

    public DashbordModel dashbordModel;


    private List<DashbordModel> dashbordModelList;

    public DashbordAdapter(List<DashbordModel> dashbordModelList) {
        this.dashbordModelList = dashbordModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dashbordModelList.get(position).getType()) {
            /**
             * getType() r moddhe mainDashbordRecyclerModelList maddhome BANNER_SLIDER cole asche
             * ebr ota catch korbo onCreateViewHolder r moddhe viewType eta diye thn
             * viewType diye LayoutInflater r maddhome BANNER_SLIDER r layout inflate korbo.
             */
            case 0:
                return dashbordModel.BANNER_SLIDER;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case dashbordModel.BANNER_SLIDER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider_layout, parent, false);
                return new BannerSliderViewHolder(view);
            default:
                return null;
        }
        /**
         *  BannerSliderViewHolder r maddhome view ta return korbo.
         */
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
