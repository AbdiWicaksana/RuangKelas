package com.example.ruangkelas;

public class Timeline {
    private String namaSender;
    private String namaAnouncement;
    private String descAnouncement;
    private String fotoSender;

    public Timeline() {

    }

    public Timeline(String namaSender, String namaAnouncement, String descAnouncement, String fotoSender) {
        this.namaSender = namaSender;
        this.namaAnouncement = namaAnouncement;
        this.descAnouncement = descAnouncement;
        this.fotoSender = fotoSender;
    }

    public String getNamaSender() {
        return namaSender;
    }

    public void setNamaSender(String namaSender) {
        this.namaSender = namaSender;
    }

    public String getNamaAnouncement() {
        return namaAnouncement;
    }

    public void setNamaAnouncement(String namaAnouncement) {
        this.namaAnouncement = namaAnouncement;
    }

    public String getDescAnouncement() {
        return descAnouncement;
    }

    public void setDescAnouncement(String descAnouncement) {
        this.descAnouncement = descAnouncement;
    }

    public String getFotoSender() {
        return fotoSender;
    }

    public void setFotoSender(String fotoSender) {
        this.fotoSender = fotoSender;
    }
}