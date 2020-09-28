package com.aripratom.aplikasimanajemenaset;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.aripratom.aplikasimanajemenaset.db.AppDatabase;
import com.aripratom.aplikasimanajemenaset.model.Barang;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity {
    private EditText txtNama, txtTanggal, txtJumlah, txtKode, txtKategori;
    private Spinner spLokasi;
    private Button btnSave, btnEdit;
    private int mYear, mMonth, mDay;
    private String tanggalDb;
    private Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setTitle("Form Tambah/Ubah Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNama = findViewById(R.id.txt_nama);
        txtTanggal = findViewById(R.id.txt_tanggal);
        txtJumlah = findViewById(R.id.txt_jumlah);
        txtKode = findViewById(R.id.txt_kode);
        txtKategori = findViewById(R.id.txt_kategori);
        spLokasi = findViewById(R.id.spn_lokasi);
        btnSave = findViewById(R.id.btn_save);
        btnEdit = findViewById(R.id.btn_edit);

        String[] lokasi = {"Bandung", "Jakarta", "Surabaya", "Semarang", "Jogjakarta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, lokasi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLokasi.setAdapter(adapter);

        Intent intent = getIntent();
        barang = (Barang) intent.getSerializableExtra("barang");

        if (barang != null) {
            txtNama.setText(barang.namaBarang);
            txtTanggal.setText(barang.tanggal);
            txtJumlah.setText(barang.jumlah);
            txtTanggal.setText(barang.tanggal);
            txtKode.setText(barang.kodeBarang);
            txtKategori.setText(barang.kategori);
            for (int i = 0; i < lokasi.length; i++) {
                String s = lokasi[i];
                if (s.equalsIgnoreCase(barang.lokasi)) {
                    spLokasi.setSelection(i);
                }
            }
        }


        txtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                txtTanggal.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                tanggalDb = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = txtNama.getText().toString();
                String jumlah = txtJumlah.getText().toString();
                String kode = txtKode.getText().toString();
                String tanggal = tanggalDb;
                String selectedLokasi = (String) spLokasi.getSelectedItem();
                String kategori = txtKategori.getText().toString();

                if (TextUtils.isEmpty(nama)) {
                    txtNama.setError("Nama Tidak Boleh Kosong");
                    return;
                }

                if (TextUtils.isEmpty(jumlah)) {
                    txtJumlah.setError("Jumlah Tidak Boleh Kosong");
                    return;
                }

                if (TextUtils.isEmpty(kode)) {
                    txtKode.setError("Kode Barang Tidak Boleh Kosong");
                    return;
                }
                if (TextUtils.isEmpty(kategori)) {
                    txtKategori.setError("Kategori Tidak Boleh Kosong");
                    return;
                }

                if (barang == null) {
                    barang = new Barang();
                    barang.namaBarang = nama;
                    barang.tanggal = tanggal;
                    barang.kodeBarang = kode;
                    barang.kategori = kategori;
                    barang.lokasi = selectedLokasi;
                    barang.jumlah = jumlah;

                    AppDatabase.getInstance(FormActivity.this)
                            .barangDao().insert(barang);
                    Toast.makeText(FormActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                } else {
                    barang.namaBarang = txtNama.getText().toString();
                    barang.tanggal = txtTanggal.getText().toString();
                    barang.kodeBarang = txtKode.getText().toString();
                    barang.kategori = txtKategori.getText().toString();
                    String selectedItem = (String) spLokasi.getSelectedItem();
                    barang.kodeBarang = txtKode.getText().toString();
                    barang.jumlah = txtJumlah.getText().toString();
                    AppDatabase.getInstance(getApplicationContext()).barangDao().update(barang);
                    Toast.makeText(FormActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                }

                Intent intent1 = new Intent(FormActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


    }
}