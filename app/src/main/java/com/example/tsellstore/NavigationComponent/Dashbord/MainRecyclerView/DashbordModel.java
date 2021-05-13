package com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView;

import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.NavigationComponent.MyWishList.MyWishListModel;

import java.util.List;

public class DashbordModel {
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_ADD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;


    private int type;
    private String backgroundColor;

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
    private String resource;

    public DashbordModel(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    private String title; /////commonly use for Horizontal and Grid
    private List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    //////////////////////////--------Horizontal product Layout ---------->>>>>>>>>>>>>>>>>>>>>
    private List<MyWishListModel> viewAllProductList;

    public DashbordModel(int type, String title, String backgroundColor,List<HorizontalScrollProductModel> horizontalScrollProductModelList,List<MyWishListModel> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
        this.viewAllProductList = viewAllProductList;
    }

    public DashbordModel(int type, String title, String backgroundColor,List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    public List<MyWishListModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<MyWishListModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }
    //////////////////////////--------Horizontal product Layout---------->>>>>>>>>>>>>>>>>>>>>

    //////////////////////////--------Grid Product Layout---------->>>>>>>>>>>>>>>>>>>>>
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

    //////////////////////////--------Grid Product Layout---------->>>>>>>>>>>>>>>>>>>>>
}
