package com.example.ruangkelas.data;

public class Assignment {
    public Integer id;
    public String nama_assignment;
    public String detail_assignment;
    public String date_assignment;

    public Assignment() {

    }

    public Assignment   (Integer id, String nama_assignment, String detail_assignment, String date_assignment) {
        this.id = id;
        this.nama_assignment = nama_assignment;
        this.detail_assignment = detail_assignment;
        this.date_assignment = date_assignment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_assignment() {
        return nama_assignment;
    }

    public void setNama_assignment(String nama_assignment) {
        this.nama_assignment = nama_assignment;
    }

    public String getDetail_assignment() {
        return detail_assignment;
    }

    public void setDetail_assignment(String detail_assignment) {
        this.detail_assignment = detail_assignment;
    }

    public String getDate_assignment() {
        return date_assignment;
    }

    public void setDate_assignment(String date_assignment) {
        this.date_assignment = date_assignment;
    }
}