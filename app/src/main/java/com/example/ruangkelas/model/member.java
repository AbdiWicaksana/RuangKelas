package com.example.ruangkelas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "member", foreignKeys = {
        @ForeignKey(entity = user.class, parentColumns = "id", childColumns = "id_user"),
        @ForeignKey(entity = kelas.class, parentColumns = "id", childColumns = "id_kelas")
})
public class member implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int memberId;

    @ColumnInfo(name = "id_user")
    public int memberIdUser;

    @ColumnInfo(name = "id_kelas")
    public int memberIdKelas;

    @ColumnInfo(name = "jabatan")
    public String memberJabatan;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getMemberIdUser() {
        return memberIdUser;
    }

    public void setMemberIdUser(int memberIdUser) {
        this.memberIdUser = memberIdUser;
    }

    public int getMemberIdKelas() {
        return memberIdKelas;
    }

    public void setMemberIdKelas(int memberIdKelas) {
        this.memberIdKelas = memberIdKelas;
    }

    public String getMemberJabatan() {
        return memberJabatan;
    }

    public void setMemberJabatan(String memberJabatan) {
        this.memberJabatan = memberJabatan;
    }
}
