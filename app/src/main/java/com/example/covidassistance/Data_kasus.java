package com.example.covidassistance;

import java.util.Date;

public class Data_kasus {

    public String nip, nama, tipe, provinsi, kota, kecamatan, kelurahan, tanggal;

    public Data_kasus(String in_nip, String in_nama, String in_tipe, String in_provinsi, String in_kota, String in_kecamatan, String in_kelurahan, String in_tanggal){
        this.nip = in_nip;
        this.nama = in_nama;
        this.tipe = in_tipe;
        this.provinsi = in_provinsi;
        this.kota = in_kota;
        this.kecamatan = in_kecamatan;
        this.kelurahan = in_kelurahan;
        this.tanggal = in_tanggal;
    }

}
