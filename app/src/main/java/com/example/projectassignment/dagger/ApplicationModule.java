package com.example.projectassignment.dagger;

import android.content.Context;

import com.example.projectassignment.App;
import com.example.projectassignment.addresslist.AddressDataSource;
import com.example.projectassignment.addresslist.AddressRepo;
import com.example.projectassignment.data.source.local.AddressDatabase;

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




    @Provides
    @Singleton
    public AddressDataSource addressDataSource() {
        AddressDatabase addressDatabase = AddressDatabase.getInstance(mApp);
        return new AddressDataSource(mApp, addressDatabase);

    }


}
