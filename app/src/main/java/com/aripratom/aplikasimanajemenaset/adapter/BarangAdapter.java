package com.aripratom.aplikasimanajemenaset.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aripratom.aplikasimanajemenaset.DetailActivity;
import com.aripratom.aplikasimanajemenaset.FormActivity;
import com.aripratom.aplikasimanajemenaset.MainActivity;
import com.aripratom.aplikasimanajemenaset.R;
import com.aripratom.aplikasimanajemenaset.db.AppDatabase;
import com.aripratom.aplikasimanajemenaset.model.Barang;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    Context context;
    List<Barang> barangs;

    public BarangAdapter(Context context, List<Barang> barangs) {
        this.context = context;
        this.barangs = barangs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_barang, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (barangs != null) ? barangs.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Barang barang = barangs.get(i);
        viewHolder.txtNama.setText(barang.namaBarang);
        viewHolder.txtKategori.setText(barang.kategori);
        viewHolder.txtKode.setText(barang.kodeBarang);
        viewHolder.txtTanggal.setText(barang.tanggal);
        viewHolder.txtLokasi.setText(barang.lokasi);
        viewHolder.txtJumlah.setText(barang.jumlah);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("barang", barang);
                context.startActivity(intent);
            }
        });

        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                                Intent intent = new Intent(context, FormActivity.class);
                                intent.putExtra("barang", barang);
                                context.startActivity(intent);

            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Konfirmasi");
                builder.setMessage("Hapus data?");
                builder.setPositiveButton("Hapus",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                delete(barang);
                                Toast.makeText(context,"Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama, txtKode, txtTanggal, txtKategori, txtLokasi, txtJumlah;
        CardView cardView;
        Button btnDelete, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtKode = itemView.findViewById(R.id.txt_kode);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtKategori = itemView.findViewById(R.id.txt_kategori);
            txtLokasi = itemView.findViewById(R.id.txt_lokasi);
            txtJumlah = itemView.findViewById(R.id.txt_jumlah);
            cardView = itemView.findViewById(R.id.card);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }
    }

    private void delete(Barang barang) {
        AppDatabase.getInstance(context).barangDao().delete(barang);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}

