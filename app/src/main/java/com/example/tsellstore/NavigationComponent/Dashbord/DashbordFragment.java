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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.example.tsellstore.NavigationComponent.Dashbord.GridProduct.GridProductAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductADAPTER;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.NavigationComponent.MainActivity;
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

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    //Category fake list
    private List<CatagoryModel> categoryModelFakeList = new ArrayList<>();
    //Dashbord fake list
    private List<DashbordModel> dashbordModelFakeList = new ArrayList<>();

    //swipe Refresh Layout
    public static SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView categoryRecyclerView;
    private CatagoryAdapter categoryAdapter;
    RecyclerView dashbord_recyclerview;
    private ImageView noInternetConnection;
    private Button reloadBtn;


    private DashbordAdapter dashbordAdapter;


    public DashbordFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashbord, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        reloadBtn = (Button) view.findViewById(R.id.reload_btn);

        //adding the recycler view
        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.catagory_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
        //create the list and the list into Adapter

        ////////////////////////////--------Main Recycler View ------------------>>>>>>>>>>>>>>>>>>>
        dashbord_recyclerview = (RecyclerView) view.findViewById(R.id.dashBord_RecyclerView);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dashbord_recyclerview.setLayoutManager(testingLayoutManager);


        //category fake list
        categoryModelFakeList.add(new CatagoryModel("null", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));
        categoryModelFakeList.add(new CatagoryModel("", ""));

        //dashbord fake list
        List<SliderModel> sliderModeFakelList = new ArrayList<>();
        sliderModeFakelList.add(new SliderModel("null", "#b9b9b9"));
        sliderModeFakelList.add(new SliderModel("null", "#b9b9b9"));
        sliderModeFakelList.add(new SliderModel("null", "#b9b9b9"));
        //HorizontalScrollProduct fake list
        List<HorizontalScrollProductModel> horizontalScrollProductModelFakeList = new ArrayList<>();
        horizontalScrollProductModelFakeList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        horizontalScrollProductModelFakeList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        horizontalScrollProductModelFakeList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        horizontalScrollProductModelFakeList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        horizontalScrollProductModelFakeList.add(new HorizontalScrollProductModel("", "", "", "", ""));

        dashbordModelFakeList.add(new DashbordModel(0,sliderModeFakelList));
        dashbordModelFakeList.add(new DashbordModel(1,"","#d9d9d9"));
        dashbordModelFakeList.add(new DashbordModel(2,"","#d9d9d9",horizontalScrollProductModelFakeList,new ArrayList<>()));
        dashbordModelFakeList.add(new DashbordModel(2,"","#d9d9d9",horizontalScrollProductModelFakeList));


        categoryAdapter = new CatagoryAdapter(categoryModelFakeList);


        dashbordAdapter = new DashbordAdapter(dashbordModelFakeList);


        ///////--------->>>>> Check the Internet Permission
        noInternetConnection = (ImageView) view.findViewById(R.id.no_internet_connection);
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() == true) { /////////////////--------->Check the Internet Permission
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternetConnection.setVisibility(View.GONE);
            reloadBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            dashbord_recyclerview.setVisibility(View.VISIBLE);

            //categoryModel
            if (categoryModelList.size() == 0) {
                loadCategories(categoryRecyclerView, getContext());
            } else {
                categoryAdapter = new CatagoryAdapter(categoryModelList);
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerView.setAdapter(categoryAdapter);
            if (lists.size() == 0) {
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<DashbordModel>());

                loadFragmentData(dashbord_recyclerview, getContext(), 0, "HOME");
            } else {
                dashbordAdapter = new DashbordAdapter(lists.get(0));
                dashbordAdapter.notifyDataSetChanged();
            }
            dashbord_recyclerview.setAdapter(dashbordAdapter);
        } else {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            categoryRecyclerView.setVisibility(View.GONE);
            dashbord_recyclerview.setVisibility(View.GONE);

            Glide.with(this).load(R.drawable.ic_home).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            reloadBtn.setVisibility(View.VISIBLE);
        }
        //swipeRefresh Layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });

        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadPage();
            }
        });
        return view;
    }

    //reload page
    private void reloadPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
        categoryModelList.clear();
        lists.clear(); //database Query te Dash bord Model list
        loadedCategoriesNames.clear();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternetConnection.setVisibility(View.GONE);
            reloadBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            dashbord_recyclerview.setVisibility(View.VISIBLE);

            categoryRecyclerView.setAdapter(categoryAdapter); //original adapter set korchi database Queries r moddhe
            dashbord_recyclerview.setAdapter(dashbordAdapter);
            //categories load hbe
            loadCategories(categoryRecyclerView, getContext());
            //fragment r moddhe data load hbe
            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<DashbordModel>());
            //ekhane ekta parameter create krbo cz jkn refresh ses hoye jabe tkn swipe Refresh Layout progressbar invisible hoye jabe
            loadFragmentData(dashbord_recyclerview, getContext(), 0, "HOME");

        } else {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            Toast.makeText(getActivity(), "No Internet Connection ...", Toast.LENGTH_SHORT).show();
            categoryRecyclerView.setVisibility(View.GONE);
            dashbord_recyclerview.setVisibility(View.GONE);
            Glide.with(getActivity()).load(R.drawable.ic_home).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            reloadBtn.setVisibility(View.VISIBLE);

            swipeRefreshLayout.setRefreshing(false);
        }
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