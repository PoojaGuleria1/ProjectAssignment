package com.example.projectassignment.addresslist;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.projectassignment.addresslist.AddressDataSource;
import com.example.projectassignment.data.models.DeliveryAddress;

import javax.inject.Inject;

public class ItemDataSourceFactory extends DataSource.Factory {

    @Inject
    AddressDataSource addressDataSource;

    public MutableLiveData<PageKeyedDataSource<Integer, DeliveryAddress>> addressLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        addressLiveDataSource.postValue(addressDataSource);
        return addressDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, DeliveryAddress>> getAddressLiveDataSource() {
        return addressLiveDataSource;
    }
}
