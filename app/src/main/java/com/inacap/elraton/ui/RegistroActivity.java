package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

public class RegistroActivity extends AppCompatActivity {
    EditText nom, ape, email,pass;
    Button regre, inser;
    String n="",a="",e="",p="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nom=findViewById(R.id.edtNombre);
        ape=findViewById(R.id.edtApellido);
        email=findViewById(R.id.edtEmail);
        pass=findViewById(R.id.edtPass);
        regre=findViewById(R.id.btnRegresar);
        inser=findViewById(R.id.btnInsertar);
        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent a=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(a);
            }
        });
        inser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    n=nom.getText().toString();
                    a=ape.getText().toString();
                    e=email.getText().toString();
                    p=pass.getText().toString();
                    if (n.equals("") || a.equals("") || e.equals("") || p.equals(""))
                    {
                        Toast.makeText(RegistroActivity.this, "Debe rellenar todos los campos antes de insertar", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        db conexionUsuario = new db(getApplicationContext(), "elRaton.db", null, 1);
                        Metodo x = new Metodo();
                        SQLiteDatabase basedato = x.Conectar(conexionUsuario);
                        ContentValues r = new ContentValues();
                        r.put("email", e);
                        r.put("nombre", n);
                        r.put("apellido", a);
                        r.put("contrasenna", p);
                        r.put("rol", "false");
                        long i;
                        i = basedato.insert("usuario", null, r);
                        Cursor cursor = basedato.rawQuery("select email, contrasenna from usuario where email='" + e + "' and contrasenna='" + p + "'", null);
                        if (cursor.moveToFirst()) {
                            Toast.makeText(RegistroActivity.this, "Usuario ingresado correctamente", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(a);
                        } else {
                            Toast.makeText(RegistroActivity.this, "El usuario no ha podido ser guardado debido a un error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (SQLException e)
                {
                    Toast.makeText(RegistroActivity.this, "Error "+e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}