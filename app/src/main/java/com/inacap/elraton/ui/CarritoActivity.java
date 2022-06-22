package com.inacap.elraton.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapterCarrito;
import com.inacap.elraton.clase.producto;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity
{
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rcv;
    ListAdapterCarrito listAdapterCarrito;
    Button btnComprar;
    TextView txtResumen;
    Metodo x= new Metodo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        btnComprar=findViewById(R.id.btnComprarCarrito);
        swipeRefreshLayout=findViewById(R.id.swipeLayout);
        rcv=findViewById(R.id.rcvCarrito);
        txtResumen=findViewById(R.id.txtResumenCompra);
        init();
        txtResumen.setText(String.valueOf(x.calculoResumen(getApplicationContext())));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                swipeRefreshLayout.setRefreshing(false);
                init();
                txtResumen.setText(String.valueOf(x.calculoResumen(getApplicationContext())));
            }
        });
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    public void init()
    {
        ArrayList<producto> listaProducto;
        listaProducto=x.cargarCarrito(getApplicationContext());
        rcv.setLayoutManager(new LinearLayoutManager(this));
        listAdapterCarrito=new ListAdapterCarrito(listaProducto, this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapterCarrito);
    }
}