package com.example.projectassignment;

import android.app.Application;

import com.example.projectassignment.dagger.ApiModule;
import com.example.projectassignment.dagger.AppComponent;
import com.example.projectassignment.dagger.ApplicationModule;
import com.example.projectassignment.dagger.DaggerAppComponent;

public class App extends Application {

    private AppComponent mAppComponent;
    public static App instance;

    public static App getApp() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(this))
                .build();
       // mAppComponent.inject(this);
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
