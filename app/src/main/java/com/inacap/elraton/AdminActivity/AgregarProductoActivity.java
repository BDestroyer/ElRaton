package com.inacap.elraton.AdminActivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;
import com.inacap.elraton.ui.LoginActivity;
import com.inacap.elraton.ui.MainActivity;

import java.io.IOException;

public class AgregarProductoActivity extends AppCompatActivity {

    ImageView imgAdd;
    TextView txtTitulo, txtDescripcion, txtPrecio, txtCantidad;
    Button btnInsertarProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        imgAdd = findViewById(R.id.imgAdd);
        txtTitulo = findViewById(R.id.edtTitulo);
        txtDescripcion = findViewById(R.id.edtDescripcion);
        txtPrecio = findViewById(R.id.edtPrecio);
        txtCantidad = findViewById(R.id.edtCantidad);
        btnInsertarProd=findViewById(R.id.btnAgregarCRUD);
        imgAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                cargarImagen();
            }
        });
        btnInsertarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    db conexionUsuario=new db(getApplicationContext(),"elRaton.db",null,1);
                    Metodo x= new Metodo();
                    SQLiteDatabase basedato=x.Conectar(conexionUsuario);
                    String TituloIng=txtTitulo.getText().toString();
                    String DescripcionIng=txtDescripcion.getText().toString();
                    int PrecioIng=Integer.parseInt(txtPrecio.getText().toString());
                    int CantidadIng=Integer.parseInt(txtCantidad.getText().toString());
                    if (TituloIng.equals("") || DescripcionIng.equals("") || PrecioIng==0 ||CantidadIng==0)
                    {
                        Toast.makeText(AgregarProductoActivity.this, "Debe rellenar los campos antes de continuar", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ContentValues r = new ContentValues();
                        r.put("titulo",TituloIng);
                        r.put("descripcion", DescripcionIng);
                        r.put("precio","$"+PrecioIng );
                        r.put("cantidad", CantidadIng);
                        long i;
                        i = basedato.insert("producto", null, r);
                        Toast.makeText(getApplicationContext(), "Producto insertado", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent=new Intent(getApplicationContext(), EntradaAdminActivity.class);
                    startActivity(intent);
                }
                catch (SQLException ex)
                {
                    Toast.makeText(AgregarProductoActivity.this, "Error de la base de datos"+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void cargarImagen() {
        Intent intent=new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(intent);
    }

    ActivityResultLauncher<Intent> launchSomeActivity= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->
    {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null)
                    {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        imgAdd.setImageBitmap(selectedImageBitmap);
                    }
                }
            });

}