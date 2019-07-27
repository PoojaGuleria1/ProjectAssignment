package com.example.projectassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.projectassignment.App;
import com.example.projectassignment.R;
import com.example.projectassignment.addresslist.AddressViewModel;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.data.models.NetworkState;
import com.example.projectassignment.databinding.ItemRowLayoutBinding;
import com.example.projectassignment.interfaces.OnItemClickListener;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> {

    private OnItemClickListener itemClickListener;
    private List<DeliveryAddress> deliveryAddressList;
    private Context context;

    public AddressListAdapter(Context context,List<DeliveryAddress> deliveryAddressList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.deliveryAddressList = deliveryAddressList;
    }

    @NonNull
    @Override
    public AddressListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowLayoutBinding itemRowLayoutBinding = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()), R.layout.item_row_layout, parent, false);
        return new AddressListViewHolder(itemRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListViewHolder holder, int position) {
        DeliveryAddress deliveryAddress = deliveryAddressList.get(position);
        AddressItemViewModel dataItemViewModel = new AddressItemViewModel();
        dataItemViewModel.setDataModel(deliveryAddress);
        holder.setViewModel(dataItemViewModel);
        holder.getBinding().executePendingBindings();

        holder.binding.addressText.setText(deliveryAddress.getDescription() + "at" +
                deliveryAddress.getLocation().getAddress());
        Glide.with(context)
                .load(deliveryAddress.getImageUrl())
                .into(holder.binding.image);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Holder class for Recyler View Adapter
    static class AddressListViewHolder extends RecyclerView.ViewHolder {
        private ItemRowLayoutBinding binding;

        AddressListViewHolder(ItemRowLayoutBinding itemRowLayoutBinding) {
            super(itemRowLayoutBinding.getRoot());
        }

        public ViewDataBinding getBinding() {
            return binding;

        }

        void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        /**
         * Holder Class method to set up the view model object for Inflated childs
         *
         * @param viewModel : Movie Item View Model for each row in the recyler view
         */
        void setViewModel(AddressItemViewModel viewModel) {
            if (binding != null) {
                binding.setModel(viewModel);
            }
        }

    }
}
