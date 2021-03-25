package com.example.tsellstore.SigninSignUp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tsellstore.NavigationComponent.MainActivity;
import com.example.tsellstore.R;
import com.example.tsellstore.SigninSignUp.signinFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class signUpFragment extends Fragment {
    private Button BacktoLogin;


    private FrameLayout parentframeLayout;
    private ProgressBar progress;

    private EditText user_name,email,password,confirm_password;
    private Button register_btn;
    private  String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    public signUpFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        BacktoLogin = (Button) view.findViewById(R.id.backtoLogin);
        parentframeLayout = (FrameLayout) getActivity().findViewById(R.id.register_frame_layout);

        user_name = (EditText) view.findViewById(R.id.user_name_input);
        email = (EditText) view.findViewById(R.id.login_email_input);
        password = (EditText) view.findViewById(R.id.login_password_input);
        confirm_password = (EditText) view.findViewById(R.id.login_confirm_password_input);
        register_btn = (Button) view.findViewById(R.id.register_btn);
        progress = (ProgressBar) view.findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new signinFragment());
            }
        });
        
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //at first check the pattern of email
                checkEmailAndPassword();
            }
        });
    }


    private void checkEmailAndPassword(){
        if(email.getText().toString().matches(email_pattern)){ //check the email
            if(password.getText().toString().equals(confirm_password.getText().toString())){ //check the password

                //a user press the button again and again - for this problem we disable the button when user press the button one time
                register_btn.setEnabled(false);
                progress.setVisibility(View.VISIBLE);
                register_btn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString()) //if email and password alright ready to udload
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    //upload data to firestore
                                    Map<Object,String> userData = new HashMap<>();
                                    userData.put("user",user_name.getText().toString());

                                    firestore.collection("USER").add(userData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if(task.isSuccessful()){
                                                startActivity(new Intent(getActivity(), MainActivity.class));
                                                getActivity().finish();
                                            }else {
                                                progress.setVisibility(View.INVISIBLE);
                                                //if error comes then enable the button
                                                register_btn.setEnabled(true);
                                                register_btn.setTextColor(Color.argb(255,255,255,255));

                                                String error = task.getException().getMessage();
                                                Toast.makeText(getActivity(), "Exception:"+error, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });



                                }else {
                                    progress.setVisibility(View.INVISIBLE);
                                    //if error comes then enable the button
                                    register_btn.setEnabled(true);
                                    register_btn.setTextColor(Color.argb(255,255,255,255));

                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), "Exception:"+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else {
                confirm_password.setError("Password doesnot match...");
            }
        }else{
            email.setError("Email invalid...");
        }
    }
    @SuppressLint("ResourceAsColor")
    private void checkInputs() {

        if(!TextUtils.isEmpty(email.getText())) {
           if(!TextUtils.isEmpty(user_name.getText())){
               if(!TextUtils.isEmpty(password.getText()) && password.length()>=8){
                   if(!TextUtils.isEmpty(confirm_password.getText())){

                       register_btn.setEnabled(true);
                       register_btn.setTextColor(R.color.black);
                   }else {
                       register_btn.setEnabled(false);
                       register_btn.setTextColor(Color.argb(50,255,255,255));
                   }

               }else{
                   register_btn.setEnabled(false);
                   register_btn.setTextColor(Color.argb(50,255,255,255));
               }
           }else {
               register_btn.setEnabled(false);
               register_btn.setTextColor(Color.argb(50,255,255,255));
           }
        }else {
            register_btn.setEnabled(false);
            register_btn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentframeLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}