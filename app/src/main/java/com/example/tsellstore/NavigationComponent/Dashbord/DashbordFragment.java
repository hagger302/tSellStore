package com.example.tsellstore.NavigationComponent.Dashbord;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.tsellstore.NavigationComponent.Dashbord.GridProduct.GridProductAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductADAPTER;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DashbordFragment extends Fragment {

    private RecyclerView recyclerView;
    private CatagoryAdapter adapter;

    //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
    private ViewPager bannerSliderViewPagger;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //////////////////////////--------Strip add Image---------->>>>>>>>>>>>>>>>>>>>>
    private ImageView stripAddImage;
    private ConstraintLayout stripConstraintLayout;

    //////////////////////////--------Horizontal product Layout---------->>>>>>>>>>>>>>>>>>>>>
    private TextView todays_deals;
    private Button view_all;
    private RecyclerView horizontal_list_item_recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashbord, container, false);

        //adding the recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.catagory_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //create the list and the list into Adapter
        List<CatagoryModel> catagoryModelList = new ArrayList<CatagoryModel>();

        catagoryModelList.add(new CatagoryModel("Link", "Home"));
        catagoryModelList.add(new CatagoryModel("Link", "Men"));
        catagoryModelList.add(new CatagoryModel("Link", "Women"));
        catagoryModelList.add(new CatagoryModel("Link", "Housing"));
        catagoryModelList.add(new CatagoryModel("Link", "Ornaments"));
        catagoryModelList.add(new CatagoryModel("Link", "AutoMobiles"));
        catagoryModelList.add(new CatagoryModel("Link", "Abrode Products"));

        if (getActivity() != null) {
            adapter = new CatagoryAdapter(catagoryModelList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>

        bannerSliderViewPagger = (ViewPager) view.findViewById(R.id.banner_slider_viewPager);
        sliderModelList = new ArrayList<>();

        //last dui ta banner
        sliderModelList.add(new SliderModel(R.drawable.ic_facebook, "#ff22")); //index 0
        sliderModelList.add(new SliderModel(R.drawable.ic_gift, "#ff22"));//index 1
        sliderModelList.add(new SliderModel(R.drawable.ic_add_to_basket, "#ff22"));//index 2

        sliderModelList.add(new SliderModel(R.drawable.ic_account, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_ribbon_banner_silhouette, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_round_account_button_with_user_inside, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_contact, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_facebook, "#ff22"));


        //nicher ta
        sliderModelList.add(new SliderModel(R.drawable.ic_gift, "#ff22"));
        //first dui ta banner
        sliderModelList.add(new SliderModel(R.drawable.ic_add_to_basket, "#ff22"));
        sliderModelList.add(new SliderModel(R.drawable.ic_account, "#ff22"));

        /**
         * all time the banner will recycle mane all time banner colte e thakbe
         * so,,,,,,,,,,,,,,,,,,,
         * first dui ta banner ek bare niche copy kore paste krbo
         * then,
         * last r dui ta banner copy kore ekbane niche paste krbo
         * then,
         * pagerLoper name ekta method banabo jeta amader page k infinite loop kore dibe
         */

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPagger.setAdapter(sliderAdapter);

        bannerSliderViewPagger.setClipToPadding(false);
        bannerSliderViewPagger.setPageMargin(20);
        bannerSliderViewPagger.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) { //idle dewa r katron holo amader page r animationb ss and page second page e shift hoye gese so, eta user r samne dhora porbe na
                    PagerLooper();
                }

            }
        };

        bannerSliderViewPagger.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();
        //user jkn touch kore dhore rakbe tkn bondho hbe slide show --> facebook story type
        bannerSliderViewPagger.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PagerLooper();
                stopBannerSlideShow();

                if (event.getAction() == MotionEvent.ACTION_UP) { //ACTION_UP mane holo user jkn hat sorai nibe
                    startBannerSlideShow();
                }
                return false;
            }
        });
        //////////////////////////--------Strip add Image---------->>>>>>>>>>>>>>>>>>>>>
        stripAddImage = (ImageView) view.findViewById(R.id.strip_add_image);
        stripConstraintLayout = (ConstraintLayout) view.findViewById(R.id.strip_add_image_Constraintlayout);
        //stripAddImage.setImageResource(R.drawable.ic_mail);
        stripConstraintLayout.setBackgroundColor(Color.parseColor("#121212"));


        //////////////////////////--------Horizontal product Layout---------->>>>>>>>>>>>>>>>>>>>>
        todays_deals = (TextView) view.findViewById(R.id.horizontal_scroll_layout_title);
        view_all = (Button) view.findViewById(R.id.horizontal_scroll_layout_button_viewAll);
        horizontal_list_item_recyclerView = (RecyclerView) view.findViewById(R.id.horizontal_scroll_layout_recyclerview);


        List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one,"Gawn","Girl's Dress","bdt. 12000"));
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one,"Gawn","Girl's Dress","bdt. 12000"));
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one,"Gawn","Girl's Dress","bdt. 12000"));
        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.dress_one,"Gawn","Girl's Dress","bdt. 12000"));

        HorizontalScrollProductADAPTER adapter = new HorizontalScrollProductADAPTER(horizontalScrollProductModelList);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        horizontal_list_item_recyclerView.setLayoutManager(linearLayoutManager1);
        horizontal_list_item_recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //////////////////////////--------Grid Product Image---------->>>>>>>>>>>>>>>>>>>>>
        TextView productTitle = (TextView) view.findViewById(R.id.grid_product_title);
        Button view_all = (Button) view.findViewById(R.id.grid_product_button_view_all);
        GridView  gridView = (GridView) view.findViewById(R.id.grid_product_gridview);

        gridView.setAdapter(new GridProductAdapter(horizontalScrollProductModelList));

        ////////////////////////////--------Main Recycler View ------------------>>>>>>>>>>>>>>>>>>>
        RecyclerView testing = (RecyclerView) view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        return view;
    }
    //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>

    private void PagerLooper() {
        //1 by 1 banner chage hbe

        if (currentPage == sliderModelList.size() - 2) {
            currentPage = 2;
            bannerSliderViewPagger.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPagger.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow() {
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()) {
                    currentPage = 1;
                }
                bannerSliderViewPagger.setCurrentItem(currentPage++, true); //animation true
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow() {
        timer.cancel();
    }
}