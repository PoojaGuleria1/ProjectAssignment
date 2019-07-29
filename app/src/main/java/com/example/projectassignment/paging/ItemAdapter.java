package com.example.projectassignment.paging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
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

public class ItemAdapter extends PagedListAdapter<DeliveryAddress, ItemAdapter.ViewHolder> {

    private Context context;

    protected ItemAdapter(Context context) {
        super(DIFF_UTIL);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowLayoutBinding itemRowLayoutBinding = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()), R.layout.item_row_layout, parent, false);
        return new ViewHolder(itemRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeliveryAddress deliveryAddress = getItem(position);
        if (null != deliveryAddress) {
            holder.itemRowLayoutBinding.addressText.setText(deliveryAddress.getDescription() + "at" +
                    deliveryAddress.getLocation().getAddress());
            Glide.with(context)
                    .load(deliveryAddress.getImageUrl())
                    .into(holder.itemRowLayoutBinding.image);
        }
    }

    private static DiffUtil.ItemCallback<DeliveryAddress> DIFF_UTIL = new DiffUtil.ItemCallback<DeliveryAddress>() {
        @Override
        public boolean areItemsTheSame(@NonNull DeliveryAddress oldItem, @NonNull DeliveryAddress newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull DeliveryAddress oldItem, @NonNull DeliveryAddress newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRowLayoutBinding itemRowLayoutBinding;

        public ViewHolder(ItemRowLayoutBinding itemView) {
            super(itemView.getRoot());
            this.itemRowLayoutBinding = itemView;
        }
    }
}
