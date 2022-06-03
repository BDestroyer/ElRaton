package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inacap.elraton.R;

public class EntradaAdminActivity extends AppCompatActivity {
    Button btnIngresar, btnModificar, btnEliminar, btnVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_admin);
    btnIngresar=findViewById(R.id.btnAAgregar);
    btnEliminar=findViewById(R.id.btnAEliminar);
    btnModificar=findViewById(R.id.btnAModificar);
    btnVer=findViewById(R.id.btnAVer);
    btnIngresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    btnEliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

        }
    });
    btnModificar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

        }
    });
    btnVer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    }
}