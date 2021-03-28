package com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView;

import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;

import java.util.List;

public class DashbordModel {
    public static final int BANNER_SLIDER = 0;
    private int type;

    //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>


    private List<SliderModel> sliderModelList;

    public DashbordModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
}
