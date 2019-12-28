package com.example.ruangkelas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class user implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int userId;

    @ColumnInfo(name = "username")
    public String userUsername;

    @ColumnInfo(name = "name")
    public String userName;

    @ColumnInfo(name = "nim")
    public String userNim;

    @ColumnInfo(name = "email")
    public String userEmail;

    @ColumnInfo(name = "password")
    public String userPassword;

    @ColumnInfo(name = "photo")
    public String userPhoto;

    @ColumnInfo(name = "role")
    public String userRole = "User";

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNim() {
        return userNim;
    }

    public void setUserNim(String userNim) {
        this.userNim = userNim;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
