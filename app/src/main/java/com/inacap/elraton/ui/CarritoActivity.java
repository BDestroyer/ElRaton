package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapterCarrito;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity
{
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rcv;
    ListAdapterCarrito listAdapterCarrito;
    Button btnComprar;
    TextView txtResumen;
    String correo;
    Metodo x= new Metodo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        btnComprar=findViewById(R.id.btnComprarCarrito);
        swipeRefreshLayout=findViewById(R.id.swipeLayout);
        rcv=findViewById(R.id.rcvCarrito);
        txtResumen=findViewById(R.id.txtResumenCompra);
        Bundle bundle=getIntent().getExtras();

        if(bundle!=null)
        {
            correo=bundle.getString("corr");
        }
        else
        {
            Toast.makeText(this, "Error al obtener datos carrito ", Toast.LENGTH_SHORT).show();
        }
        init(correo);
        txtResumen.setText(String.valueOf(x.calculoResumen(correo, getApplicationContext())));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                swipeRefreshLayout.setRefreshing(false);
                init(correo);
                txtResumen.setText(String.valueOf(x.calculoResumen(correo,getApplicationContext())));
            }
        });
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void init(String correo)
    {
        ArrayList<producto> listaProducto;
        listaProducto=x.cargarCarrito(getApplicationContext(),correo);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        listAdapterCarrito=new ListAdapterCarrito(listaProducto, this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapterCarrito);
    }
}