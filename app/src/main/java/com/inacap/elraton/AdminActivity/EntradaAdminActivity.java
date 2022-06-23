package com.inacap.elraton.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.inacap.elraton.R;
import com.inacap.elraton.ui.LoginActivity;

public class  EntradaAdminActivity extends AppCompatActivity {
    Button btnAgregar, btnAdministrar, btnRecomend, btnCerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_admin);
    btnAgregar=findViewById(R.id.btnAAgregar);
    btnAdministrar=findViewById(R.id.btnAVer);
    btnRecomend=findViewById(R.id.btnRecomendaciones);
    btnCerrar=findViewById(R.id.btnCerrarSesionAdmin);
    btnAgregar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent a=new Intent(getApplicationContext(), AgregarProductoActivity.class);
            startActivity(a);
        }
    });
    btnAdministrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent a=new Intent(getApplicationContext(), AdministrarProductoActivity.class);
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
    btnCerrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent a=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(a);
        }
    });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK)
        {
            Intent a=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(a);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}