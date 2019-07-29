package com.example.projectassignment.data.source.remote;

import com.example.projectassignment.data.models.DeliveryAddress;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteServices {

    @GET(ApiConstants.ENDPOINT)
    Call<List<DeliveryAddress>> getDeliveryAddress(@Query("offset") int offset,@Query("limit") int size);
}
