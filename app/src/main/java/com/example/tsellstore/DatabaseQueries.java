package com.example.tsellstore;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tsellstore.NavigationComponent.Dashbord.CatagoryAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.CatagoryModel;
import com.example.tsellstore.NavigationComponent.Dashbord.DashbordFragment;
import com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct.HorizontalScrollProductModel;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordAdapter;
import com.example.tsellstore.NavigationComponent.Dashbord.MainRecyclerView.DashbordModel;
import com.example.tsellstore.NavigationComponent.Dashbord.ViewPager.SliderModel;
import com.example.tsellstore.NavigationComponent.MyWishList.MyWishListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQueries {
    public static List<CatagoryModel> categoryModelList = new ArrayList<>();
    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    //public static List<DashbordModel> dashbordModelList = new ArrayList<>();

    public static List<List<DashbordModel>> lists = new ArrayList<>(); //firebase theke j category access korchi
    public static  List<String> loadedCategoriesNames = new ArrayList<>(); //j category access kortechi sei category r name store korchi

    public static void loadCategories(CatagoryAdapter categoryAdapter, Context context){

        firebaseFirestore.collection("CATEGORIES").orderBy("index")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    //category collection er joto gula data ase oi data gula k return korabo for loop maddhome
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        categoryModelList.add(new CatagoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
                    }
                    categoryAdapter.notifyDataSetChanged();
                }else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, "The error is : "+error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static void loadFragmentData(DashbordAdapter dashbordAdapter,Context context,int index,String categoryName){
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").document(categoryName.toUpperCase())
                .collection("TOP_DEALS").orderBy("index")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    //category collection er joto gula data ase oi data gula k return korabo for loop maddhome
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                        if((long) documentSnapshot.get("view_type") == 0){
                            List<SliderModel> sliderModelList = new ArrayList<>();
                            Long no_of_banners =(Long) (documentSnapshot.get("no_of_banners")) ;

                            for(long x=1 ; x<no_of_banners+1 ; x++){

                                sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+x).toString(),
                                        documentSnapshot.get("banner_" + x + "_background").toString() ));
                            }
                            lists.get(index).add(new DashbordModel(0,sliderModelList));
                        }else if((long) documentSnapshot.get("view_type") == 1){
                            lists.get(index).add(new DashbordModel(1,documentSnapshot.get("strip_add_banner").toString(),
                                    documentSnapshot.get("strip_add_banner_background").toString()));


                        }else if((long) documentSnapshot.get("view_type") == 2){
                            /**
                             * ekhane duto list banabo ->
                             * 1.horizontalScrollProductModelList
                             * 2. horizontalScrollProductModelList r item e press korle
                             * recyclerview te sokol product dekhabe. ekane layout use korbo MyWishList r layout.
                             */

                            List<MyWishListModel> viewAllProductList = new ArrayList<>();

                            List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();

                            Long no_of_products =(Long) (documentSnapshot.get("no_of_products")) ;
                            for(long x=1 ; x<no_of_products+1 ; x++){
                                //At first image and data  set kore dibe  model r maddhe
                                horizontalScrollProductModelList.add(new HorizontalScrollProductModel(
                                        documentSnapshot.get("product_ID_"+x).toString(),
                                        documentSnapshot.get("product_image_"+x).toString(),
                                        documentSnapshot.get("product_title_"+x).toString(),
                                        documentSnapshot.get("product_subtitle_"+x).toString(),
                                        documentSnapshot.get("product_price_"+x).toString()));

                                viewAllProductList.add(new MyWishListModel(documentSnapshot.get("product_image_"+x).toString(),
                                        documentSnapshot.get("product_full_title_"+x).toString(),
                                        (long) documentSnapshot.get("free_coupons_"+x),
                                        documentSnapshot.get("average_ratting_"+x).toString(),
                                        (long)documentSnapshot.get("total_rattings_"+x),
                                        documentSnapshot.get("product_price_"+x).toString(),
                                        documentSnapshot.get("cutted_price_"+x).toString(),
                                        (boolean) documentSnapshot.get("COD_"+x)
                                        ));
                            }
                            lists.get(index).add(new DashbordModel(2,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(),horizontalScrollProductModelList,viewAllProductList));//adapter r moddhe giye viewAllProductList ta set korte hobe ->view all + dashbordAdapter
                        }else if((long) documentSnapshot.get("view_type") == 3){
                            List<HorizontalScrollProductModel> GridModelList = new ArrayList<>();
                            Long no_of_products =(Long) (documentSnapshot.get("no_of_products")) ;
                            for(long x=1 ; x<no_of_products+1 ; x++){
                                //At first image and data  set kore dibe  model r maddhe
                                GridModelList.add(new HorizontalScrollProductModel(
                                        documentSnapshot.get("product_ID_"+x).toString(),
                                        documentSnapshot.get("product_image_"+x).toString(),
                                        documentSnapshot.get("product_title_"+x).toString(),
                                        documentSnapshot.get("product_subtitle_"+x).toString(),
                                        documentSnapshot.get("product_price_"+x).toString()));
                            }
                            lists.get(index).add(new DashbordModel(3,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(),GridModelList));
                        }

                    }
                    dashbordAdapter.notifyDataSetChanged();
                    DashbordFragment.swipeRefreshLayout.setRefreshing(false);
                }else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, "The error is : "+error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
