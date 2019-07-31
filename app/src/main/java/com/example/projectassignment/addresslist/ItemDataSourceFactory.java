package com.example.projectassignment.addresslist;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.projectassignment.addresslist.AddressDataSource;
import com.example.projectassignment.data.models.DeliveryAddress;

import javax.inject.Inject;

public class ItemDataSourceFactory extends DataSource.Factory {


    public MutableLiveData<PageKeyedDataSource<Integer, DeliveryAddress>> addressLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        AddressDataSource addressDataSource = new AddressDataSource();
        addressLiveDataSource.postValue(addressDataSource);
        return addressDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, DeliveryAddress>> getAddressLiveDataSource() {
        return addressLiveDataSource;
    }
}
