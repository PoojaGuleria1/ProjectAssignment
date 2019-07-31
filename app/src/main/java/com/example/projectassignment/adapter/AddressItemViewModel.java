package com.example.projectassignment.adapter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.projectassignment.data.models.DeliveryAddress;

/**
 * ViewModel class for AddressList Adapter
 */
public class AddressItemViewModel extends ViewModel {

    private DeliveryAddress deliveryAddress;
    private String description;

    public void setDataModel(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDescription(){
        description = deliveryAddress.getDescription();
    }

}
