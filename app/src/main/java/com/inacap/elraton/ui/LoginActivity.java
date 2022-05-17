package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

public class LoginActivity extends AppCompatActivity {
    ImageView img;
    Button btnIngresar, btnLimpiar;
    TextView ingUsuario,ingContrasenna;
    String user="admin",contrasenna="admin",usuarioIng,contraIng;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
        Metodo x= new Metodo();
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        ContentValues r=new ContentValues();
        r.put("email","N.Araya@gmail.com");
        r.put("nombre",user);
        r.put("apellido",user);
        r.put("contrasenna",contrasenna);
        long i;
        i=basedato.insert("usuario",null,r);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        img=findViewById(R.id.img);
        img.setImageResource(R.drawable.descarga);
        ingUsuario=findViewById(R.id.edt_Usuario);
        ingContrasenna=findViewById(R.id.edt_Contraseña);
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
                    Cursor cursor=basedato.rawQuery("select email, contrasenna from usuario",null);
                    if (cursor.moveToFirst())
                    {
                        Toast.makeText(LoginActivity.this, "asdasdasd", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "ctm", Toast.LENGTH_SHORT).show();
                    }
                    if (usuarioIng.equals(user) && contraIng.equals(contrasenna))
                    {
                        //aqui redirección a CRUD productos
                        Toast.makeText(LoginActivity.this, "admin", Toast.LENGTH_LONG).show();
                        //Intent a = new Intent(getApplicationContext(), MainActivity.class);
                        //startActivity(a);
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