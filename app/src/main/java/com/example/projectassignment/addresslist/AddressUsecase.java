package com.example.projectassignment.addresslist;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.projectassignment.App;
import com.example.projectassignment.dagger.AppComponent;
import com.example.projectassignment.data.models.DeliveryAddress;

import java.util.List;

import javax.inject.Inject;

public class AddressUsecase {

    @Inject
    AddressRepo addressRepo;

    public AddressUsecase() {
        App.getApp().getAppComponent().inject(this);
    }

    public MutableLiveData<List<DeliveryAddress>> getAddressList(){
        return addressRepo.getAddressList();

    }
}
