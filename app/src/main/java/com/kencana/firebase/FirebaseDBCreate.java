package com.kencana.firebase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import static android.text.TextUtils.isEmpty;

public class FirebaseDBCreate extends AppCompatActivity {
    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etKode, etNama, etSatuan, etHargaBeli, etHargaJual, etStok, etStokMin, etDeskripsi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbcreate);

        // inisialisasi fields EditText dan Button
        etKode = (EditText) findViewById(R.id.et_kodebarang);
        etNama = (EditText) findViewById(R.id.et_namabarang);
        etSatuan = (EditText) findViewById(R.id.et_satuan);
        etHargaBeli = (EditText) findViewById(R.id.et_hargabeli);
        etHargaJual = (EditText) findViewById(R.id.et_hargajual);
        etStok = (EditText) findViewById(R.id.et_stok);
        etStokMin = (EditText) findViewById(R.id.et_stokmin);
        etDeskripsi = (EditText) findViewById(R.id.et_deskripsi);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final Barang barang = (Barang) getIntent().getSerializableExtra("data");

        if (barang != null) {
            etKode.setText(barang.getKd_barang());
            etNama.setText(barang.getNm_barang());
            etSatuan.setText(barang.getSatuan());
            etHargaBeli.setText(barang.getHarga_beli());
            etHargaJual.setText(barang.getHarga_jual());
            etStok.setText(barang.getStok());
            etStokMin.setText(barang.getStok_min());
            etDeskripsi.setText(barang.getDeskripsi());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    barang.setKd_barang(etKode.getText().toString());
                    barang.setNm_barang(etNama.getText().toString());
                    barang.setSatuan(etSatuan.getText().toString());
                    barang.setHarga_beli(etHargaBeli.getText().toString());
                    barang.setHarga_jual(etHargaJual.getText().toString());
                    barang.setStok(etStok.getText().toString());
                    barang.setStok_min(etStokMin.getText().toString());
                    barang.setDeskripsi(etDeskripsi.getText().toString());
                    updateBarang(barang);
                }
            });
        } else {
            // kode yang dipanggil ketika tombol Submit diklik
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etKode.getText().toString()) &&
                            !isEmpty(etNama.getText().toString()) &&
                            !isEmpty(etSatuan.getText().toString()) &&
                            !isEmpty(etHargaBeli.getText().toString()) &&
                            !isEmpty(etHargaJual.getText().toString()) &&
                            !isEmpty(etStok.getText().toString()) &&
                            !isEmpty(etStokMin.getText().toString()) &&
                            !isEmpty(etDeskripsi.getText().toString())
                    )
                        submitBarang(new Barang(etKode.getText().toString(),
                                etNama.getText().toString(),
                                etSatuan.getText().toString(),
                                etHargaBeli.getText().toString(),
                                etHargaJual.getText().toString(),
                                etStok.getText().toString(),
                                etStokMin.getText().toString(),
                                etDeskripsi.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit),
                                "Data barang tidak boleh kosong",
                                Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etNama.getWindowToken(), 0);
                }
            });
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (barang == null){
            getSupportActionBar().setTitle("Tambah Data");
        }else{
            getSupportActionBar().setTitle("Edit Data");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(Barang barang) {
        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("barang") //akses parent index, ibaratnya seperti nama tabel
                .child(barang.getKey()) //select barang berdasarkan key
                .setValue(barang) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil diupdatekan",
                                Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitBarang(Barang barang) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke
         Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang
         akan dijalankan
         * ketika data berhasil ditambahkan
         */

        database.child("barang").push().setValue(barang)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etKode.setText("");
                        etNama.setText("");
                        etSatuan.setText("");
                        etHargaBeli.setText("");
                        etHargaJual.setText("");
                        etStok.setText("");
                        etStokMin.setText("");
                        etDeskripsi.setText("");
                        Snackbar.make(findViewById(R.id.bt_submit),
                                "Data berhasil ditambahkan",
                                Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, FirebaseDBCreate.class);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //hide keyboard when touch other view
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
