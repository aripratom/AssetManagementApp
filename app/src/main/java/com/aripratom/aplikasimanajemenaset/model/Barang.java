package com.aripratom.aplikasimanajemenaset.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Barang implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;
    public String tanggal;
    public String kategori;
    public String namaBarang;
    public String kodeBarang;
    public String lokasi;
    public String jumlah;
}
