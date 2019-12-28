package com.example.ruangkelas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "kelas")
public class kelas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int kelasid;

    @ColumnInfo(name = "nama_kelas")
    public String namaKelas;

    @ColumnInfo(name = "subject_kelas")
    public String subjekKelas;

    @ColumnInfo(name = "author_kelas")
    public String authorKelas;

    public int getKelasId() {
        return kelasid;
    }

    public void setKelasid(int kelasid) {
        this.kelasid = kelasid;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getSubjekKelas() {
        return subjekKelas;
    }

    public void setSubjekKelas(String subjekKelas) {
        this.subjekKelas = subjekKelas;
    }

    public String getAuthorKelas() {
        return authorKelas;
    }

    public void setAuthorKelas(String authorKelas) {
        this.authorKelas = authorKelas;
    }
}
