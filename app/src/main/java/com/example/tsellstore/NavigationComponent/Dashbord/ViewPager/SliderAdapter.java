package com.example.tsellstore.NavigationComponent.Dashbord.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tsellstore.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModel> sliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_slidder, container, false);
        ImageView banner = view.findViewById(R.id.banner_slidde_imageview);
        ConstraintLayout constraintLayout = view.findViewById(R.id.banner_slidde_constraintLayout);
        //constraintLayout r upre background Tint set krbo
//        constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).getBackgroundColor())));
        banner.setImageResource(sliderModelList.get(position).getBanner());
        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }


}
