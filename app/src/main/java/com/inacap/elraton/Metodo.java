package com.inacap.elraton;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
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
        try {
            db cU = new db(v.getContext(), "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            Cursor cursor=bd.rawQuery("select * from carrito",null);
            if (cursor.moveToFirst())
            {
                if(id==cursor.getInt(0))
                {
                    Toast.makeText(v.getContext(), "Producto ya agregado en el carrito", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues r = new ContentValues();
                    r.put("id", id);
                    r.put("cantidad", cantidad);
                    bd.insert("carrito", null, r);
                    Toast.makeText(v.getContext(), "Producto agregado correctamente al carrito", Toast.LENGTH_SHORT).show();
                    bd.close();
                }
            }
        }
        catch (SQLiteConstraintException e)
        {
            Toast.makeText(v.getContext(), "Algo ha salido mal, contactese con el administrador", Toast.LENGTH_LONG).show();
            Toast.makeText(v.getContext(), "Error "+e, Toast.LENGTH_SHORT).show();
        }
    }
    public void EliminarCarrito(int idBusq, View v)
    {
        try {
            db cU = new db(v.getContext(), "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            //String[] a=new String[]{String.valueOf(id)};
            bd.delete("carrito", "id='" + idBusq+"'", null);
            Toast.makeText(v.getContext(), "Producto eliminado del carrito", Toast.LENGTH_SHORT).show();
            bd.close();
        }
        catch (Exception e)
        {
            Toast.makeText(v.getContext(), "Algo ha salido mal, contactese con el administrador", Toast.LENGTH_LONG).show();
            Toast.makeText(v.getContext(), "Error "+e, Toast.LENGTH_SHORT).show();
        }
    }

}
