package com.example.ruangkelas.database;

import android.provider.BaseColumns;

public final class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {}

    /* Inner class that defines the table contents */
    public static class TimelineEntry implements BaseColumns {
        public static final String TABLE_NAME = "timeline";
        public static final String COLUMN_NAMA_USER = "nama_user";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ANNOUNCE = "announce";

        public static final String SQL_CREATE_TIMELINE =
                "CREATE TABLE " + TimelineEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        TimelineEntry.COLUMN_NAMA_USER + " TEXT," +
                        TimelineEntry.COLUMN_TITLE + " TEXT," +
                        TimelineEntry.COLUMN_ANNOUNCE + " TEXT)";

        public static final String SQL_DELETE_TIMELINE =
                "DROP TABLE IF EXISTS " + TimelineEntry.TABLE_NAME;
    }

    public static class AssignmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "assignment";
        public static final String COLUMN_NAMA_ASSIGNMENT = "nama_assignment";
        public static final String COLUMN_DETAIL_ASSIGNMENT = "detail_assignment";
        public static final String COLUMN_DATE_ASSIGNMENT = "date_assignment";

        public static final String SQL_CREATE_ASSIGNMENT =
                "CREATE TABLE " + AssignmentEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        AssignmentEntry.COLUMN_NAMA_ASSIGNMENT + " TEXT," +
                        AssignmentEntry.COLUMN_DETAIL_ASSIGNMENT + " TEXT," +
                        AssignmentEntry.COLUMN_DATE_ASSIGNMENT + " TEXT)";

        public static final String SQL_DELETE_ASSIGNMENT =
                "DROP TABLE IF EXISTS " + AssignmentEntry.TABLE_NAME;
    }

    public static class KelasEntry implements BaseColumns {
        public static final String TABLE_NAME = "kelas";
        public static final String COLUMN_NAMA_KELAS = "nama_kelas";
        public static final String COLUMN_SUBJECT_KELAS = "subject_kelas";
        public static final String COLUMN_AUTHOR_KELAS = "author_kelas";

        public static final String SQL_CREATE_KELAS =
                "CREATE TABLE " + KelasEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        KelasEntry.COLUMN_NAMA_KELAS + " TEXT," +
                        KelasEntry.COLUMN_SUBJECT_KELAS + " TEXT," +
                        KelasEntry.COLUMN_AUTHOR_KELAS + " TEXT)";

        public static final String SQL_DELETE_KELAS =
                "DROP TABLE IF EXISTS " + KelasEntry.TABLE_NAME;
    }

}