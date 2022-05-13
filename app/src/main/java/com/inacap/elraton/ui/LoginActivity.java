package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.R;

public class LoginActivity extends AppCompatActivity {
    ImageView img;
    Button btnIngresar, btnLimpiar;
    TextView ingUsuario,ingContrasenna;
    String user="admin",contrasenna="admin",usuarioIng,contraIng;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                usuarioIng=ingUsuario.getText().toString();
                contraIng=ingContrasenna.getText().toString();
                if (usuarioIng.equals("")||contraIng.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Debe rellenar los campos antes de continuar", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (usuarioIng.equals(user) && contraIng.equals(contrasenna))
                    {
                        //aqui redireccion a CRUD productos
                        Toast.makeText(LoginActivity.this, "admin", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "usuario", Toast.LENGTH_LONG).show();
                        Intent a = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(a);
                    }
                }

            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ingUsuario.setText("");
                ingContrasenna.setText("");
                ingUsuario.requestFocus();
            }
        });
    }


}