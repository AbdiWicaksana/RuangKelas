package com.example.ruangkelas;

public class Comment {
    private String namaPengCom;
    private String comPeng;
//    private String fotoPengCom;
    private String photo;

    public Comment() {

    }

    public Comment(String namaPengCom, String comPeng, String photo) {
        this.namaPengCom = namaPengCom;
        this.comPeng = comPeng;
        this.photo = photo;
    }

    public String getNamaPengCom() {
        return namaPengCom;
    }

    public void setNamaPengCom(String namaPengCom) {
        this.namaPengCom = namaPengCom;
    }

    public String getComPeng() {
        return comPeng;
    }

    public void setComPeng(String comPeng) {
        this.comPeng = comPeng;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
