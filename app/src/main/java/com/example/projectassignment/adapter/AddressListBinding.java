package com.example.projectassignment.adapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectassignment.data.models.DeliveryAddress;

import java.util.List;

public class AddressListBinding {

    @BindingAdapter({"app:addressListData"})
    public static void addressListAdapter(RecyclerView recyclerView, List<DeliveryAddress> items) {

    }
}
