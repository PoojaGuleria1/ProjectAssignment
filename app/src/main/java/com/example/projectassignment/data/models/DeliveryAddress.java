package com.example.projectassignment.data.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "deliveryAddress")
public class DeliveryAddress {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    private Integer id;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("imageUrl")
    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @SerializedName("location")
    @Embedded
    private Location location;

    // use for ordering the items in view
    public static DiffUtil.ItemCallback<DeliveryAddress> DIFF_CALLBACK = new DiffUtil.ItemCallback<DeliveryAddress>() {
        @Override
        public boolean areItemsTheSame(@NonNull DeliveryAddress oldItem, @NonNull DeliveryAddress newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DeliveryAddress oldItem, @NonNull DeliveryAddress newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
