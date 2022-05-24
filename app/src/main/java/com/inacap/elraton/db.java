package com.inacap.elraton;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class db extends SQLiteOpenHelper
{
    public db (@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE usuario(email email primary key not null, contrasenna text not null, nombre text not null, apellido text not null, rol boolean not null)");
        db.execSQL("CREATE TABLE producto(id integer primary key autoincrement not null, titulo text not null, descripcion text not null, precio integer not null, cantidad integer not null)");
        db.execSQL("CREATE TABLE carrito(id integer primary key not null)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists producto");
        db.execSQL("CREATE TABLE usuario(email email primary key not null, nombre text not null, apellido text not null, rol boolean not null)");
        db.execSQL("CREATE TABLE producto(id integer primary key autoincrement not null, titulo text not null, descripcion text not null, precio integer not null, cantidad integer not null)");
        db.execSQL("CREATE TABLE carrito(id integer primary key not null)");
        onCreate(db);
    }
    //https://www.youtube.com/watch?v=e7yQHjWiiLM
    //https://youtu.be/dA_T1IHxcMg
}
