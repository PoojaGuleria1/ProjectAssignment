package com.example.projectassignment.addresslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.projectassignment.App;
import com.example.projectassignment.R;
import com.example.projectassignment.adapter.AddressListAdapter;
import com.example.projectassignment.dagger.AppComponent;
import com.example.projectassignment.databinding.ActivityAddressBinding;
import com.example.projectassignment.interfaces.OnItemClickListener;

public class AddressActivity extends AppCompatActivity implements OnItemClickListener {

   private ActivityAddressBinding activityAddressBinding;
   private AddressViewModel addressViewModel;
   private AddressListAdapter addressListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewBindings();
        setUpAddressListAdapter();
    }

    // Method to set Data-binding and View Model for the fragment
    private void setViewBindings() {
        activityAddressBinding = DataBindingUtil.setContentView(this,R.layout.activity_address);
        addressViewModel = ViewModelProviders.of(this).get(AddressViewModel.class);
        activityAddressBinding.setModel(addressViewModel);
        activityAddressBinding.setLifecycleOwner(this);
        activityAddressBinding.executePendingBindings();
    }

    @SuppressLint("WrongConstant")
    private void setUpAddressListAdapter() {
        activityAddressBinding.addressSwipetorefresh.setRefreshing(true);
        addressViewModel.getAddressList();
        RecyclerView addressListRecyclerView = activityAddressBinding.dataRecyclerView;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        addressListRecyclerView.setLayoutManager(mLayoutManager);
        addressListAdapter = new AddressListAdapter(this,addressViewModel.getDeliveryAddressList(),this);
        addressListRecyclerView.setAdapter(addressListAdapter);
        activityAddressBinding.addressSwipetorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addressViewModel.getAddressList();
            }
        });

    }

    @Override
    public void onItemClick() {
      //TODO hanle item click here
    }
}
