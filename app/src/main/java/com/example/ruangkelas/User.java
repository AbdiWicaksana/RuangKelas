package com.example.ruangkelas;

public class User {
    private String nameUser;
    private String usernameUser;
    private String emailUser;
    private String passUser;
    private String confpassUser;
    private String imageUser;

    public User() {

    }

    public User(String nameUser, String usernameUser, String emailUser, String passUser, String confpassUser, String imageUser) {
        this.nameUser = nameUser;
        this.usernameUser = usernameUser;
        this.emailUser = emailUser;
        this.passUser = passUser;
        this.confpassUser  = confpassUser;
        this.imageUser = imageUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public String getConfpassUser() {
        return confpassUser;
    }

    public void setConfpassUser(String confpassUser) {
        this.confpassUser = confpassUser;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }
}
