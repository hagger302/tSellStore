package com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView;

import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;

import java.util.List;

public class DashbordModel {
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_ADD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;
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

    //////////////////////////--------Strip add Image---------->>>>>>>>>>>>>>>>>>>>>
    private int resource;
    private String backgroundColor;

    public DashbordModel(int type, int resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }
    public int getResource() {
        return resource;
    }
    public void setResource(int resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    //////////////////////////--------Horizontal product Layout && Grid Product Image---------->>>>>>>>>>>>>>>>>>>>>
    private String title;
    private List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public DashbordModel(int type, String title, List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.type = type;
        this.title = title;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalScrollProductModel> getHorizontalScrollProductModelList() {
        return horizontalScrollProductModelList;
    }

    public void setHorizontalScrollProductModelList(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    //////////////////////////--------Horizontal product Layout && Grid Product Image---------->>>>>>>>>>>>>>>>>>>>>
}
