package com.example.tsellstore.NavigationComponent;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tsellstore.NavigationComponent.Dashbord.DashbordFragment;
import com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery.MyAccountFragment;
import com.example.tsellstore.NavigationComponent.MyCart.MyCartFragment;
import com.example.tsellstore.NavigationComponent.MyRewards.MyRewardsFragment;
import com.example.tsellstore.NavigationComponent.MyWishList.MyWishListFragment;
import com.example.tsellstore.NavigationComponent.Myorders.MyOrdersFragment;
import com.example.tsellstore.R;
import com.example.tsellstore.SigninSignUp.RegisterActivity;
import com.example.tsellstore.SigninSignUp.SignUpFragment;
import com.example.tsellstore.SigninSignUp.SigninFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.tsellstore.SigninSignUp.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int MY_ORDER_FRAGMENT = 2;
    private static final int MY_WISHLIST_FRAGMENT = 3;
    private static final int MY_Reward_FRAGMENT = 4;
    private static final int MY_ACCOUNT_FRAGMENT = 5;
    public static  Boolean showCart = false;


    private int currentFragment = -1; //static keyword sorai niyechi cz ekta Activity k dui jaiga e use krbo
    NavigationView navigationView;
    private FrameLayout frameLayout;
    private TextView actionBar_logo;
    private Toolbar toolbar;
    private Dialog signin_dialog;

    //for changing the status bar color
    private Window window;
    public static DrawerLayout drawer;

    //check the user status ----->>
    private FirebaseUser CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar_logo = (TextView) findViewById(R.id.actionBarLogo);

        //My Rewards fragment e status bar r color change korbo tai window call kora
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //default back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout = (FrameLayout) findViewById(R.id.main_framLayout);

        if (showCart) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new MyCartFragment(), -2);
        } else {
            //hamburger icon
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    drawer,
                    toolbar,
                    R.string.openNavBar,
                    R.string.closeNavBar
            );
            drawer.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
            setFragment(new DashbordFragment(), HOME_FRAGMENT);
        }

        signin_dialog = new Dialog(MainActivity.this);
        signin_dialog.setContentView(R.layout.sign_in_dialog);
        signin_dialog.setCancelable(true);
        signin_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button sign_in = signin_dialog.findViewById(R.id.signin_btn_quantitydialog);
        Button sign_up = signin_dialog.findViewById(R.id.signup_btn_quantitydialog);

        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninFragment.closeDisableBtn = true;
                SignUpFragment.closeDisableBtn = true;
                signin_dialog.dismiss();
                setSignUpFragment = false; //RegisterActivity te static method declear krchi
                startActivity(registerIntent);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninFragment.closeDisableBtn = true;
                SignUpFragment.closeDisableBtn = true;
                signin_dialog.dismiss();
                setSignUpFragment = true;
                startActivity(registerIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //check the user status - > user null ase ki na seta check krbo ->
        //user r status r maddhome signout btn enable disable kore diyechi

        CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(CurrentUser == null){
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        }else {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT) {
                currentFragment = -1;
                super.onBackPressed();
            } else {
                if(showCart){
                    showCart = false;
                    finish();
                }else {
                    actionBar_logo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new DashbordFragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
            }

        }

    }

    //autogenerated when we create a NavigationDrawer Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }


    //autogenerated when we create a NavigationDrawer Activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.main_search){
            return true;
        }else if(id == R.id.main_notification) {
            return true;
        }else if(id == R.id.main_cart) {
            if (CurrentUser == null) {
                signin_dialog.show();
            }else {
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            }
            return true;
        }else if(id == android.R.id.home) {
            if(showCart) {
                showCart = false;
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        actionBar_logo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
    }

    //autogenerated when we create a NavigationDrawer Activity
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (CurrentUser != null) {

            int id = item.getItemId();

            if (id == R.id.nav_dashbord) {
                actionBar_logo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new DashbordFragment(), HOME_FRAGMENT);

            }
            else if(id == R.id.nav_myorders) {
                gotoFragment("My Order", new MyOrdersFragment(), MY_ORDER_FRAGMENT);
            }
            else if(id == R.id.nav_rewards) {
                gotoFragment("My Reward", new MyRewardsFragment(), MY_Reward_FRAGMENT);
            }
            else if(id == R.id.nav_cart) {
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                //setFragment(new MyCartFragment(),CART_FRAGMENT);
            }
            else if(id == R.id.nav_wishlist) {
                gotoFragment("My Wishlist", new MyWishListFragment(), MY_WISHLIST_FRAGMENT);
            }
            else if(id == R.id.nav_account) {
                gotoFragment("My Account", new MyAccountFragment(), MY_ACCOUNT_FRAGMENT);
            }
            else if(id == R.id.nav_signout){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            drawer.closeDrawer(GravityCompat.START);
            signin_dialog.dismiss();
            return false;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {
            if (fragmentNo == MY_Reward_FRAGMENT) {
                window.setStatusBarColor(getResources().getColor(R.color.tStore_ochan_lightBlue));
                toolbar.setBackgroundColor(getResources().getColor(R.color.tStore_ochan_lightBlue));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.tStore_ochan_lightBlue));
                toolbar.setBackgroundColor(getResources().getColor(R.color.tStore_ochan_lightBlue));
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }
}