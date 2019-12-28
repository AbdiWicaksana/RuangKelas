package com.example.ruangkelas.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruangkelas.model.announce;

@Dao
public interface announceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAnnounce(announce Announce);

    @Update
    int updateAnnounce(announce Announce);

    @Delete
    void deleteAnnounce(announce Announce);

    @Query("SELECT * FROM announce")
    announce[] readDataAnnounce();

}
