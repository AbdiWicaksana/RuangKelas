package com.example.ruangkelas.database;

import android.provider.BaseColumns;

public final class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {}

    /* Inner class that defines the table contents */
    public static class AnnounceEntry implements BaseColumns {
        public static final String TABLE_NAME = "Timeline";
        public static final String COLUMN_ID_KELAS = "id_kelas";
        public static final String COLUMN_ID_USER = "id_user";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ANNOUNCE = "announce";
        public static final String COLUMN_STATUS = "status";

        public static final String SQL_CREATE_ANNOUNCE =
                "CREATE TABLE " + AnnounceEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        AnnounceEntry.COLUMN_ID_KELAS + " TEXT," +
                        AnnounceEntry.COLUMN_ID_USER + " TEXT," +
                        AnnounceEntry.COLUMN_TITLE + " TEXT," +
                        AnnounceEntry.COLUMN_ANNOUNCE + " TEXT," +
                        AnnounceEntry.COLUMN_STATUS + " TEXT)";

        public static final String SQL_DELETE_ANNOUNCE =
                "DROP TABLE IF EXISTS " + AnnounceEntry.TABLE_NAME;
    }

    public static class AssignmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "assignment";
        public static final String COLUMN_ID_KELAS = "id_kelas";
        public static final String COLUMN_NAMA_ASSIGNMENT = "nama_assignment";
        public static final String COLUMN_DETAIL_ASSIGNMENT = "detail_assignment";
        public static final String COLUMN_DATE_ASSIGNMENT = "date_assignment";
        public static final String COLUMN_STATUS = "status";

        public static final String SQL_CREATE_ASSIGNMENT =
                "CREATE TABLE " + AssignmentEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        AssignmentEntry.COLUMN_ID_KELAS + " TEXT," +
                        AssignmentEntry.COLUMN_NAMA_ASSIGNMENT + " TEXT," +
                        AssignmentEntry.COLUMN_DETAIL_ASSIGNMENT + " TEXT," +
                        AssignmentEntry.COLUMN_DATE_ASSIGNMENT + " TEXT," +
                        AssignmentEntry.COLUMN_STATUS + " TEXT)";

        public static final String SQL_DELETE_ASSIGNMENT =
                "DROP TABLE IF EXISTS " + AssignmentEntry.TABLE_NAME;
    }

    public static class DetailAnnounceEntry implements BaseColumns {
        public static final String TABLE_NAME = "detail_announce";
        public static final String COLUMN_ID_ANNOUNCE = "id_announce";
        public static final String COLUMN_ID_USER = "id_user";
        public static final String COLUMN_COMMENT = "comment";

        public static final String SQL_CREATE_DETAIL_ANNOUNCE =
                "CREATE TABLE " + DetailAnnounceEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        DetailAnnounceEntry.COLUMN_ID_ANNOUNCE + " TEXT," +
                        DetailAnnounceEntry.COLUMN_ID_USER + " TEXT," +
                        DetailAnnounceEntry.COLUMN_COMMENT + " TEXT)";

        public static final String SQL_DELETE_DETAIL_ANNOUNCE =
                "DROP TABLE IF EXISTS " + DetailAnnounceEntry.TABLE_NAME;
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

    public static class MemberEntry implements BaseColumns {
        public static final String TABLE_NAME = "member";
        public static final String COLUMN_ID_USER = "id_user";
        public static final String COLUMN_ID_KELAS = "id_kelas";

        public static final String SQL_CREATE_MEMBER =
                "CREATE TABLE " + MemberEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        MemberEntry.COLUMN_ID_USER + " TEXT," +
                        MemberEntry.COLUMN_ID_KELAS + " TEXT)";

        public static final String SQL_DELETE_MEMBER =
                "DROP TABLE IF EXISTS " + MemberEntry.TABLE_NAME;
    }

    public static class NotificationEntry implements BaseColumns {
        public static final String TABLE_NAME = "notification";
        public static final String COLUMN_ID_USER = "id_user";
        public static final String COLUMN_TOKEN = "token";

        public static final String SQL_CREATE_NOTIFICATION =
                "CREATE TABLE " + NotificationEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        NotificationEntry.COLUMN_ID_USER + " TEXT," +
                        NotificationEntry.COLUMN_TOKEN + " TEXT)";

        public static final String SQL_DELETE_NOTIFICATION =
                "DROP TABLE IF EXISTS " + NotificationEntry.TABLE_NAME;
    }

    public static class UsersEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_NIM = "nim";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_PHOTO = "photo";
        public static final String COLUMN_ROLE = "role";

        public static final String SQL_CREATE_USERS =
                "CREATE TABLE " + UsersEntry.TABLE_NAME + " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY," +
                        UsersEntry.COLUMN_USERNAME + " TEXT," +
                        UsersEntry.COLUMN_NAMA + " TEXT," +
                        UsersEntry.COLUMN_NIM + " TEXT," +
                        UsersEntry.COLUMN_EMAIL + " TEXT," +
                        UsersEntry.COLUMN_PASSWORD + " TEXT," +
                        UsersEntry.COLUMN_PHOTO + " TEXT," +
                        UsersEntry.COLUMN_ROLE + " TEXT)";

        public static final String SQL_DELETE_USERS =
                "DROP TABLE IF EXISTS " + UsersEntry.TABLE_NAME;
    }
}