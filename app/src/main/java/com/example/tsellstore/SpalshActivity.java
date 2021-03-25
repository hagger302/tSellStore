package com.example.tsellstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.tsellstore.NavigationComponent.MainActivity;
import com.example.tsellstore.SigninSignUp.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SpalshActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        firebaseAuth = FirebaseAuth.getInstance();
        SystemClock.sleep(5000);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser Currentuser = firebaseAuth.getCurrentUser();

        if(Currentuser == null){
            startActivity(new Intent(SpalshActivity.this, RegisterActivity.class));
            finish();
        }else{
            startActivity(new Intent(SpalshActivity.this, MainActivity.class));
            finish();
        }
    }
}