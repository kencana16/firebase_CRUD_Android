package com.kencana.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDBDetail extends AppCompatActivity {

    private TextView tv_kode, tv_nama, tv_satuan, tv_hargabeli, tv_hargajual, tv_stok, tv_stokmin, tv_deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbdetail);

        tv_kode = findViewById(R.id.tv_kodebarang_detail);
        tv_nama = findViewById(R.id.tv_namabarang_detail);
        tv_satuan = findViewById(R.id.tv_satuan_detail);
        tv_hargabeli = findViewById(R.id.tv_hargabeli_detail);
        tv_hargajual = findViewById(R.id.tv_hargajual_detail);
        tv_stok = findViewById(R.id.tv_stok_detail);
        tv_stokmin = findViewById(R.id.tv_stokmin_detail);
        tv_deskripsi = findViewById(R.id.tv_deskripsi_detail);

        // mengambil data dari intent
        final Barang barang = (Barang) getIntent().getSerializableExtra("data");

        tv_kode.setText(barang.getKd_barang());
        tv_nama.setText(barang.getNm_barang());
        tv_satuan.setText(barang.getSatuan());
        tv_hargabeli.setText(barang.getHarga_beli());
        tv_hargajual.setText(barang.getHarga_jual());
        tv_stok.setText(barang.getStok());
        tv_stokmin.setText(barang.getStok_min());
        tv_deskripsi.setText(barang.getDeskripsi());
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, FirebaseDBDetail.class);
    }
}
