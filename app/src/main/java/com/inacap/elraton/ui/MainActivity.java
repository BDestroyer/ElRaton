package com.inacap.elraton.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.adapter.ListAdapter;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.databinding.ActivityMainBinding;
import com.inacap.elraton.db;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    SearchView txtBuscar;
    RecyclerView rcv;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_Inicio, R.id.nav_Buscar, R.id.nav_Contacto).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        cargarDatos();
        init();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        txtBuscar=findViewById(R.id.SearchView);
        //txtBuscar.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)|| super.onSupportNavigateUp();
    }
    public void cargarDatos()
    {
        String nombre, correo;
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            View vistaHeader=binding.navView.getHeaderView(0);
            final TextView txtNombr = vistaHeader.findViewById(R.id.txtNombre);
            final TextView txtCorr = vistaHeader.findViewById(R.id.txtCorreo);
            nombre = bundle.getString("nombre completo");
            correo = bundle.getString("correo");
            txtNombr.setText(nombre);
            txtCorr.setText(correo);
        }
    }
/*
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }*/

    public void init()
    {
        ArrayList<producto> listaProducto;
        Metodo x= new Metodo();
        producto prod=null;
        db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        listaProducto = new ArrayList<>();
        Cursor cursor=basedato.rawQuery("select * from producto",null);
        while (cursor.moveToNext())
        {
            prod=new producto();
            prod.setId(cursor.getInt(0));
            prod.setTitulo(cursor.getString(1));
            prod.setDescripcion(cursor.getString(2));
            prod.setPrecio(+cursor.getInt(3));
            prod.setCantidad(cursor.getInt(4));
            listaProducto.add(prod);
        }
        rcv=findViewById(R.id.listRecyclerView);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        listAdapter=new ListAdapter(listaProducto, this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(listAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        listAdapter.filtrado(newText);
        return false;
    }
}