package com.example.ruangkelas.data;

public class Comment {

    public String nama;
    public String  comment;

    public Comment() {

    }

    public Comment(String nama, String comment) {
        this.nama = nama;
        this.comment = comment;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
