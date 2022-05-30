package com.inacap.elraton.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.inacap.elraton.R;
import com.inacap.elraton.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

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
        loadData();
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)|| super.onSupportNavigateUp();
    }
    private void loadData()
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
}