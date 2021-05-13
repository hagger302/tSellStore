package com.example.tsellstore;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.CaseMap;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductSpecificationItemAdapter extends RecyclerView.Adapter<ProductSpecificationItemAdapter.ViewHolder> {
    private List<ProducSpecificationItemModel> specificationItemModelList;

    public ProductSpecificationItemAdapter(List<ProducSpecificationItemModel> specificationItemModelList) {
        this.specificationItemModelList = specificationItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (specificationItemModelList.get(position).getType()) {
            case 0:
                return ProducSpecificationItemModel.SPECIFICATION_TITLE;
            case 1:
                return ProducSpecificationItemModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ProducSpecificationItemModel.SPECIFICATION_TITLE:
                //ekhane amader kase kno layout nai tai seta amra backend theke pathabo
                TextView title = new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, parent.getContext()),
                        setDp(16, parent.getContext()), setDp(16, parent.getContext()), setDp(16, parent.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProducSpecificationItemModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item_layout, parent, false);
                return new ViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (specificationItemModelList.get(position).getType()) {
            case ProducSpecificationItemModel.SPECIFICATION_TITLE:
                holder.setTitle(specificationItemModelList.get(position).getTitle());
                break;
            case ProducSpecificationItemModel.SPECIFICATION_BODY:
                String F_name = specificationItemModelList.get(position).getFeatureName();
                String F_value = specificationItemModelList.get(position).getFeatureValue();
                holder.setData(F_name, F_value);
                break;
            default:
                return;
        }


    }

    @Override
    public int getItemCount() {
        return specificationItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView featurename;
        private TextView featurevalue;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        private void setTitle(String titleText) {
            title = (TextView) itemView;
            title.setText(titleText);
        }

        private void setData(String FeatureName, String FeatureValue) {
            featurename = (TextView) itemView.findViewById(R.id.feature_name);
            featurevalue = (TextView) itemView.findViewById(R.id.feature_value);
            featurename.setText(FeatureName);
            featurevalue.setText(FeatureValue);
        }
    }

    private int setDp(int dp, Context context) {
        //convert Pixels to DP
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

}
