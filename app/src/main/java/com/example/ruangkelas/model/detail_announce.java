package com.example.ruangkelas.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "detail_announce", foreignKeys = {
        @ForeignKey(entity = announce.class, parentColumns = "id", childColumns = "id_announce"),
        @ForeignKey(entity = user.class, parentColumns = "id", childColumns = "id_user")
})
public class detail_announce implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int detAnnId;

    @ColumnInfo(name = "id_announce")
    public int detAnnIdAnn;

    @ColumnInfo(name = "id_user")
    public int detAnnIdUser;

    @ColumnInfo(name = "comment")
    public String detAnnComment;

    public int getDetAnnId() {
        return detAnnId;
    }

    public void setDetAnnId(int detAnnId) {
        this.detAnnId = detAnnId;
    }

    public int getDetAnnIdAnn() {
        return detAnnIdAnn;
    }

    public void setDetAnnIdAnn(int detAnnIdAnn) {
        this.detAnnIdAnn = detAnnIdAnn;
    }

    public int getDetAnnIdUser() {
        return detAnnIdUser;
    }

    public void setDetAnnIdUser(int detAnnIdUser) {
        this.detAnnIdUser = detAnnIdUser;
    }

    public String getDetAnnComment() {
        return detAnnComment;
    }

    public void setDetAnnComment(String detAnnComment) {
        this.detAnnComment = detAnnComment;
    }
}
