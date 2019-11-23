package com.example.ruangkelas;

public class Dosen {
    private String namaDosen;
    private String fotoDosen;

    public Dosen() {

    }

    public Dosen(String namaDosen, String fotoDosen) {
        this.namaDosen = namaDosen;
        this.fotoDosen = fotoDosen;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public String getFotoDosen() {
        return fotoDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public void setFotoDosen(String fotoMahasiswa) {
        this.fotoDosen = fotoMahasiswa;
    }
}
