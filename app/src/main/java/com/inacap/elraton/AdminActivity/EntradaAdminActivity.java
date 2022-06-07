package com.inacap.elraton.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inacap.elraton.R;

public class EntradaAdminActivity extends AppCompatActivity {
    Button btnIngresar, btnModificar, btnEliminar, btnVer, btnRecomend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_admin);
    btnIngresar=findViewById(R.id.btnAAgregar);
    btnEliminar=findViewById(R.id.btnAEliminar);
    btnModificar=findViewById(R.id.btnAModificar);
    btnVer=findViewById(R.id.btnAVer);
    btnRecomend=findViewById(R.id.btnRecomendaciones);
    btnIngresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent a=new Intent(getApplicationContext(), AgregarProductoActivity.class);
            startActivity(a);
        }
    });
    btnEliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent a=new Intent(getApplicationContext(), EliminarProductoActivity.class);
            startActivity(a);
        }
    });
    btnModificar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent a=new Intent(getApplicationContext(), ModificarProductoActivity.class);
            startActivity(a);
        }
    });
    btnVer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent a=new Intent(getApplicationContext(), VerProductoActivity.class);
            startActivity(a);
        }
    });
    btnRecomend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent a=new Intent(getApplicationContext(), RecomendacionesAdminActivity.class);
            startActivity(a);
        }
    });
    }
}