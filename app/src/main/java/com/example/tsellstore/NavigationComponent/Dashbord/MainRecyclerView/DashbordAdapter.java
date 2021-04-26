package com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tsellstore.NavigationComponent.Dashbord.GridProduct.GridProductAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.GridProduct.GridProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductADAPTER;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.ProductDetailsActivity;
import com.example.tsellstore.R;
import com.example.tsellstore.ViewAllActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DashbordAdapter extends RecyclerView.Adapter {

    //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
    private List<DashbordModel> dashbordModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public DashbordAdapter(List<DashbordModel> dashbordModelList) {
        this.dashbordModelList = dashbordModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
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
            case 1:
                return DashbordModel.STRIP_ADD_BANNER;
            case 2:
                return DashbordModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return  DashbordModel.GRID_PRODUCT_VIEW;
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
            //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
            case DashbordModel.BANNER_SLIDER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider_layout, parent, false);
                return new BannerSliderViewHolder(view);

            case DashbordModel.STRIP_ADD_BANNER:
                View strip_add_banner_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_add_layout,parent,false);
                return new StripAddBannerViewHolder(strip_add_banner_view);

            case DashbordModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontal_product_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout,parent,false);
                return new HorizontalProductViewViewHolder(horizontal_product_view);

            case DashbordModel.GRID_PRODUCT_VIEW:
                View grid_product_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_laout,parent,false);
                return new GridProductViewViewHolder(grid_product_view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /**
         * Data Binding korbo Banner Slider e
         */
        //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
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

            case DashbordModel.STRIP_ADD_BANNER:
                int resource = dashbordModelList.get(position).getResource();
                String color = dashbordModelList.get(position).getBackgroundColor();

                ((StripAddBannerViewHolder)holder).setStripAdd(resource,color);
                break;

               case DashbordModel.HORIZONTAL_PRODUCT_VIEW:
                   String title = dashbordModelList.get(position).getTitle();
                   List<HorizontalScrollProductModel> horizontalScrollProductModelList = dashbordModelList.get(position).getHorizontalScrollProductModelList();

                   ((HorizontalProductViewViewHolder)holder).setHorizontalProductLayout(horizontalScrollProductModelList,title);
                   break;

            case DashbordModel.GRID_PRODUCT_VIEW:
                String gridtitle = dashbordModelList.get(position).getTitle();
                List<HorizontalScrollProductModel> gridScrollProductModelList = dashbordModelList.get(position).getHorizontalScrollProductModelList();

                ((GridProductViewViewHolder)holder).setGridProductLayout(gridScrollProductModelList,gridtitle);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return dashbordModelList.size();
    }
    //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
        private ViewPager bannerSliderViewPagger;
        //private int currentPage = 2;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 2000;
        final private long PERIOD_TIME = 2000;
        private List<SliderModel> arrangeList;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPagger = (ViewPager) itemView.findViewById(R.id.banner_slider_viewPager);
        }

        //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>

        private void setBannerSliderViewPagger(List<SliderModel> sliderModelList) {

            /**
             * jkn view recycle hoi tkn abr notun kore ei method call hoi tai simply check korbo
             * timer null ase ki na
             * jodi null na hoi tobe timer cancel hobe r startBannerSlideShow r maddhome new timer add hoye jabe
             *
             * jkn amra view k recycle kori tkn current page r no. 2 set krte hobe - e jonno method r moddhe set krbo
             * current page r variable upre declare kore rakchi caz bar bar ei method call hoi so
             */
            currentPage = 2;
            if(timer != null){
                timer.cancel();
            }
            //tric for infinte bannar slider model data
            arrangeList = new ArrayList<>();
            for(int x = 0; x<sliderModelList.size();x++){
                arrangeList.add(x,sliderModelList.get(x)); //sliderModelList r sokol eliment copy hoye giyeche
            }
            //arrangement krbo
            arrangeList.add(0,sliderModelList.get(sliderModelList.size() - 2)); //second last item k 0 position e set kore diyechi
            arrangeList.add(1,sliderModelList.get(sliderModelList.size() - 1)); //second first item k 1 position e set kore diyechi
            arrangeList.add(sliderModelList.get(0));
            arrangeList.add(sliderModelList.get(1));


            SliderAdapter sliderAdapter = new SliderAdapter(arrangeList);
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
                        PagerLooper(arrangeList);
                    }

                }
            };

            bannerSliderViewPagger.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangeList);
            //user jkn touch kore dhore rakbe tkn bondho hbe slide show --> facebook story type
            bannerSliderViewPagger.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    PagerLooper(arrangeList);
                    stopBannerSlideShow();

                    if (event.getAction() == MotionEvent.ACTION_UP) { //ACTION_UP mane holo user jkn hat sorai nibe
                        startBannerSlideShow(arrangeList);
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

    //////////////////////////--------Strip add Image---------->>>>>>>>>>>>>>>>>>>>>
    public class StripAddBannerViewHolder extends RecyclerView.ViewHolder{
        private ImageView stripAddImage;
        private ConstraintLayout stripConstraintLayout;
        public StripAddBannerViewHolder(@NonNull View itemView) {
            super(itemView);

            stripAddImage = (ImageView) itemView.findViewById(R.id.strip_add_image);
            stripConstraintLayout = (ConstraintLayout) itemView.findViewById(R.id.strip_add_image_Constraintlayout);

        }
        private void setStripAdd(int resource,String backgroundColor){
            stripAddImage.setImageResource(resource);
            stripConstraintLayout.setBackgroundColor(Color.parseColor(backgroundColor));
        }
    }

    //////////////////////////--------Horizontal product Layout---------->>>>>>>>>>>>>>>>>>>>>
    public class HorizontalProductViewViewHolder extends RecyclerView.ViewHolder{
        private TextView todays_deals;
        private Button view_all;
        private RecyclerView horizontal_list_item_recyclerView;

        public HorizontalProductViewViewHolder(@NonNull View itemView) {
            super(itemView);

            todays_deals = (TextView) itemView.findViewById(R.id.horizontal_scroll_layout_title);
            view_all = (Button) itemView.findViewById(R.id.horizontal_scroll_layout_button_viewAll);
            horizontal_list_item_recyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);

            horizontal_list_item_recyclerView.setRecycledViewPool(recycledViewPool);

        }

        private void setHorizontalProductLayout(List<HorizontalScrollProductModel>horizontalScrollProductModelList,String title){
            todays_deals.setText(title);

            if(horizontalScrollProductModelList.size() >3){
                view_all.setVisibility(View.VISIBLE);

                view_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        intent.putExtra("layout_code",0);
                        itemView.getContext().startActivity(intent);
                    }
                });
            }else{
                view_all.setVisibility(View.INVISIBLE);
            }

            HorizontalScrollProductADAPTER adapter = new HorizontalScrollProductADAPTER(horizontalScrollProductModelList);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
            horizontal_list_item_recyclerView.setLayoutManager(linearLayoutManager1);
            horizontal_list_item_recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    //////////////////////////--------Grid Product Image---------->>>>>>>>>>>>>>>>>>>>>
    public class GridProductViewViewHolder extends  RecyclerView.ViewHolder{
        private androidx.gridlayout.widget.GridLayout gridLayout;
        private Button view_all_grid;
        private TextView productTitle;

        public GridProductViewViewHolder(@NonNull View itemView) {
            super(itemView);
             productTitle = (TextView) itemView.findViewById(R.id.grid_product_title);
             view_all_grid = (Button) itemView.findViewById(R.id.grid_product_button_view_all);
            gridLayout =  itemView.findViewById(R.id.grid_layout);
        }
        private void setGridProductLayout(List<HorizontalScrollProductModel>horizontalScrollProductModelList,String title){
            productTitle.setText(title);

            for(int x=0; x<4; x++){
                ImageView productImage = gridLayout.getChildAt(x).findViewById(R.id.h_s_product_image);
                TextView productTitle = gridLayout.getChildAt(x).findViewById(R.id.h_s_product_title);
                TextView productDescription = gridLayout.getChildAt(x).findViewById(R.id.h_s_product_description);
                TextView productPrice = gridLayout.getChildAt(x).findViewById(R.id.h_s_product_price);

                productImage.setImageResource(horizontalScrollProductModelList.get(x).getProductImage());
                productTitle.setText(horizontalScrollProductModelList.get(x).getProductTitle());
                productDescription.setText(horizontalScrollProductModelList.get(x).getProductDescription());
                productPrice.setText(horizontalScrollProductModelList.get(x).getProductPrice());

                gridLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));

                gridLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }


            view_all_grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    intent.putExtra("layout_code",1);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
