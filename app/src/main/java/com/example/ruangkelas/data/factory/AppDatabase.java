package com.example.ruangkelas.data.factory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ruangkelas.model.kelas;
import com.example.ruangkelas.data.kelasDAO;

@Database(entities = {kelas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract kelasDAO KelasDAO();

}
