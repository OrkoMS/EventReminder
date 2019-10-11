package com.roar71.orko.eventreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Boolean loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent activityIntent;

        SharedPreferences sharedPref = getSharedPreferences("userLogout", Context.MODE_PRIVATE);
        loggedInUser= sharedPref.getBoolean("loggedInUser",false);

        if(loggedInUser)
        {
            activityIntent = new Intent(MainActivity.this, HomeActivity.class);
        }
        else {
            activityIntent = new Intent(MainActivity.this, SplashActivity.class);
        }
        startActivity(activityIntent);
        finish();
    }
}
