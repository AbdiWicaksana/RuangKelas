package com.example.ruangkelas.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruangkelas.model.kelas;

import java.util.List;

@Dao
public interface kelasDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertKelas(kelas Kelas);

    @Update
    int updateKelas(kelas Kelas);

    @Delete
    int deleteKelas(kelas Kelas);

    @Query("SELECT * FROM tb_kelas")
    public List<kelas> selectAllKelas();

    @Query("SELECT * FROM tb_kelas WHERE kelasid = :id LIMIT 1")
    kelas selectKelasDetail(int id);

}
