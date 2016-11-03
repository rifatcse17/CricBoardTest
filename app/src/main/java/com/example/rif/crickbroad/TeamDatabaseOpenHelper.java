package com.example.rif.crickbroad;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeamDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cricboard.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TEAM_TABLE = "team";
    public static final String PLAYER_TABLE = "player";

    public static final String COLUMN_TEAM_ID = "tid";
    public static final String COLUMN_TEAM_NAME = "tname";

    public static final String COLUMN_PLAYER_ID = "pid";
    public static final String COLUMN_PLAYER_NAME = "pname";
    public static final String COLUMN_FOREIGN_TEAM_ID = "tkey";


    private static final String CREATE_TEAM_TABLE = "CREATE TABLE "
            + TEAM_TABLE + " (" + COLUMN_TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEAM_NAME + " TEXT)";
    private static final String CREATE_PLAYER_TABLE = "CREATE TABLE "
            + PLAYER_TABLE + " (" + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOREIGN_TEAM_ID + " INTEGER,"
            + COLUMN_PLAYER_NAME + " TEXT, FOREIGN KEY(" + COLUMN_FOREIGN_TEAM_ID + ") REFERENCES " + TEAM_TABLE + "(" + COLUMN_TEAM_ID + "))";

    ///FOREIGN KEY(customer_id) REFERENCES customers(id)


    public TeamDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEAM_TABLE);
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TEAM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ PLAYER_TABLE);
        onCreate(db);
    }
}
