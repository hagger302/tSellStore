package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsellstore.NavigationComponent.MainActivity;
import com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.DeliveryActivity;
import com.example.tsellstore.NavigationComponent.MyRewards.MyRewardAdapter;
import com.example.tsellstore.NavigationComponent.MyRewards.MyRewardModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.tsellstore.NavigationComponent.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImageViewPager;
    private TabLayout ViewPagerIndicator;
    private LinearLayout ratingContainer;
    private Button quopon_redemption_btn;

    private Button buy_now_btn;

    //---> Product Images layout
    private TextView product_title;
    private TextView averageRatingMiniView;
    private TextView totalRatingMiniView;
    private TextView productPrice;
    private TextView cuttedPrice;
    private TextView COD;
    private TextView tv_cod_indicator;
    //--------->reward with product layout
    private TextView rewardTitle;
    private TextView rewardBody;

    //-------->product description
    private ConstraintLayout productDetailsOnlyContainer;
    private ConstraintLayout productDetailsTabsContainer;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    private TextView productOnlyDescriptionBody;
    private List<ProducSpecificationItemModel> specificationItemModelList = new ArrayList<>();
    //------>product description tab layout
    private String productDescription;
    private String productOtherDescription;
    private int tabPosition = -1;

    //--------->RatingLayout
    private LinearLayout rattedNowContainer;
    private TextView totalRatings;
    private LinearLayout rattingNoContainer;
    private TextView totalratingsfigure;
    private LinearLayout rattingProgressBarContainer;
    private TextView averageRating;


    private FloatingActionButton addToWishListBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;

    /////--------->>>coupon Dialog
    public static TextView couponTitle;
    public static TextView couponExperityDate;
    public static TextView couponBody;
    private static RecyclerView couponRecyclerview;
    private static LinearLayout selectedCoupons;

    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        productImageViewPager = (ViewPager) findViewById(R.id.product_images_viewPager);
        ViewPagerIndicator = (TabLayout) findViewById(R.id.view_pagger_indicator);
        buy_now_btn = (Button) findViewById(R.id.buy_now_btn);
        quopon_redemption_btn = (Button) findViewById(R.id.cuppon_redemption_btn);

        //---> Product Images layout
        product_title = (TextView) findViewById(R.id.product_title);
        averageRatingMiniView = (TextView) findViewById(R.id.total_rating_mini_view);
        totalRatingMiniView = (TextView) findViewById(R.id.tv_product_rating_miniview);
        productPrice = (TextView) findViewById(R.id.product_detail_price);
        cuttedPrice = (TextView) findViewById(R.id.cutted_price);
        COD = (TextView) findViewById(R.id.cod_indicator_textView);
        tv_cod_indicator = (TextView) findViewById(R.id.tv_cod_indicator);
        rewardTitle = (TextView) findViewById(R.id.reward_title);
        rewardBody = (TextView) findViewById(R.id.reward_body);
        productOnlyDescriptionBody = (TextView) findViewById(R.id.product_details_body);
        totalratingsfigure = (TextView) findViewById(R.id.total_ratings_figure);

        totalRatings = (TextView) findViewById(R.id.total_rattings);
        rattingNoContainer = (LinearLayout) findViewById(R.id.ratings_numbers_containers);
        rattingProgressBarContainer = (LinearLayout) findViewById(R.id.rating_progress_container);
        averageRating = (TextView) findViewById(R.id.average_rating);

        productDetailsViewPager = (ViewPager) findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = (TabLayout) findViewById(R.id.product_details_tab_layout);

        productDetailsTabsContainer = findViewById(R.id.product_details_tabs_container);
        productDetailsOnlyContainer=findViewById(R.id.product_details_container);


        //---------------->>>>>>>>>>retrive images & product details from database
        List<String> productImage = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("PRODUCTS").document("an6HlAEYDqV73LbW4B4U")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    //retrive the image from database
                    for (long x =1; x < (long) documentSnapshot.get("no_of_product_images")+1;x++){
                        productImage.add(documentSnapshot.get("product_image_"+x).toString());
                    }
                    Product_Images_Adapter productImagesAdapter = new Product_Images_Adapter(productImage);
                    productImageViewPager.setAdapter(productImagesAdapter);

                    product_title.setText(documentSnapshot.get("product_title").toString());
                    averageRatingMiniView.setText(documentSnapshot.get("average_rating").toString());
                    totalRatingMiniView.setText("("+documentSnapshot.get("total_ratings")+")rating");
                    productPrice.setText("Bdt. "+documentSnapshot.get("product_price")+" /-");
                    cuttedPrice.setText("Bdt. "+documentSnapshot.get("cutted_price")+" /-");

                    if((boolean)documentSnapshot.get("COD")){
                        COD.setVisibility(View.VISIBLE);
                        tv_cod_indicator.setVisibility(View.VISIBLE);
                    }else {
                        COD.setVisibility(View.INVISIBLE);
                        tv_cod_indicator.setVisibility(View.INVISIBLE);
                    }
                    rewardTitle.setText((long) documentSnapshot.get("free_coupons") + documentSnapshot.get("free_coupon_title").toString());
                    rewardBody.setText(documentSnapshot.get("free_coupon_body").toString());

                    if((boolean)documentSnapshot.get("use_tab_layout")){
                        productDetailsTabsContainer.setVisibility(View.VISIBLE);
                        productDetailsOnlyContainer.setVisibility(View.GONE);
                        productDescription = documentSnapshot.get("product_description").toString();
                        //--->ProductSpecificationFragment

                        productOtherDescription = documentSnapshot.get("product_other_details").toString();
                        //for no of  title check korbe
                        for(long x=1; x < (long) documentSnapshot.get("total_spec_titles")+1;x++){
                            //first title access korbe
                            specificationItemModelList.add(new ProducSpecificationItemModel(0,documentSnapshot.get("spec_title_"+x).toString()));
                            //title r jonno joto gula field ase segula access korbe
                            for(long y=1; y < (long) documentSnapshot.get("spec_title_"+x+"_total_fields")+1; y++){
                                specificationItemModelList.add(new ProducSpecificationItemModel(1,documentSnapshot.get("spec_title_"+x+"_field_"+y+"_name").toString(),documentSnapshot.get("spec_title_"+x+"_field_"+y+"_value").toString()));

                            }
                        }

                    }else {
                        productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());
                        productDetailsTabsContainer.setVisibility(View.INVISIBLE);
                        productDetailsOnlyContainer.setVisibility(View.VISIBLE);
                    }
                    //-------> Rating layout
                    totalRatings.setText((long)documentSnapshot.get("total_ratings")+" rattings");

                    for(int x = 0 ; x <5 ;x++){
                        TextView ratting = (TextView) rattingNoContainer.getChildAt(x);
                        ratting.setText(String.valueOf((long) documentSnapshot.get((5-x)+"_star")));

                        //--->set max Progress in ProgressBar
                        ProgressBar progressBar = (ProgressBar) rattingProgressBarContainer.getChildAt(x);
                        int maxProgress =Integer.parseInt(String.valueOf((long)documentSnapshot.get("total_ratings")));
                        progressBar.setMax(maxProgress);
                        //k koto tuku progress contain korche
                        progressBar.setProgress(Integer.parseInt(String.valueOf((long) documentSnapshot.get((5-x)+"_star"))));
                    }
                    totalratingsfigure.setText(String.valueOf((long)documentSnapshot.get("total_ratings")));
                    averageRating.setText(documentSnapshot.get("average_rating").toString());
                    productDetailsViewPager.setAdapter(new ProductDetailsViewPaggerAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount(),productDescription,productOtherDescription,specificationItemModelList));

                }else {
                    String error = task.getException().getMessage();
                    Toast.makeText(ProductDetailsActivity.this, "The error is: "+error, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ////////----------->>>>>>>>>coupons Dialog
        Dialog couponDialog = new Dialog(ProductDetailsActivity.this);
        couponDialog.setContentView(R.layout.copun_redem_dialog);
        couponDialog.setCancelable(true);
        couponDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerview = couponDialog.findViewById(R.id.toggle_recycle);
        couponRecyclerview = couponDialog.findViewById(R.id.coupons_recyclerview);
        selectedCoupons = couponDialog.findViewById(R.id.selected_coupon);
        couponTitle = couponDialog.findViewById(R.id.couponTitle_myreward);
        couponExperityDate = couponDialog.findViewById(R.id.coupon_validity_myreward);
        couponBody = couponDialog.findViewById(R.id.coupon_body_myreward);

        TextView originalPrice = couponDialog.findViewById(R.id.original_price);
        TextView discountedPrice = couponDialog.findViewById(R.id.discounted_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        couponRecyclerview.setLayoutManager(layoutManager);

        List<MyRewardModel> myRewardModelList = new ArrayList<>();
        myRewardModelList.add(new MyRewardModel("Discount for Sweetie", "20th April,2021", "Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));
        myRewardModelList.add(new MyRewardModel("Cash back", "21th April,2021", "Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));
        myRewardModelList.add(new MyRewardModel("Buy 2 Get 1 Free", "22th April,2021", "Get 20% off on any product above Bdt. 1000/- and bellow 3000/-"));

        MyRewardAdapter adapter = new MyRewardAdapter(myRewardModelList, true); //false coming from MyRewardFragment theke
        couponRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        toggleRecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogRecyclerView();
            }
        });
        ////////////////////--->Coupons Dialog
        quopon_redemption_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                couponDialog.show();
            }
        });

        buy_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
                startActivity(intent);
            }
        });



        //add the tab layout with the viewPager
        ViewPagerIndicator.setupWithViewPager(productImageViewPager, true);

        /////////////--------->>>>>>>>FLOATING ACTION BUTTON
        addToWishListBtn = (FloatingActionButton) findViewById(R.id.add_to_wishlist);
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.tStore_red));
                }
            }
        });

        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ////////////////------------>>>>>>>>Personal Rating Layout
        ratingContainer = (LinearLayout) findViewById(R.id.rate_now_container);

        for (int x = 0; x < ratingContainer.getChildCount(); x++) {
            final int starPosition = x;
            ratingContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startRating(starPosition);
                }
            });
        }
    }

    public static void showDialogRecyclerView() {
        if (couponRecyclerview.getVisibility() == View.GONE) {
            couponRecyclerview.setVisibility(View.VISIBLE);
            selectedCoupons.setVisibility(View.GONE); //at a time ekta layout niye kj krbo - tai ei plan korchi
        } else {
            couponRecyclerview.setVisibility(View.GONE);
            selectedCoupons.setVisibility(View.VISIBLE);
        }
    }

    ////////////////------------>>>>>>>>Personal Rating Layout
    private void startRating(int starPosition) {
        //at first for loop sob star r color grey kore dibe
        //then if statement satify hole j koita star e press kora hbe segula r clr cng hbe
        for (int x = 0; x < ratingContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) ratingContainer.getChildAt(x); //joi starPosition eta dei tahole ekta imageview show korbe;sob koita star tw cole asche x r moddhe oi jnne x pass korbo
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

            if (x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#28E4F4")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.main_search:
                break;

            case R.id.main_cart:
                Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
                showCart = true;
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}