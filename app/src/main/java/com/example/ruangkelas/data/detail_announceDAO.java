package com.example.ruangkelas.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruangkelas.model.detail_announce;

@Dao
public interface detail_announceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertDetAnn(detail_announce DetailAnnounce);

    @Update
    int updateDetAnn(detail_announce DetailAnnounce);

    @Delete
    void deleteDetAnn(detail_announce DetailAnnounce);

    @Query("SELECT * FROM detail_announce")
    detail_announce[] readDataDetAnn();

}
