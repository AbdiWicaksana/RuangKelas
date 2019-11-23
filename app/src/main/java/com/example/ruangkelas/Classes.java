package com.example.ruangkelas;

public class Classes {
    private String namaKelas;
    private String subjekKelas;
    private String authorKelas;

    public Classes() {

    }

    public Classes(String namaKelas, String subjekKelas, String authorKelas) {
        this.namaKelas = namaKelas;
        this.subjekKelas = subjekKelas;
        this.authorKelas = authorKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getSubjekKelas() {
        return subjekKelas;
    }

    public void setSubjekKelas(String subjekKelas) {
        this.subjekKelas = subjekKelas;
    }

    public String getAuthorKelas() {
        return authorKelas;
    }

    public void setAuthorKelas(String authorKelas) {
        this.authorKelas = authorKelas;
    }
}
