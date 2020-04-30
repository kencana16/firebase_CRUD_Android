package com.kencana.firebase;

import java.io.Serializable;

public class Barang implements Serializable {
    private String kd_barang, nm_barang, satuan, harga_beli, harga_jual, stok, stok_min, deskripsi;
    private String key;

    public Barang(){}
    public Barang(String kd_barang,String nm_barang,String satuan,String harga_beli,String harga_jual,
                  String stok,String stok_min,String deskripsi){
        this.kd_barang = kd_barang;
        this.nm_barang = nm_barang;
        this.satuan = satuan;
        this.harga_beli = harga_jual;
        this.harga_jual = harga_beli;
        this.stok = stok;
        this.stok_min = stok_min;
        this.deskripsi = deskripsi;
    }

    public String getKd_barang() {
        return kd_barang;
    }

    public void setKd_barang(String kd_barang) {
        this.kd_barang = kd_barang;
    }

    public String getNm_barang() {
        return nm_barang;
    }

    public void setNm_barang(String nm_barang) {
        this.nm_barang = nm_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getStok_min() {
        return stok_min;
    }

    public void setStok_min(String stok_min) {
        this.stok_min = stok_min;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Override
    public String toString() {
        return " "+kd_barang+"\n" +
                " "+nm_barang +"\n" +
                " "+satuan +"\n" +
                " "+harga_beli +"\n" +
                " "+harga_jual +"\n" +
                " "+stok +"\n" +
                " "+stok_min +"\n" +
                " "+deskripsi;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
