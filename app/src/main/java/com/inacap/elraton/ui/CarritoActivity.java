package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapterAdmin;
import com.inacap.elraton.adapter.ListAdapterCarrito;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {
    RecyclerView rcv;
    ListAdapterCarrito listAdapterCarrito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        init();
    }
    public void init()
    {
        ArrayList<producto> listaProducto;
        Metodo x= new Metodo();
        producto prod=null;
        db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        listaProducto = new ArrayList<>();
        Cursor cursor=basedato.rawQuery("select * from carrito",null);
        if (cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                prod=new producto();
                prod.setId(cursor.getInt(0));
                //prod.setFoto(Integer.parseInt(cursor.getString(1)));
                prod.setTitulo(cursor.getString(2));
                prod.setDescripcion(cursor.getString(3));
                prod.setPrecio(+cursor.getInt(4));
                prod.setCantidad(cursor.getInt(5));
                listaProducto.add(prod);
            }
        }
        else
        {
            Toast.makeText(this, "No se han encontrado productos en el carrito de compras", Toast.LENGTH_SHORT).show();
        }
        rcv=findViewById(R.id.rcvCarrito);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        listAdapterCarrito=new ListAdapterCarrito(listaProducto, this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapterCarrito);
    }
}