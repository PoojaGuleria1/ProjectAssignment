package com.example.projectassignment.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projectassignment.data.models.DeliveryAddress;

import java.util.List;

@Dao
public interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddressData(DeliveryAddress deliveryAddress);

    @Query("SELECT * FROM deliveryAddress  where  description= :description")
    DeliveryAddress getAddressListData(String description);

    @Query("SELECT * FROM deliveryAddress")
    List<DeliveryAddress> getAllAddresses();

    @Query("DELETE FROM deliveryAddress")
    void deleteAllAddress();
}
