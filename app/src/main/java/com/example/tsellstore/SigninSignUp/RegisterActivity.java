package com.example.tsellstore.SigninSignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.example.tsellstore.R;
import com.example.tsellstore.SigninSignUp.signinFragment;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout framlayout;

    public static boolean onForgetPasswordFragment = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        framlayout = (FrameLayout) findViewById(R.id.register_frame_layout);

        setDefaultFragment(new signinFragment());


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(onForgetPasswordFragment){
                onForgetPasswordFragment = false;
                setFragment(new signinFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(framlayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(framlayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}