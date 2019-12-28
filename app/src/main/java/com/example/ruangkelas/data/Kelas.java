package com.example.ruangkelas.data;

public class Kelas {
    public Integer id;
    public String nama_kelas;
    public String subject_kelas;
    public String author_kelas;

    public Kelas() {

    }

    public Kelas(Integer id, String nama_kelas, String subject_kelas, String author_kelas) {
        this.id = id;
        this.nama_kelas = nama_kelas;
        this.subject_kelas = subject_kelas;
        this.author_kelas = author_kelas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getSubject_kelas() {
        return subject_kelas;
    }

    public void setSubject_kelas(String subject_kelas) {
        this.subject_kelas = subject_kelas;
    }

    public String getAuthor_kelas() {
        return author_kelas;
    }

    public void setAuthor_kelas(String author_kelas) {
        this.author_kelas = author_kelas;
    }
}
