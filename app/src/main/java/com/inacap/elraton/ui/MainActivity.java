package com.inacap.elraton.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
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
import com.inacap.elraton.ui.Inicio.InicioFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    FloatingActionButton fab;
    String correo;

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
        Metodo x=new Metodo();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            correo = bundle.getString("correo");
            Fragment f=new InicioFragment();
            Bundle args=new Bundle();
            args.putString("mail",correo);
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.relativeLayout2,f).addToBackStack(null).commit();
        }
        else
        {
            Toast.makeText(this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
        }
        x.cargarDatosCliente(bundle, binding);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle bun=new Bundle();
                Intent a=new Intent(getApplicationContext(), CarritoActivity.class);
                bun.putString("corr",correo);
                a.putExtras(bun);
                startActivity(a);
            }
        });
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
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("¿Desea cerrar la sesión?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)|| super.onSupportNavigateUp();
    }

}