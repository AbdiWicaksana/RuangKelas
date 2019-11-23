package com.example.ruangkelas;

public class Mahasiswa {
    private String namaMahasiswa;
    private String nim;
    private String fotoMahasiswa;

    public Mahasiswa() {

    }

    public Mahasiswa(String namaMahasiswa, String nim, String fotoMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
        this.nim = nim;
        this.fotoMahasiswa = fotoMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getNim() {
        return nim;
    }

    public String getFotoMahasiswa() {
        return fotoMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setFotoMahasiswa(String fotoMahasiswa) {
        this.fotoMahasiswa = fotoMahasiswa;
    }
}
