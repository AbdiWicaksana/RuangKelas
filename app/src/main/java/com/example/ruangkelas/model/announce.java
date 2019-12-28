package com.example.ruangkelas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "announce", foreignKeys = {
        @ForeignKey(entity = kelas.class, parentColumns = "id", childColumns = "id_kelas"),
        @ForeignKey(entity = user.class, parentColumns = "id", childColumns = "id_user")
})
public class announce implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int annId;

    @ColumnInfo(name = "id_kelas")
    public int annIdKelas;

    @ColumnInfo(name = "id_user")
    public int annIdUser;

    @ColumnInfo(name = "title")
    public String annTitle;

    @ColumnInfo(name = "announce")
    public String annAnnounce;

    @ColumnInfo(name = "status")
    public String annStatus;

    public int getAnnId() {
        return annId;
    }

    public void setAnnId(int annId) {
        this.annId = annId;
    }

    public int getAnnIdKelas() {
        return annIdKelas;
    }

    public void setAnnIdKelas(int annIdKelas) {
        this.annIdKelas = annIdKelas;
    }

    public int getAnnIdUser() {
        return annIdUser;
    }

    public void setAnnIdUser(int annIdUser) {
        this.annIdUser = annIdUser;
    }

    public String getAnnTitle() {
        return annTitle;
    }

    public void setAnnTitle(String annTitle) {
        this.annTitle = annTitle;
    }

    public String getAnnAnnounce() {
        return annAnnounce;
    }

    public void setAnnAnnounce(String annAnnounce) {
        this.annAnnounce = annAnnounce;
    }

    public String getAnnStatus() {
        return annStatus;
    }

    public void setAnnStatus(String annStatus) {
        this.annStatus = annStatus;
    }
}
