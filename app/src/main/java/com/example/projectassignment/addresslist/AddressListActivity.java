package com.example.projectassignment.addresslist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectassignment.R;
import com.example.projectassignment.addressDetail.AddressDetailActivity;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.databinding.ActivityItemBinding;
import com.example.projectassignment.interfaces.OnItemClickListener;
import com.example.projectassignment.adapter.AddressListAdapter;

import java.util.List;

public class AddressListActivity extends AppCompatActivity implements OnItemClickListener {

    private ActivityItemBinding activityItemBinding;
    private List<DeliveryAddress> deliveryAddressList;
    private AdddressViewModel itemViewModel;
    private AddressListAdapter addressListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        setViewBindings();
        setUpRecyclerView();
        activityItemBinding.addressSwipetorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemViewModel.refresh();
            }
        });

    }

    private void setUpRecyclerView() {
        activityItemBinding.addressSwipetorefresh.setRefreshing(true);
        activityItemBinding.dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressListAdapter = new AddressListAdapter(this, this);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<DeliveryAddress>>() {
            @Override
            public void onChanged(final PagedList<DeliveryAddress> deliveryAddresses) {
                deliveryAddressList = deliveryAddresses;
                addressListAdapter.submitList(deliveryAddresses);
                if (activityItemBinding.addressSwipetorefresh.isRefreshing() && null != deliveryAddresses)
                   activityItemBinding.addressSwipetorefresh.setRefreshing(false);

        }
        });
        activityItemBinding.dataRecyclerView.setAdapter(addressListAdapter);

    }

    private void setViewBindings() {
        activityItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_item);
        itemViewModel = ViewModelProviders.of(this).get(AdddressViewModel.class);
        activityItemBinding.setLifecycleOwner(this);
        activityItemBinding.executePendingBindings();

       // activityItemBinding.header.backButton.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, AddressDetailActivity.class);
        intent.putExtra("deliveryAddress", deliveryAddressList.get(position));
        startActivity(intent);
    }


}
