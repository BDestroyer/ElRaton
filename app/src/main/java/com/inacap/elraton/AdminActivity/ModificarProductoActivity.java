package com.inacap.elraton.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

public class ModificarProductoActivity extends AppCompatActivity {
    TextView txtTitulo, txtDescripcion, txtPrecio, txtCantidad;
    Button btnModificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);
        Metodo x=new Metodo();
        int id;
        db cU = new db(getApplicationContext(), "elRaton.db", null, 1);
        SQLiteDatabase bd = x.Conectar(cU);
        Bundle b=getIntent().getExtras();
        if (b!=null)
        {
            id=b.getInt("id");
        }
        else
        {
            id=0;
        }
        txtTitulo=findViewById(R.id.txtTituloAdmin);
        txtDescripcion=findViewById(R.id.txtDescripcionAdmin);
        txtPrecio=findViewById(R.id.txtPrecioAdmin);
        txtCantidad=findViewById(R.id.txtCantidadAdmin);
        btnModificar=findViewById(R.id.btnModificarCRUD);
        try
        {
            Cursor cursor = bd.rawQuery("select * from producto where id='" + id + "'", null);
            if (cursor.moveToFirst()) {
                txtTitulo.setText(cursor.getString(2));
                txtDescripcion.setText(cursor.getString(3));
                txtPrecio.setText(String.valueOf(cursor.getInt(4)));
                txtCantidad.setText(String.valueOf(cursor.getInt(5)));
            }
            cursor.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }
        int finalId = id;
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txtTitulo.getText().toString().equals("") || txtDescripcion.getText().toString().equals("") || txtPrecio.getText().toString().equals("") || txtCantidad.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Debe rellenar los campos antes de continuar", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Integer.parseInt(txtCantidad.getText().toString()) <= 99 && Integer.parseInt(txtCantidad.getText().toString()) >= 1) {
                            String TituloIng = txtTitulo.getText().toString();
                            String DescripcionIng = txtDescripcion.getText().toString();
                            int PrecioIng = Integer.parseInt(txtPrecio.getText().toString());
                            int CantidadIng = Integer.parseInt(txtCantidad.getText().toString());
                            ContentValues r = new ContentValues();
                            r.put("titulo", TituloIng);
                            r.put("descripcion", DescripcionIng);
                            r.put("precio", PrecioIng);
                            r.put("cantidad", CantidadIng);
                            bd.update("producto", r, "id=?", new String[]{String.valueOf(finalId)});
                            Toast.makeText(getApplicationContext(), "Producto modificado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdministrarProductoActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Debe ingresar una cantidad mayor o igual a 1 y menor a 100", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(ModificarProductoActivity.this, "Error "+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK)
        {
            Intent a=new Intent(getApplicationContext(),EntradaAdminActivity.class);
            startActivity(a);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}