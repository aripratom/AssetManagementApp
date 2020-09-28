package com.aripratom.aplikasimanajemenaset.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.aripratom.aplikasimanajemenaset.model.Barang;

@Database(entities = {Barang.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BarangDao barangDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    AppDatabase() {
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class,
                "barang.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
