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
import com.inacap.elraton.RegistroActivity;
import com.inacap.elraton.db;

public class LoginActivity extends AppCompatActivity {
    Button btnIngresar, btnLimpiar;
    TextView redireccion, ingUsuario,ingContrasenna;
    String user,contrasenna,usuarioIng,contraIng;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
        Metodo x= new Metodo();
        SQLiteDatabase basedato=x.Conectar(conexionUsuario);
        ContentValues r=new ContentValues();
        r.put("email", "N.Araya");
        r.put("nombre", "admin");
        r.put("apellido", "admin");
        r.put("contrasenna", "admin");
        r.put("rol", "true");
        long i;
        i=basedato.insert("usuario",null,r);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        redireccion=findViewById(R.id.txtRegistrar);
        ingUsuario=findViewById(R.id.edt_Usuario);
        ingContrasenna=findViewById(R.id.edt_Contraseña);
        btnIngresar=findViewById(R.id.btn_ingresar);
        btnLimpiar=findViewById(R.id.btn_Limpiar);
        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
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
                    Cursor cursor=basedato.rawQuery("select email, contrasenna from usuario where email='"+usuarioIng+"' and contrasenna='"+contraIng+"'",null);
                    if (cursor.moveToFirst())
                    {
                        Cursor cursorAdmin=basedato.rawQuery("select email, contrasenna, rol from usuario where email='"+usuarioIng+"' and contrasenna='"+contraIng+"' and rol='true'",null);
                        if( cursorAdmin.moveToFirst())
                        {
                            //CRUD Admin
                            Toast.makeText(LoginActivity.this, "Weeeeeeeeeeeeeeeeeeeeeena", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent a=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(a);
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Usuario no encontrado, por favor registrese", Toast.LENGTH_SHORT).show();
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
        redireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent a=new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(a);
            }
        });
    }


}