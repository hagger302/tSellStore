package com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DashbordAdapter extends RecyclerView.Adapter {

    //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
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
                return DashbordModel.BANNER_SLIDER;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            /**
             *  BannerSliderViewHolder r maddhome view ta return korbo.
             */
            case DashbordModel.BANNER_SLIDER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider_layout, parent, false);
                return new BannerSliderViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /**
         * Data Binding korbo Banner Slider e
         */
        switch (dashbordModelList.get(position).getType()) {
            case DashbordModel.BANNER_SLIDER:
                /**
                 * Banner Slider e joto gula list ase sob gula access korbo tai list banabo
                 * list ta hobe slider model r thn list ber kora r por amra viewHolder e bosabo
                 * onk gula view niye kaj korbo tw oi jonno specicly bole dibo kon iew te bosbe
                 * orthat viewholder custing korbo
                 */
                List<SliderModel> sliderModelList = dashbordModelList.get(position).getSliderModelList();

                ((BannerSliderViewHolder) holder).setBannerSliderViewPagger(sliderModelList);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return dashbordModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
        private ViewPager bannerSliderViewPagger;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerSliderViewPagger = (ViewPager) itemView.findViewById(R.id.banner_slider_viewPager);

        }

        //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>

        private void setBannerSliderViewPagger(List<SliderModel> sliderModelList) {
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
                        PagerLooper(sliderModelList);
                    }

                }
            };

            bannerSliderViewPagger.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(sliderModelList);
            //user jkn touch kore dhore rakbe tkn bondho hbe slide show --> facebook story type
            bannerSliderViewPagger.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    PagerLooper(sliderModelList);
                    stopBannerSlideShow();

                    if (event.getAction() == MotionEvent.ACTION_UP) { //ACTION_UP mane holo user jkn hat sorai nibe
                        startBannerSlideShow(sliderModelList);
                    }
                    return false;
                }
            });
        }

        private void PagerLooper(List<SliderModel> sliderModelList) {
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

        private void startBannerSlideShow(List<SliderModel> sliderModelList) {
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
}
