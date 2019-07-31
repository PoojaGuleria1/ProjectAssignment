package com.example.projectassignment.addresslist;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.projectassignment.App;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.data.source.local.AddressDao;
import com.example.projectassignment.data.source.local.AddressDatabase;
import com.example.projectassignment.data.source.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressDataSource extends PageKeyedDataSource<Integer, DeliveryAddress> {


    public static final int PAGE_SIZE = 7;
    public static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, DeliveryAddress> callback) {
        RetrofitClient.getInstance().
                getApi().getDeliveryAddress(FIRST_PAGE,PAGE_SIZE).enqueue(new Callback<List<DeliveryAddress>>() {
            @Override
            public void onResponse(Call<List<DeliveryAddress>> call, Response<List<DeliveryAddress>> response) {
                if (null != response.body()) {
                     callback.onResult(response.body(), null, FIRST_PAGE + 1);
                }

            }

            @Override
            public void onFailure(Call<List<DeliveryAddress>> call, Throwable t) {
                Log.d("error","njfdsjds");
            }


        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, DeliveryAddress> callback) {
        RetrofitClient.getInstance().
                getApi().getDeliveryAddress(params.key,PAGE_SIZE).enqueue(new Callback<List<DeliveryAddress>>() {
            @Override
            public void onResponse(Call<List<DeliveryAddress>> call, Response<List<DeliveryAddress>> response) {

                Integer key = (params.key > 1) ? params.key - 1 : null;
                if (null != response.body()) {
                    callback.onResult(response.body(), key);
                }
            }

            @Override
            public void onFailure(Call<List<DeliveryAddress>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, DeliveryAddress> callback) {
        RetrofitClient.getInstance().
                getApi().getDeliveryAddress(params.key,PAGE_SIZE).enqueue(new Callback<List<DeliveryAddress>>() {
            @Override
            public void onResponse(Call<List<DeliveryAddress>> call, Response<List<DeliveryAddress>> response) {
                Integer key;
                if(response.code() == 200 && response.body() != null){
                    key = params.key + 1;
                }else{
                    key = null;
                }

                callback.onResult(response.body(),key);

            }
            @Override
            public void onFailure(Call<List<DeliveryAddress>> call, Throwable t) {

            }
        });
    }
}
