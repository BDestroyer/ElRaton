package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.AdminActivity.EntradaAdminActivity;
import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

public class LoginActivity extends AppCompatActivity {
    Button btnIngresar;
    TextView redireccion, ingUsuario,ingContrasenna;
    String usuarioIng,contraIng;
    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Metodo x= new Metodo();
        verificarPermisos();
        x.truncarTablaCarrito(getApplicationContext());
        x.truncarTablaMisCompras(getApplicationContext());
        db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        Cursor cursor=basedato.rawQuery("select email from usuario",null);
        if (!(cursor.moveToFirst()))
        {
            ContentValues r=new ContentValues();
            r.put("email", "admin");
            r.put("nombre", "admin");
            r.put("apellido", "admin");
            r.put("contrasenna", "admin");
            r.put("rol", "true");
            basedato.insert("usuario",null,r);
        }
        cursor.close();
        redireccion=findViewById(R.id.txtRegistrar);
        ingUsuario=findViewById(R.id.edt_Usuario);
        ingContrasenna=findViewById(R.id.edt_Contraseña);
        btnIngresar=findViewById(R.id.btn_ingresar);
        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    if (ingUsuario.getText().toString().equals("") || ingContrasenna.getText().toString().equals(""))
                    {
                        Toast.makeText(LoginActivity.this, "Debe rellenar los campos antes de continuar", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        usuarioIng=ingUsuario.getText().toString();
                        contraIng=ingContrasenna.getText().toString();
                        Cursor cursor=basedato.rawQuery("select email, contrasenna from usuario where email='"+usuarioIng+"' and contrasenna='"+contraIng+"'",null);
                        if (cursor.moveToFirst())
                        {
                            cursor.close();
                            Cursor cursorAdmin=basedato.rawQuery("select email, contrasenna, rol from usuario where email='"+usuarioIng+"' and contrasenna='"+contraIng+"' and rol='true'",null);
                            if( cursorAdmin.moveToFirst())
                            {
                                cursorAdmin.close();
                                Intent a=new Intent(getApplicationContext(), EntradaAdminActivity.class);
                                startActivity(a);
                            }
                            else
                            {
                                cursor=basedato.rawQuery("select email, nombre, apellido from usuario where email='"+usuarioIng+"'",null);
                                if (cursor.moveToFirst())
                                {
                                    do
                                    {
                                        String email=cursor.getString(0);
                                        String nom=cursor.getString(1);
                                        String ape=cursor.getString(2);
                                        bundle.putString("nombre_completo",nom+" "+ape);
                                        bundle.putString("correo",email);
                                        Intent a=new Intent(getApplicationContext(), MainActivity.class);
                                        a.putExtras(bundle);
                                        startActivity(a);
                                    }while (cursor.moveToNext());
                                    cursor.close();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Usuario no encontrado, por favor registrese", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                catch (SQLException ex)
                {
                    Toast.makeText(LoginActivity.this, "Error de la base de datos"+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
        redireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent a=new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(a);
                finish();
            }
        });
    }

    private void verificarPermisos()
    {
        int permisoReadExternal = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permisoReadExternal == PackageManager.PERMISSION_DENIED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
    }
}