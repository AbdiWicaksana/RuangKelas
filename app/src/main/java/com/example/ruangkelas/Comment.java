package com.example.ruangkelas;

public class Comment {
    private String namaPengCom;
    private String comPeng;
    private String fotoPengCom;

    public Comment() {

    }

    public Comment(String namaPengCom, String comPeng, String fotoPengCom) {
        this.namaPengCom = namaPengCom;
        this.comPeng = comPeng;
        this.fotoPengCom = fotoPengCom;
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

    public String getFotoPengCom() {
        return fotoPengCom;
    }

    public void setFotoPengCom(String fotoPengCom) {
        this.fotoPengCom = fotoPengCom;
    }
}
