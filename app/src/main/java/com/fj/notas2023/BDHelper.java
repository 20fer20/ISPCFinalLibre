package com.fj.notas2023;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {

    public BDHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table registro(codigo integer primary key unique," +
                "curso text not null," +
                "nota1 integer not null," +
                "nota2 integer not null," +
                "nota3 integer not null," +
                "promedio integer  not null)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists registro");
        db.execSQL("create table registro(codigo integer primary key unique," +
                "curso text not null," +
                "nota1 integer  not null," +
                "nota2 integer  not null," +
                "nota3 integer  not null," +
                "promedio integer  not null)");
    }


    public ArrayList<String> getAllRegistros()
    {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from registro", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){

            array_list.add("Id:" + res.getString(0) +" | "+"Curso"+" "+
                    res.getString(1) + " | "+ " Promedio " + res.getString(5) );

            res.moveToNext();}

        return array_list;
    }
}
