package com.inacap.elraton.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

import java.util.ArrayList;
import java.util.List;

public class RecomendacionesAdminActivity extends AppCompatActivity {
    ListView ArrayMostrar;
    SQLiteDatabase basedato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones_admin);
        ArrayMostrar=findViewById(R.id.ArrayRecomendaciones);
        List b=LlenarListado();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,b);
        ArrayMostrar.setAdapter(adapter);
    }

    private List LlenarListado()
    {
        db ConexionUsuario=new db(getApplicationContext(),"elRaton.db",null, 1);
        Metodo x=new Metodo();
        basedato=x.Conectar(ConexionUsuario);
        ArrayList<String> Mostrar=new ArrayList<>();
        Cursor cursor=basedato.rawQuery("select * from contacto",null);
        if (cursor.moveToFirst())
        {
            do
            {
                Mostrar.add(cursor.getString(3));
            }while (cursor.moveToNext());
        }
        else
        {
            Mostrar.add("No se han encontrado resultados");
        }
        return Mostrar;
    }
}