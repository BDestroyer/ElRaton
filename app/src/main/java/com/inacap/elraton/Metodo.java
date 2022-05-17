package com.inacap.elraton;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Metodo
{
    public SQLiteDatabase Conectar(db cn)
    {
        SQLiteDatabase b = cn.getWritableDatabase();
        return b;
    }
}
