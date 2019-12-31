package com.example.ruangkelas.data;

public class Member {
    public String nama;
    public String nim;
    public String photo;

    public Member() {

    }

    public Member(String nama, String nim, String photo) {
        this.nama = nama;
        this.nim = nim;
        this.photo = photo;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String  photo) {
        this.photo = photo;
    }
}
