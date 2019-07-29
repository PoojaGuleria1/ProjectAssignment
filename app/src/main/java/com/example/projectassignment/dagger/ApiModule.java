package com.example.projectassignment.dagger;


import com.example.projectassignment.App;
import com.example.projectassignment.data.source.remote.RemoteServices;
import com.example.projectassignment.data.source.remote.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    private App app;

    public ApiModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public RemoteServices remoteServices() {
        return RetrofitClient.getInstance().getApi();
    }
}
