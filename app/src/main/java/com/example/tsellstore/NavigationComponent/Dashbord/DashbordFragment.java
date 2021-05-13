 package com.example.tsellstore.NavigationComponent.Dashbord;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.example.tsellstore.NavigationComponent.Dashbord.GridProduct.GridProductAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductADAPTER;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.tsellstore.DatabaseQueries.categoryModelList;

import static com.example.tsellstore.DatabaseQueries.lists;
import static com.example.tsellstore.DatabaseQueries.loadCategories;
import static com.example.tsellstore.DatabaseQueries.loadFragmentData;
import static com.example.tsellstore.DatabaseQueries.loadedCategoriesNames;


 public class DashbordFragment extends Fragment {

    private RecyclerView recyclerView;
    private CatagoryAdapter categoryAdapter;
    RecyclerView dashbord_recyclerview;
    private ImageView noInternetConnection;

    private DashbordAdapter dashbordAdapter;


    public DashbordFragment(){

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashbord, container, false);

        ///////--------->>>>> Check the Internet Permission
        noInternetConnection = (ImageView) view.findViewById(R.id.no_internet_connection);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() == true){ /////////////////--------->Check the Internet Permission
            noInternetConnection.setVisibility(View.GONE);

            //adding the recycler view
            recyclerView = (RecyclerView) view.findViewById(R.id.catagory_recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            //create the list and the list into Adapter

            categoryAdapter = new CatagoryAdapter(categoryModelList);
            recyclerView.setAdapter(categoryAdapter);

            if(categoryModelList.size() == 0){
                loadCategories(categoryAdapter,getContext());
            }else {
                categoryAdapter.notifyDataSetChanged();
            }

            ////////////////////////////--------Main Recycler View ------------------>>>>>>>>>>>>>>>>>>>
            dashbord_recyclerview = (RecyclerView) view.findViewById(R.id.dashBord_RecyclerView);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            dashbord_recyclerview.setLayoutManager(testingLayoutManager);



            if(lists.size() == 0){
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<DashbordModel>());
                dashbordAdapter = new DashbordAdapter(lists.get(0));
                loadFragmentData(dashbordAdapter,getContext(),0,"HOME");
            }else {
                dashbordAdapter = new DashbordAdapter(lists.get(0));
                dashbordAdapter.notifyDataSetChanged();
            }
            dashbord_recyclerview.setAdapter(dashbordAdapter);
        }else {
            Glide.with(this).load(R.drawable.ic_home).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
        }



        return view;
    }

    /**
     * //////////////////////////--------BANNER sLIDER vIEWpAGGER---------->>>>>>>>>>>>>>>>>>>>>
     *
     *     private void PagerLooper() {
     *         //1 by 1 banner chage hbe
     *
     *         if (currentPage == sliderModelList.size() - 2) {
     *             currentPage = 2;
     *             bannerSliderViewPagger.setCurrentItem(currentPage, false);
     *         }
     *         if (currentPage == 1) {
     *             currentPage = sliderModelList.size() - 3;
     *             bannerSliderViewPagger.setCurrentItem(currentPage, false);
     *         }
     *     }
     *
     *     private void startBannerSlideShow() {
     *         Handler handler = new Handler();
     *         Runnable update = new Runnable() {
     *             @Override
     *             public void run() {
     *                 if (currentPage >= sliderModelList.size()) {
     *                     currentPage = 1;
     *                 }
     *                 bannerSliderViewPagger.setCurrentItem(currentPage++, true); //animation true
     *             }
     *         };
     *         timer = new Timer();
     *         timer.schedule(new TimerTask() {
     *             @Override
     *             public void run() {
     *                 handler.post(update);
     *             }
     *         }, DELAY_TIME, PERIOD_TIME);
     *     }
     *
     *     private void stopBannerSlideShow() {
     *         timer.cancel();
     *     }
     */
}