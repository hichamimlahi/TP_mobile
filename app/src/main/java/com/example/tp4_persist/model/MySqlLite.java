package com.example.tp4_persist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlLite extends SQLiteOpenHelper {

    private String creation = "CREATE TABLE EMPLOYE ("
            +"ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"NOM TEXT NOT NULL,"
            +"PRENOM TEXT NOT NULL,"
            +"AGE INTEGER NOT NULL,"
            +"CIN TEXT NOT NULL,"
            +"FONCTION TEXT NOT NULL,"
            +"DOTI INTEGER NOT NULL);";

    public MySqlLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}