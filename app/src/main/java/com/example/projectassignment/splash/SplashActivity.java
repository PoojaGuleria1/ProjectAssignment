package com.example.projectassignment.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.projectassignment.R;
import com.example.projectassignment.addresslist.AddressActivity;
import com.example.projectassignment.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUpViewBindings();
        startHandlerDelay();
        getSupportActionBar().hide();

    }

    //Setting up the View model and Data binding for the view
    private void setUpViewBindings() {
        ActivitySplashBinding activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        activitySplashBinding.executePendingBindings();
    }

    //Method to add delay in order to show static loader of loading here...
    private void startHandlerDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, AddressActivity.class));
                finish();
            }
        }, 3000);
    }
}
