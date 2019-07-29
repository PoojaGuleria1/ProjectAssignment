package com.example.projectassignment.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.projectassignment.data.models.DeliveryAddress;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<DeliveryAddress>> itemPagedList = new MutableLiveData<>();
    public LiveData<PageKeyedDataSource<Integer,DeliveryAddress>> itemPageDataSource = new MutableLiveData<>();

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        itemPageDataSource = itemDataSourceFactory.getAddressLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory,config)).build();

    }
}

