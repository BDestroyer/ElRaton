package com.inacap.elraton;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "elRaton.db";

    public db (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (email string PRIMARY KEY, password password not null, primerNombre string not null, segundoNombre string not null, apellidoPaterno string not null, apellidoMaterno string not null)");
        db.execSQL("CREATE TABLE producto(id int primary key autoincrement not null, descripcion string not null, precio int not null, cantidad int not null)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("drop table if exists usuario");
        db.execSQL("CREATE TABLE usuario (email string PRIMARY KEY, password password not null, primerNombre string not null, segundoNombre string not null, apellidoPaterno string not null, apellidoMaterno string not null)");
        db.execSQL("drop table if exists producto");
        db.execSQL("CREATE TABLE producto(id int primary key autoincrement not null, descripcion string not null, precio int not null, cantidad int not null)");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
