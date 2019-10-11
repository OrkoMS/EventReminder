package com.roar71.orko.eventreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    private Boolean newUser;

    private ImageView imageView;
    private LinearLayout splashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.logo);
        splashLayout = findViewById(R.id.splash_layout);

        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_transit);
        imageView.startAnimation(splashAnimation);

        SharedPreferences sharedPref = getSharedPreferences("firstUser", Context.MODE_PRIVATE);
        newUser = sharedPref.getBoolean("newUser",true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (newUser == false) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }

            }
        }, 1500);
    }
}
