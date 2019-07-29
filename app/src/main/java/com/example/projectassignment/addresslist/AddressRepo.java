package com.example.projectassignment.addresslist;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.projectassignment.App;
import com.example.projectassignment.dagger.AppComponent;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.data.source.local.AddressDao;
import com.example.projectassignment.data.source.local.AddressDatabase;
import com.example.projectassignment.data.source.remote.RemoteServices;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddressRepo {

    private MutableLiveData<List<DeliveryAddress>> deliveryAddressMutableLiveData;

    @Inject
    public RemoteServices mRemoteServices;

    private AddressDao addressDao;
    private Context context;

    public AddressRepo(Context context, AddressDatabase addressDatabase) {
        App.getApp().getAppComponent().inject(this);
        addressDao = addressDatabase.moviesDao();
        this.context = context;
    }

    public MutableLiveData<List<DeliveryAddress>> getAddressList() {
        deliveryAddressMutableLiveData = new MutableLiveData<>();
        //List<DeliveryAddress> movies = addressDao.getAllAddresses();
        //deliveryAddressMutableLiveData.setValue(movies);
        Call<List<DeliveryAddress>> call = mRemoteServices.getDeliveryAddress(1,4);
        call.enqueue(new Callback<List<DeliveryAddress>>() {
            @Override
            public void onResponse(Call<List<DeliveryAddress>> call, Response<List<DeliveryAddress>> response) {
                if(response.isSuccessful() && null != response.body()) {
                    deliveryAddressMutableLiveData.setValue(response.body());
                    addressDao.deleteAllAddress();
                    insertMoviesIntoDb(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DeliveryAddress>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                deliveryAddressMutableLiveData.setValue(null);
            }
        });


        return deliveryAddressMutableLiveData;
    }

    /**
     * Method to fetch movie details from DB as per provided movie name
     *
     * @param description : name of the movie whose details needs to be fetched
     * @return : Movie object containing details of the movie
     */
    public DeliveryAddress fetchAddressDetails(String description) {
        return addressDao.getAddressListData(description);
    }

    /**
     * Method to insert the movie response data into the DB
     *
     * @param deliveryAddresses : contains movie response fetched from Server
     */
    private void insertMoviesIntoDb(List<DeliveryAddress> deliveryAddresses) {
        for (DeliveryAddress address : deliveryAddresses)
            addressDao.insertAddressData(address);
    }
}

