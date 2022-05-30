package com.inacap.elraton.ui.Inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inacap.elraton.ListAdapter;
import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.ui.CarritoActivity;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment
{
    FloatingActionButton fab;
    List<producto> elements;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_inicio,container,false);
    }
    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        fab=view.findViewById(R.id.fab);
        init();
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

        elements = new ArrayList<>();
        elements.add(new producto("este es un uno","15000", "1", "#775447"));
        elements.add(new producto("este es un dos","5000", "2", "#705447"));
        elements.add(new producto("este es un tres","1000", "1", "#775007"));
        elements.add(new producto("este es un cuatro","14000", "1", "#d55447"));
        elements.add(new producto("este es un cinco","1000", "1", "#77d447"));

        ListAdapter listAdapter=new ListAdapter(elements, getContext());
        RecyclerView recyclerView=getView().findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }
}