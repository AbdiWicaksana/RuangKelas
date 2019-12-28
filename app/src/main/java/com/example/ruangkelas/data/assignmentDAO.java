package com.example.ruangkelas.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruangkelas.model.assignment;

@Dao
public interface assignmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAssignment(assignment Assignment);

    @Update
    int updateAssignment(assignment Assignment);

    @Delete
    void deleteAssignment(assignment Assignment);

    @Query("SELECT * FROM assignment")
    assignment[] readDataAssignmenr();

}
