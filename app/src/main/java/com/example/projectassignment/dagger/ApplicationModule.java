package com.example.projectassignment.dagger;

import android.content.Context;

import com.example.projectassignment.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private static final String TAG = ApplicationModule.class.getSimpleName();
    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }





}
