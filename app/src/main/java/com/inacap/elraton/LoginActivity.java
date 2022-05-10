package com.inacap.elraton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ImageView img;
    Button btnIngresar, btnLimpiar;
    TextView ingUsuario,ingContrasenna;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent a=new Intent();
        String user="admin";
        String contrasenna="admin";
        ingUsuario=findViewById(R.id.edt_Usuario);
        ingContrasenna=findViewById(R.id.edt_Contraseña);
        img=findViewById(R.id.img);
        img.setImageResource(R.drawable.descarga);
        btnIngresar=findViewById(R.id.btn_ingresar);
        btnLimpiar=findViewById(R.id.btn_Limpiar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                    if (ingUsuario.getText().toString().equals(user)&&ingContrasenna.getText().toString().equals(contrasenna))
                    {
                        Toast.makeText(LoginActivity.this, "admin", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "usuario", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }


}