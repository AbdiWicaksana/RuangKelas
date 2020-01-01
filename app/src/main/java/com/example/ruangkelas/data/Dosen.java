package com.example.ruangkelas.data;

public class Dosen {
    private String nama;
    private String photo;

    public Dosen() {

    }

    public Dosen(String nama, String photo) {
        this.nama = nama;
        this.photo = photo;
    }

    public String getNama() {
        return nama;
    }

    public String getPhoto() {
        return photo;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
