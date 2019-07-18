package com.cahyonoz.exercise_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context) {
        super(context, "Mahasiswa.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mahasiswa" +
                "(Id INTEGER PRIMARY KEY, nim INTEGER, nama TEXT, jk TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    //@Override
    //public boolean onUpdate(SQLiteDatabase db) {
    // SQLiteDatabase db = this.getWritableDatabase();
    //
    //}
}


