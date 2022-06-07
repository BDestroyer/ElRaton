package com.inacap.elraton.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
    RecyclerView rcv;
    ListAdapter listAdapter;
    SearchView Busqueda;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_Inicio, R.id.nav_Mis_Compras, R.id.nav_Contacto).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Busqueda=findViewById(R.id.txtBuscar);
        cargarDatos();

        Busqueda.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.cerrar_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.menuCerrarSesion)
        {
            Intent i=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
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
            nombre = bundle.getString("nombre_completo");
            correo = bundle.getString("correo");
            txtNombr.setText(nombre);
            txtCorr.setText(correo);
        }
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