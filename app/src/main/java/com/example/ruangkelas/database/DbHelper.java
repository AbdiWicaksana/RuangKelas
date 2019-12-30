package com.example.ruangkelas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "id12007477_ruangkelas.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.AnnounceEntry.SQL_CREATE_ANNOUNCE);
        db.execSQL(DbContract.AssignmentEntry.SQL_CREATE_ASSIGNMENT);
        db.execSQL(DbContract.DetailAnnounceEntry.SQL_CREATE_DETAIL_ANNOUNCE);
        db.execSQL(DbContract.KelasEntry.SQL_CREATE_KELAS);
        db.execSQL(DbContract.MemberEntry.SQL_CREATE_MEMBER);
        db.execSQL(DbContract.NotificationEntry.SQL_CREATE_NOTIFICATION);
        db.execSQL(DbContract.UsersEntry.SQL_CREATE_USERS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DbContract.AnnounceEntry.SQL_DELETE_ANNOUNCE);
        db.execSQL(DbContract.AssignmentEntry.SQL_DELETE_ASSIGNMENT);
        db.execSQL(DbContract.DetailAnnounceEntry.SQL_DELETE_DETAIL_ANNOUNCE);
        db.execSQL(DbContract.KelasEntry.SQL_DELETE_KELAS);
        db.execSQL(DbContract.MemberEntry.SQL_DELETE_MEMBER);
        db.execSQL(DbContract.NotificationEntry.SQL_DELETE_NOTIFICATION);
        db.execSQL(DbContract.UsersEntry.SQL_DELETE_USERS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
