package com.example.tsellstore.SigninSignUp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.TransitionManager;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsellstore.R;
import com.example.tsellstore.SigninSignUp.signinFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordFragment extends Fragment {

    private EditText registered_email;
    private Button reset_btn;
    private ImageView back_arrow;
    private FirebaseAuth firebaseAuth;

    private ViewGroup liniarLayout;
    private ImageView emailIcon;
    private TextView email_icon_text;
    private ProgressBar progress;

    private FrameLayout parentLayout;
    public ForgetPasswordFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_forget_password, container, false);
        registered_email = (EditText) view.findViewById(R.id.forgot_password_email) ;
        reset_btn = (Button) view.findViewById(R.id.forgot_password_btn);
        back_arrow = (ImageView) view.findViewById(R.id.imgv_back);
        liniarLayout = (LinearLayout) view.findViewById(R.id.linearLayout_for_emailPass);
        emailIcon = (ImageView) view.findViewById(R.id.image_icon);
        email_icon_text = (TextView) view.findViewById(R.id.image_text);
        progress = (ProgressBar) view.findViewById(R.id.image_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        parentLayout = getActivity().findViewById(R.id.register_frame_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(liniarLayout);
                email_icon_text.setVisibility(View.GONE);

                TransitionManager.beginDelayedTransition(liniarLayout);
                emailIcon.setVisibility(View.VISIBLE);
                progress.setVisibility(View.VISIBLE);


                reset_btn.setEnabled(false);
                reset_btn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.sendPasswordResetEmail(registered_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,emailIcon.getWidth()/2,emailIcon.getHeight()/2);
                                    scaleAnimation.setDuration(100);
                                    scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                                    scaleAnimation.setRepeatCount(1);

                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            email_icon_text.setText("Recovery email sent successfully ! check your inbox");
                                            email_icon_text.setTextColor(getResources().getColor(R.color.colorPrimary));

                                            TransitionManager.beginDelayedTransition(liniarLayout);
                                            emailIcon.setVisibility(View.INVISIBLE);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                            emailIcon.setImageResource(R.drawable.ic_mail);
                                        }
                                    });


                                    emailIcon.startAnimation(scaleAnimation);

                                    Toast.makeText(getActivity(), "veification code gone..!", Toast.LENGTH_SHORT).show();
                                }else {
                                    String error = task.getException().getMessage();
                                    //Toast.makeText(getActivity(), "Exception: "+error, Toast.LENGTH_SHORT).show();

                                    reset_btn.setEnabled(true);
                                    reset_btn.setTextColor(Color.rgb(255,255,255));

                                    email_icon_text.setText(error);
                                    email_icon_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    TransitionManager.beginDelayedTransition(liniarLayout);
                                    email_icon_text.setVisibility(View.VISIBLE);

                                }
                                //button enable code here cause there is fragments sifted from here...
                                progress.setVisibility(View.GONE);

                            }
                        });
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new signinFragment());
            }
        });

        registered_email.addTextChangedListener(new TextWatcher() {
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
    }

    private void checkInputs() {

        if(TextUtils.isEmpty(registered_email.getText().toString())){
            reset_btn.setEnabled(false);
            reset_btn.setTextColor(Color.argb(50,255,255,255));
        }else {
            reset_btn.setEnabled(true);
            reset_btn.setTextColor(Color.rgb(255,255,255));
        }

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}