package com.example.tsellstore.SigninSignUp;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tsellstore.NavigationComponent.MainActivity;
import com.example.tsellstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SigninFragment extends Fragment {


    private Button RegistrationButton;
    private FrameLayout ParentframeLayout;

    private EditText email_login,password_login;
    private Button login_btn,forget_password_btn;
    private ImageView close_btn;
    private  String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    public static boolean closeDisableBtn = false;
    public SigninFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(ParentframeLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signin, container, false);
        RegistrationButton =(Button) view.findViewById(R.id.btnRegLogin);
        ParentframeLayout = getActivity().findViewById(R.id.register_frame_layout);


        email_login = (EditText) view.findViewById(R.id.login_email);
        password_login = (EditText) view.findViewById(R.id.login_password);
        login_btn = (Button) view.findViewById(R.id.button);
        progressBar = (ProgressBar) view.findViewById(R.id.login_progress);
        forget_password_btn= (Button) view.findViewById(R.id.forget_password);
        close_btn= (ImageView) view.findViewById(R.id.closeBtn);


        firebaseAuth = FirebaseAuth.getInstance();

        if(closeDisableBtn){
            close_btn.setVisibility(View.GONE);
        }else {
            close_btn.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        forget_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean onForgetPasswordFragment = true;
                setFragment(new ForgetPasswordFragment());
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainIntent();
            }
        });
        email_login.addTextChangedListener(new TextWatcher() {
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

        password_login.addTextChangedListener(new TextWatcher() {
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

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }

    private void checkEmailAndPassword() {
        if(email_login.getText().toString().matches(email_pattern)) { //check the email
            if(password_login.length() >= 8) { //check the password

                progressBar.setVisibility(View.VISIBLE);
                login_btn.setEnabled(false);
                login_btn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.signInWithEmailAndPassword(email_login.getText().toString() , password_login.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                    getActivity().finish();
                                }else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    login_btn.setEnabled(true);
                                    login_btn.setTextColor(Color.rgb(255,255,255));

                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), "Exception:"+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else {
                Toast.makeText(getActivity(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
        }

    }


    private void checkInputs() {
        if(!TextUtils.isEmpty(email_login.getText())){
            if(!TextUtils.isEmpty(password_login.getText()) && password_login.length()>=8){
                login_btn.setEnabled(true);
                login_btn.setTextColor(Color.rgb(255,255,255));

            }else {
                login_btn.setEnabled(false);
                login_btn.setTextColor(Color.argb(50,255,255,255));
            }
        }else {
            login_btn.setEnabled(false);
            login_btn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void mainIntent(){
        if(closeDisableBtn){
            closeDisableBtn = false;
        }else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);
        }
        getActivity().finish();
    }
}