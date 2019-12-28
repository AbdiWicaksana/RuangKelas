package com.example.ruangkelas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "assignment", foreignKeys = @ForeignKey(entity = kelas.class, parentColumns = "id", childColumns = "id_kelas"))
public class assignment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int assignId;

    @ColumnInfo(name = "id_kelas")
    public int assignIdKelas;

    @ColumnInfo(name = "nama_assignment")
    public String assignName;

    @ColumnInfo(name = "detail_assignment")
    public String assignDetail;

    @ColumnInfo(name = "date_assignment")
    public String assignDate;

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public int getAssignIdKelas() {
        return assignIdKelas;
    }

    public void setAssignIdKelas(int assignIdKelas) {
        this.assignIdKelas = assignIdKelas;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getAssignDetail() {
        return assignDetail;
    }

    public void setAssignDetail(String assignDetail) {
        this.assignDetail = assignDetail;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }
}
