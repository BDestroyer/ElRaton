package com.inacap.elraton.ui.MisCompras;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapterAdmin;
import com.inacap.elraton.adapter.ListAdapterCompras;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class MisComprasFragment extends Fragment {

    RecyclerView rcv;
    ArrayList<producto> listaMisCompras;
    ListAdapterCompras listCompras;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
     return inflater.inflate(R.layout.fragment_mis_compras,container,false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        init();

    }

    private void init()
    {
        producto prod;
        listaMisCompras = new ArrayList<>();
        Metodo x=new Metodo();
        db conexionUsuario=new db(getContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        //meter where con email usuario!!!!!!!!!!!!
        Cursor cursor=basedato.rawQuery("select * from miscompras",null);
        if (cursor.moveToFirst())
        {
            do
            {
                prod=new producto();
                Bitmap bmap= BitmapFactory.decodeFile(cursor.getString(1));
                prod.setFoto(bmap);
                prod.setTitulo(cursor.getString(2));
                prod.setPrecio(cursor.getInt(3));
                prod.setDescripcion(cursor.getString(4));
                prod.setCantidad(cursor.getInt(5));
                listaMisCompras.add(prod);
            } while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(getContext(), "Usted aún no tiene compras registradas", Toast.LENGTH_SHORT).show();
        }
        rcv=getView().findViewById(R.id.rcvMisCompras);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        listCompras=new ListAdapterCompras(listaMisCompras, getContext());
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listCompras);
    }
}