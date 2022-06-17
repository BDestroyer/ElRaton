package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapterCarrito;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {
    RecyclerView rcv;
    ListAdapterCarrito listAdapterCarrito;
    Button btnComprar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        btnComprar=findViewById(R.id.btnComprarCarrito);
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
        Cursor c=basedato.rawQuery("select * from carrito",null);
        //Preguntar
        if (c.moveToFirst())
        {
            do {
                int id=c.getInt(0);
                Cursor cursor=basedato.rawQuery("select * from producto where id='"+id+"'",null);
                if (cursor.moveToFirst())
                {
                    do
                    {
                        prod=new producto();
                        Bitmap bmap= BitmapFactory.decodeFile(cursor.getString(1));
                        prod.setFoto(bmap);
                        prod.setTitulo(cursor.getString(2));
                        prod.setDescripcion(cursor.getString(3));
                        prod.setPrecio(cursor.getInt(4));
                        prod.setCantidad(c.getInt(1));
                        listaProducto.add(prod);
                    }while (cursor.moveToNext());
                }
            }while (c.moveToNext());
        }
        else
        {
            Toast.makeText(this, "El carrito de compras se encuentra vacio", Toast.LENGTH_LONG).show();
        }
        rcv=findViewById(R.id.rcvCarrito);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        listAdapterCarrito=new ListAdapterCarrito(listaProducto, this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapterCarrito);
    }
}