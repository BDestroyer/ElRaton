package com.inacap.elraton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    ImageView img;
    Button btnIngresar, btnLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String admin="admin1";
        String contrasenna="admin";
        img=findViewById(R.id.img);
        img.setImageResource(R.drawable.descarga);
        btnIngresar=findViewById(R.id.btn_ingresar);
        btnLimpiar=findViewById(R.id.btn_Limpiar);
    }
    Intent a=new Intent();

}