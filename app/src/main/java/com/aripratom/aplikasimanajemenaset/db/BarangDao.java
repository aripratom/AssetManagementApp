package com.aripratom.aplikasimanajemenaset.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aripratom.aplikasimanajemenaset.model.Barang;

import java.util.List;

@Dao
public interface BarangDao {
    @Query("SELECT * FROM Barang")
    List<Barang> getAll();

    @Query("SELECT * FROM Barang WHERE id=:id")
    Barang getById(Long id);

    @Insert
    void insert(Barang Barang);

    @Update
    void update(Barang Barang);

    @Delete
    void delete(Barang Barang);

    @Query("SELECT COUNT(*) from Barang")
    Integer count();
}
