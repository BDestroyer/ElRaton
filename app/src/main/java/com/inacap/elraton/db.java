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
        db.execSQL("CREATE TABLE producto(id integer primary key autoincrement not null, rutaImg text not null, titulo text not null, descripcion text not null, precio integer not null, cantidad integer not null)");
        db.execSQL("CREATE TABLE carrito(id integer not null, cantidad integer not null, valorTotal integer not null)");
        db.execSQL("CREATE TABLE miscompras(id integer primary key not null, rutaImg text not null, nombreProducto text not null, precioProducto integer not null, DescripcionProducto text not null, cantidadProducto integer not null, emailUsuario email not null)");
        db.execSQL("CREATE TABLE contacto(id integer primary key autoincrement not null, dato text not null)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists producto");
        db.execSQL("drop table if exists carrito");
        db.execSQL("drop table if exists miscompras");
        db.execSQL("drop table if exists contacto");
        db.execSQL("CREATE TABLE contacto(id integer primary key autoincrement not null, dato text not null)");
        db.execSQL("CREATE TABLE usuario(email email primary key not null, email email not null, apellido text not null, rol boolean not null)");
        db.execSQL("CREATE TABLE producto(id integer primary key autoincrement not null, rutaImg text not null, titulo text not null, descripcion text not null, precio integer not null, cantidad integer not null)");
        db.execSQL("CREATE TABLE carrito(id integer not null, cantidad integer not null, valorTotal integer not null)");
        db.execSQL("CREATE TABLE miscompras(id integer primary key not null, nombreProducto text not null, precioProducto integer not null, DescripcionProducto text not null, cantidadProducto integer not null, emailUsuario email not null)");
        onCreate(db);
    }

}
