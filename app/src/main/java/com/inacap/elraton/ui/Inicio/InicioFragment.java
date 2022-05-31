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

        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));
        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));
        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));
        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));
        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));
        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));
        elements.add(new producto("ola","$"+"10000","1","Este es  un producto muy bueno pq ola que talca muy buenas jajsja "));

        ListAdapter listAdapter=new ListAdapter(elements, getContext());
        RecyclerView recyclerView=getView().findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }
}