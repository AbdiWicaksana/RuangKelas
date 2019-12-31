package com.example.ruangkelas.data;

public class Timeline {
    public Integer id;
    public String nama_user;
    public String title;
    public String announce;
    public String photo;

    public Timeline() {

    }

    public Timeline(Integer id, String nama_user, String title, String announce, String photo) {
        this.id = id;
        this.nama_user = nama_user;
        this.title = title;
        this.announce = announce;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}