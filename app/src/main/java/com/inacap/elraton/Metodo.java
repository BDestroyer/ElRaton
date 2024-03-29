package com.inacap.elraton;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.clase.producto;
import com.inacap.elraton.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class Metodo
{

    public void truncarTablaCarrito(Context a)
    {
        try {
            db cU = new db(a, "elRaton.db", null, 1);
            SQLiteDatabase basedato = Conectar(cU);
            basedato.execSQL("delete from carrito");
        }
        catch (Exception e)
        {
            Toast.makeText(a, "Error "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public SQLiteDatabase Conectar(db cn)
    {
        return cn.getWritableDatabase();
    }

    public void AgregarACarrito(int cantidad, View v, int id)
    {
        try {
            db cU = new db(v.getContext(), "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            Cursor cursor = bd.rawQuery("select * from carrito where id='"+id+"'", null);
            if (cursor.moveToFirst())
            {
                Toast.makeText(v.getContext(), "Producto ya existe en el carrito", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Cursor c=bd.rawQuery("select precio from producto where id='"+id+"'",null);
                if (c.moveToFirst())
                {
                    int precio=c.getInt(0);
                    ContentValues r = new ContentValues();
                    r.put("id", id);
                    r.put("cantidad", cantidad);
                    r.put("valorTotal",precio*cantidad);
                    bd.insert("carrito", null, r);
                    Toast.makeText(v.getContext(), "Producto agregado correctamente al carrito", Toast.LENGTH_SHORT).show();
                    bd.close();
                }
                else
                {
                    Toast.makeText(v.getContext(), "Kie", Toast.LENGTH_SHORT).show();
                }
                c.close();
            }
            cursor.close();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(v.getContext(), "Algo ha salido mal, contactese con el administrador", Toast.LENGTH_LONG).show();
            Toast.makeText(v.getContext(), "Error " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public void EliminarCarrito(int idBusq, View v) {
        try {
            db cU = new db(v.getContext(), "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            bd.execSQL("delete from carrito where id='" + idBusq + "'");
            Toast.makeText(v.getContext(), "Producto eliminado del carrito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(v.getContext(), "Algo ha salido mal, contactese con el administrador", Toast.LENGTH_LONG).show();
            Toast.makeText(v.getContext(), "Error " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList cargarCarrito(Context a)
    {
        ArrayList<producto> listaProducto;
        producto prod;
        db conexionUsuario = new db(a, "elRaton.db", null, 1);
        SQLiteDatabase basedato = Conectar(conexionUsuario);
        listaProducto = new ArrayList<>();
        Cursor c = basedato.rawQuery("select * from carrito", null);
        if (c.moveToFirst())
        {
            do {
                int id = c.getInt(0);
                Cursor cursor = basedato.rawQuery("select * from producto where id='" + id + "'", null);
                if (cursor.moveToFirst()) {
                    do {
                        prod = new producto();
                        prod.setId(cursor.getInt(0));
                        Bitmap bmap = BitmapFactory.decodeFile(cursor.getString(1));
                        prod.setFoto(bmap);
                        prod.setTitulo(cursor.getString(2));
                        prod.setDescripcion(cursor.getString(3));
                        prod.setPrecio(cursor.getInt(4));
                        int pre = cursor.getInt(4);
                        prod.setCantidad(c.getInt(1));
                        int cant = c.getInt(1);
                        prod.setPrecioTotal(pre * cant);
                        listaProducto.add(prod);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            } while (c.moveToNext());
            c.close();
        } else {
            Toast.makeText(a, "El carrito de compras se encuentra vacio", Toast.LENGTH_LONG).show();
        }
        return listaProducto;
    }

    public void cargarDatosCliente(Bundle bundle, ActivityMainBinding binding) {
        String nombre, correo;
        if (bundle != null)
        {
            View vistaHeader = binding.navView.getHeaderView(0);
            final TextView txtNombr = vistaHeader.findViewById(R.id.txtNombre);
            final TextView txtCorr = vistaHeader.findViewById(R.id.txtCorreo);
            nombre = bundle.getString("nombre_completo");
            correo = bundle.getString("correo");
            txtNombr.setText(nombre);
            txtCorr.setText(correo);
        }
    }

    public int calculoResumen(Context v)
    {
        int valor;
        int acum=0;
        try
        {
            db cU = new db(v, "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            Cursor cursor = bd.rawQuery("select valorTotal from carrito", null);
            if (cursor.moveToFirst())
            {
                do
                {
                    valor=cursor.getInt(0);
                    acum=acum+valor;
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (SQLiteException exception)
        {
            Toast.makeText(v, "Error "+exception, Toast.LENGTH_SHORT).show();
        }
        return acum;
    }

    public void truncarTablaMisCompras(Context a)
    {
        try {
            db cU = new db(a, "elRaton.db", null, 1);
            SQLiteDatabase basedato = Conectar(cU);
            basedato.delete("miscompras", null,null);
        }catch (Exception e)
        {
            Toast.makeText(a, "Error "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public void EliminarProductoAdmin(int id, Context v)
    {
        try {
            db cU = new db(v.getApplicationContext(), "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            bd.execSQL("delete from producto where id='" + id + "'");
            Toast.makeText(v.getApplicationContext(), "Producto eliminado del carrito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(v.getApplicationContext(), "Algo ha salido mal, contactese con el administrador", Toast.LENGTH_LONG).show();
            Toast.makeText(v.getApplicationContext(), "Error " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public void AgregarAMisCompras(Context a, int valor)
    {
        try
        {
            db cU = new db(a, "elRaton.db", null, 1);
            SQLiteDatabase bd = Conectar(cU);
            String rutaImagen;
            String titulo;
            String descripcion;
            int precioUnitario;
            Cursor cursor=bd.rawQuery("select * from carrito",null);
            if (cursor.moveToFirst())
            {
                do
                {
                    int id=cursor.getInt(0);
                    int cantidad=cursor.getInt(1);
                    Cursor c=bd.rawQuery("select * from producto where id='"+id+"'",null);
                    if (c.moveToFirst())
                    {
                        do
                        {
                            rutaImagen=c.getString(1);
                            titulo=c.getString(2);
                            descripcion=c.getString(3);
                            precioUnitario=c.getInt(4);
                            ContentValues r=new ContentValues();
                            r.put("id",id);
                            r.put("rutaImg",rutaImagen);
                            r.put("nombreProducto",titulo);
                            r.put("precioUnitarioProducto",precioUnitario);
                            r.put("precioTotalProducto", valor);
                            r.put("DescripcionProducto",descripcion);
                            r.put("cantidadProducto",cantidad);
                            bd.insert("miscompras",null,r);
                        }while (c.moveToNext());
                    }
                    c.close();
                }while (cursor.moveToNext());
            }
            cursor.close();
            Toast.makeText(a, "Producto comprado, puede verlo en Mis Compras", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(a, "Algo ha salido mal, contactese con el administrador", Toast.LENGTH_LONG).show();
            Toast.makeText(a, "Error " + e, Toast.LENGTH_SHORT).show();
        }
    }
}
