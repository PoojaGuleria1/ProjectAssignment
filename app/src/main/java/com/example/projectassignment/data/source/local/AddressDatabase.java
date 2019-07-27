package com.example.projectassignment.data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectassignment.data.models.DeliveryAddress;

@Database(entities = {DeliveryAddress.class},
        version = 1, exportSchema = false)
public abstract class AddressDatabase extends RoomDatabase {
    private static AddressDatabase INSTANCE = null;
    private static final Object lock = new Object();

    public static AddressDatabase getInstance(Context context) {
        synchronized (lock) {
            if (null == INSTANCE) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AddressDatabase
                        .class, "movie.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            }
            return INSTANCE;
        }
    }

    public abstract AddressDao moviesDao();
}
