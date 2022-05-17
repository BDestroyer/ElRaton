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
        db.execSQL("CREATE TABLE usuario(email email primary key not null, nombre text not null, apellido text not null)");
        db.execSQL("CREATE TABLE producto(id int primary key autoincrement not null, descripcion text not null, precio int not null, cantidad int not null)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists producto");
        db.execSQL("CREATE TABLE usuario(email email primary key not null, nombre text not null, apellido text not null)");
        db.execSQL("CREATE TABLE producto(id int primary key autoincrement not null, descripcion text not null, precio int not null, cantidad int not null)");
        onCreate(db);
    }
}
