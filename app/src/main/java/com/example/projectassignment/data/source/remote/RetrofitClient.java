package com.example.projectassignment.data.source.remote;

import com.example.projectassignment.data.source.remote.ApiConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static RetrofitClient mInstance;

    private RetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
    }

    public static synchronized RetrofitClient getInstance(){
        if(null == mInstance){
            mInstance = new RetrofitClient();

        }
        return mInstance;
    }

    public RemoteServices getApi(){
       return retrofit.create(RemoteServices.class);
    }
}
