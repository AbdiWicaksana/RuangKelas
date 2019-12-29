package com.example.ruangkelas;

public class Assigment {
    private String namaAssigment;
    private String tanggalAssigment;
    private String fotoAssigment;
    private String detailAssigment;

    public Assigment() {

    }

    public Assigment(String namaAssigment, String tanggalAssigment, String fotoAssigment, String detailAssigment) {
        this.namaAssigment = namaAssigment;
        this.tanggalAssigment = tanggalAssigment;
        this.fotoAssigment = fotoAssigment;
        this.detailAssigment = detailAssigment;
    }

    public String getDetailAssigment() {
        return detailAssigment;
    }

    public void setDetailAssigment(String detailAssigment) {
        this.detailAssigment = detailAssigment;
    }

    public String getNamaAssigment() {
        return namaAssigment;
    }

    public void setNamaAssigment(String namaAssigment) {
        this.namaAssigment = namaAssigment;
    }

    public String getTanggalAssigment() {
        return tanggalAssigment;
    }

    public void setTanggalAssigment(String tanggalAssigment) {
        this.tanggalAssigment = tanggalAssigment;
    }

    public String getFotoAssigment() {
        return fotoAssigment;
    }

    public void setFotoAssigment(String fotoAssigment) {
        this.fotoAssigment = fotoAssigment;
    }
}
