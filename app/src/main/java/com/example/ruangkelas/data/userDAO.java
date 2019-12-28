package com.example.ruangkelas.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruangkelas.model.user;

@Dao
public interface userDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(user User);

    @Update
    int updateUser(user User);

    @Delete
    void deleteUser(user User);

    @Query("SELECT * FROM users")
    user[] readDataUser();

}
