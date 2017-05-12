package com.example.android.courtcounterdatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.courtcounterdatabase.data.GameListContract.GameListEntry.COLUMN_TIMESTAMP;
import static com.example.android.courtcounterdatabase.data.GameListContract.GameListEntry.TABLE_NAME;

/**
 * Created by ETORO on 10/05/2017.
 */

public class GameListDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "courtcounterdatabase.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public GameListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold waitlist data

        //_ID is from the BaseColumn Interface it implemented
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + GameListContract.GameListEntry.TABLE_NAME + " (" +
                GameListContract.GameListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GameListContract.GameListEntry.COLUMN_TEAM_A + " TEXT NOT NULL, " +
                GameListContract.GameListEntry.COLUMN_TEAM_A_SCORE + " INTEGER NOT NULL, " +
                GameListContract.GameListEntry.COLUMN_TEAM_B + " TEXT NOT NULL, " +
                GameListContract.GameListEntry.COLUMN_TEAM_B_SCORE + " INTEGER NOT NULL, " +
                GameListContract.GameListEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GameListContract.GameListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

