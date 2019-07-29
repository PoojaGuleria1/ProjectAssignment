package com.example.projectassignment.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.projectassignment.data.models.DeliveryAddress;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, DeliveryAddress>> addressLiveDataSource = new MutableLiveData<>();
    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        addressLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, DeliveryAddress>> getAddressLiveDataSource(){
        return addressLiveDataSource;
    }
}
