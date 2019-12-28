package com.example.ruangkelas.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruangkelas.model.member;

@Dao
public interface memberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMember(member Member);

    @Update
    int updateMember(member Member);

    @Delete
    void deleteMember(member Member);

    @Query("SELECT * FROM member")
    member[] readDataMember();

}
