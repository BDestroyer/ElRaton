package com.inacap.elraton.ui.Inicio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapter;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class InicioFragment extends Fragment implements SearchView.OnQueryTextListener
{
    SearchView Busqueda;
    String correo;
    ListAdapter listAdapter;
    RecyclerView rcv;
    ArrayList<producto> listaProducto;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_inicio,container,false);
    }

    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        if (getArguments()!=null)
        {
            correo=getArguments().getString("mail");
        }
        init();
        Busqueda=view.findViewById(R.id.txtBuscar);
        Busqueda.setOnQueryTextListener(this);
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
    public void init()
    {
        producto prod;
        listaProducto = new ArrayList<>();
        Metodo x= new Metodo();
        db conexionUsuario=new db(getContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        Cursor cursor=basedato.rawQuery("select * from producto",null);
        if (cursor.moveToFirst())
        {
            do
            {
                prod=new producto();
                prod.setId(cursor.getInt(0));
                prod.setCorreo(correo);
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
            Toast.makeText(getContext(), "No se han encontrado productos disponibles", Toast.LENGTH_SHORT).show();
        }
        rcv=getView().findViewById(R.id.listRecyclerView);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter=new ListAdapter(listaProducto, getContext());
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapter);
    }
}