package com.cahyonoz.exercise_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController {
    DBHandler dbSqlite;
    public DBController(Context context){

        dbSqlite = new DBHandler(context);
    }

    public void saveMahasiswa(HashMap<String, String> queryValues){
        try {
            SQLiteDatabase database = dbSqlite.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nim", queryValues.get("nim"));
            values.put("nama", queryValues.get("nama"));
            values.put("jk", queryValues.get("jk"));
            values.put("email", queryValues.get("email"));
            database.insert("mahasiswa", null, values);
            database.close();
        }catch (Exception e){
            Log.d("GETDATA", e.getMessage());
        }
    }
    public void EditTeman(HashMap<String, String>queryValues){
        SQLiteDatabase database = dbSqlite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nim",queryValues.get("nim"));
        values.put("nama", queryValues.get("nama"));
        values.put("jk", queryValues.get("jk"));
        values.put("email", queryValues.get("email"));
        database.update("mahasiswa", values,"Id = ?", new String[]{queryValues.get("id")});
        database.close();
    }
    public void deleteMhs(String id){
        SQLiteDatabase database = dbSqlite.getWritableDatabase();
        database.delete("mahasiswa","Id = ?",new String[]{id});
    }

    public ArrayList<HashMap<String, String>> showAllMahasiswa(){
        ArrayList<HashMap<String, String>> data;
        data = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM mahasiswa";
        SQLiteDatabase database = dbSqlite.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Id", cursor.getString(0));
                map.put("nim", cursor.getString(1));
                map.put("nama", cursor.getString(2));
                map.put("jk", cursor.getString(3));
                map.put("email", cursor.getString(4));
                data.add(map);
            }while (cursor.moveToNext());
        }
        database.close();
        return data;
    }

    public ArrayList<HashMap<String, String>> showMahasiswabyId(int id){
        try {
            ArrayList<HashMap<String, String>> data;
            data = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT * FROM mahasiswa where Id='" + id + "'";
            SQLiteDatabase database = dbSqlite.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Id", cursor.getString(0));
                    map.put("nim", cursor.getString(1));
                    map.put("nama", cursor.getString(2));
                    map.put("jk", cursor.getString(3));
                    map.put("email", cursor.getString(4));
                    data.add(map);
                } while (cursor.moveToNext());
            }
            database.close();

            return data;
        }catch (Exception e){
            Log.d("GETDATA", e.getMessage());
            return null;
        }
    }
}
