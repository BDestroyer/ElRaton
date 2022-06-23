package com.inacap.elraton.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapterAdmin;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class AdministrarProductoActivity extends AppCompatActivity {
    RecyclerView rcv;
    ListAdapterAdmin listAdapter;
    SwipeRefreshLayout srl;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_producto);
        init();
        srl=findViewById(R.id.srl);
        btnRegresar=findViewById(R.id.btnBack);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });

    }

    public void init()
    {
        ArrayList<producto> listaProducto;
        Metodo x= new Metodo();
        producto prod;
        db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        listaProducto = new ArrayList<>();
        Cursor cursor=basedato.rawQuery("select * from producto",null);
        if (cursor.moveToFirst())
        {
            do
            {
                prod=new producto();
                prod.setId(cursor.getInt(0));
                Bitmap bmap= BitmapFactory.decodeFile(cursor.getString(1));
                prod.setFoto(bmap);
                prod.setTitulo(cursor.getString(2));
                prod.setDescripcion(cursor.getString(3));
                prod.setPrecio(cursor.getInt(4));
                prod.setCantidad(cursor.getInt(5));
                listaProducto.add(prod);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(this, "No se han encontrado productos en la base de datos", Toast.LENGTH_SHORT).show();
        }
        rcv=findViewById(R.id.RecyclerView);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        listAdapter=new ListAdapterAdmin(listaProducto, this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapter);
    }
}