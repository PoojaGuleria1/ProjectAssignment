package com.example.projectassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectassignment.R;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.databinding.ItemRowLayoutBinding;
import com.example.projectassignment.interfaces.OnItemClickListener;

public class AddressListAdapter extends PagedListAdapter<DeliveryAddress, AddressListAdapter.ViewHolder> {

    private OnItemClickListener itemClickListener;

    private static DiffUtil.ItemCallback<DeliveryAddress> DIFF_UTIL = new DiffUtil.ItemCallback<DeliveryAddress>() {
        @Override
        public boolean areItemsTheSame(@NonNull DeliveryAddress oldItem, @NonNull DeliveryAddress newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull DeliveryAddress oldItem, @NonNull DeliveryAddress newItem) {
            return oldItem.equals(newItem);
        }
    };

    private Context context;

    public AddressListAdapter(Context context, OnItemClickListener itemClickListener) {
        super(DIFF_UTIL);
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowLayoutBinding itemRowLayoutBinding = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()), R.layout.item_row_layout, parent, false);
        return new ViewHolder(itemRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        DeliveryAddress deliveryAddress = getItem(position);
        if (null != deliveryAddress) {
            holder.itemRowLayoutBinding.addressText.setText(deliveryAddress.getDescription() + " at " + "\n" +
                    deliveryAddress.getLocation().getAddress());
            Glide.with(context)
                    .load(deliveryAddress.getImageUrl())
                    .into(holder.itemRowLayoutBinding.image);
        }

        holder.itemRowLayoutBinding.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(position);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRowLayoutBinding itemRowLayoutBinding;

        public ViewHolder(ItemRowLayoutBinding itemView) {
            super(itemView.getRoot());
            this.itemRowLayoutBinding = itemView;
        }
    }
}
