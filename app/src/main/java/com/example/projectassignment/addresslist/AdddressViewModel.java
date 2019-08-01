package com.example.projectassignment.addresslist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.projectassignment.data.models.DeliveryAddress;

public class AdddressViewModel extends ViewModel {

    public LiveData<PagedList<DeliveryAddress>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, DeliveryAddress>> itemPageDataSource;
    private ItemDataSourceFactory itemDataSourceFactory;

    public AdddressViewModel() {

        itemDataSourceFactory = new ItemDataSourceFactory();
        itemPageDataSource = itemDataSourceFactory.getAddressLiveDataSource();
    }

    public void onScreenLoaded() {
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(AddressDataSource.PAGE_SIZE)
                        .build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }

    public void refresh() {
        itemDataSourceFactory.addressLiveDataSource.getValue().invalidate();
    }
}

