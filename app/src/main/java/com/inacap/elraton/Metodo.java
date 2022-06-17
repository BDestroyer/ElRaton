package com.inacap.elraton;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

public class Metodo {
    public SQLiteDatabase Conectar(db cn) {
        SQLiteDatabase b = cn.getWritableDatabase();
        return b;
    }
    public void AgregarACarrito(int cantidad, View v, int id)
    {
        Toast.makeText(v.getContext(), "cantidad= "+cantidad+" id= "+id, Toast.LENGTH_SHORT).show();

    }

}
