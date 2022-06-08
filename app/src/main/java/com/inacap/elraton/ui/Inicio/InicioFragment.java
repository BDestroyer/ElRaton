package com.inacap.elraton.ui.Inicio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapter;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;
import com.inacap.elraton.ui.CarritoActivity;

import java.util.ArrayList;

public class InicioFragment extends Fragment implements SearchView.OnQueryTextListener
{
    RecyclerView rcv;
    FloatingActionButton fab;
    ListAdapter listAdapter;
    SearchView Busqueda;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_inicio,container,false);
    }

    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        fab=view.findViewById(R.id.fab);
        init();
        Busqueda=view.findViewById(R.id.txtBuscar);
        Busqueda.setOnQueryTextListener(this);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent a=new Intent(getContext(), CarritoActivity.class);
                startActivity(a);
            }
        });

    }
    public void init()
    {
        ArrayList<producto> listaProducto;
        Metodo x= new Metodo();
        producto prod=null;
        db conexionUsuario=new db(getContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        listaProducto = new ArrayList<>();
        Cursor cursor=basedato.rawQuery("select * from producto",null);
        if (cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                prod=new producto();
                //prod.setFoto(Integer.parseInt(cursor.getString(1)));
                prod.setTitulo(cursor.getString(2));
                prod.setDescripcion(cursor.getString(3));
                prod.setPrecio(cursor.getInt(4));
                prod.setCantidad(cursor.getInt(5));
                listaProducto.add(prod);
            }
        }
        else
        {
            prod=new producto();
            prod.setFoto(1);
            prod.setTitulo("Ropa");
            prod.setDescripcion("lorem ipsum");
            prod.setPrecio(15000);
            prod.setCantidad(1);
            listaProducto.add(prod);
        }
        rcv=getView().findViewById(R.id.listRecyclerView);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter=new ListAdapter(listaProducto, getContext());
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filtrado(newText);
        return false;
    }
}