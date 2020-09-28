package com.aripratom.aplikasimanajemenaset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.aripratom.aplikasimanajemenaset.model.Barang;

public class DetailActivity extends AppCompatActivity {
    private TextView txtNama, txtTanggal, txtJumlah, txtKode, txtKategori, txtLokasi;
    private Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Detail Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        barang = (Barang) intent.getSerializableExtra("barang");

        txtNama = findViewById(R.id.txt_nama_detail);
        txtTanggal = findViewById(R.id.txt_tanggal_detail);
        txtJumlah = findViewById(R.id.txt_jumlah_detail);
        txtKode = findViewById(R.id.txt_kode_detail);
        txtKategori = findViewById(R.id.txt_kategori_detail);
        txtLokasi = findViewById(R.id.txt_lokasi_detail);
        txtNama.setText(barang.namaBarang);
        txtTanggal.setText(barang.tanggal);
        txtJumlah.setText(barang.jumlah);
        txtTanggal.setText(barang.tanggal);
        txtKode.setText(barang.kodeBarang);
        txtKategori.setText(barang.kategori);
        txtLokasi.setText(barang.lokasi);

    }
}