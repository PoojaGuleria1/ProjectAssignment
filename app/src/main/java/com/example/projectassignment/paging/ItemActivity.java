package com.example.projectassignment.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.projectassignment.R;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.databinding.ActivityItemBinding;

public class ItemActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ActivityItemBinding activityItemBinding = DataBindingUtil.setContentView(this,R.layout.activity_item);
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        activityItemBinding.setLifecycleOwner(this);
        activityItemBinding.executePendingBindings();

         activityItemBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
         activityItemBinding.recyclerView.setHasFixedSize(true);

         final ItemAdapter itemAdapter = new ItemAdapter(this);

         itemViewModel.itemPagedList.observe(this, new Observer<PagedList<DeliveryAddress>>() {
             @Override
             public void onChanged(PagedList<DeliveryAddress> deliveryAddresses) {
                 itemAdapter.submitList(deliveryAddresses);
             }
         });
        activityItemBinding.recyclerView.setAdapter(itemAdapter);
    }
}
