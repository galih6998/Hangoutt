package com.example.hangout;

public class ModelCafe {
    String nama;
    String deskripsi;
    String gambar;

    public ModelCafe(String nama, String deskripsi, String gambar) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public ModelCafe() {
    }
}