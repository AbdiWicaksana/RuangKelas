package com.example.ruangkelas.data.factory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ruangkelas.model.kelas;
import com.example.ruangkelas.model.user;
import com.example.ruangkelas.model.announce;
import com.example.ruangkelas.model.detail_announce;
import com.example.ruangkelas.model.assignment;
import com.example.ruangkelas.model.member;
import com.example.ruangkelas.data.kelasDAO;
import com.example.ruangkelas.data.userDAO;
import com.example.ruangkelas.data.announceDAO;
import com.example.ruangkelas.data.detail_announceDAO;
import com.example.ruangkelas.data.assignmentDAO;
import com.example.ruangkelas.data.memberDAO;

@Database(entities = {kelas.class, user.class, announce.class, detail_announce.class, assignment.class, member.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract kelasDAO KelasDAO();
    public abstract userDAO UserDAO();
    public abstract announceDAO AnnounceDAO();
    public abstract detail_announceDAO DetAnnDAO();
    public abstract assignmentDAO AssignmentDAO();
    public abstract memberDAO MemberDAO();

}
