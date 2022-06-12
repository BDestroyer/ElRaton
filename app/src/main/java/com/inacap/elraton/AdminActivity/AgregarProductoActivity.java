package com.inacap.elraton.AdminActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

import java.io.File;

public class AgregarProductoActivity extends AppCompatActivity {

    private static final int COD_SELECCIONA = 10;
    final String relativeLocation = Environment.DIRECTORY_DCIM + File.separator+"imgElRaton"+File.separator;
    ImageView imgAdd;
    TextView txtTitulo, txtDescripcion, txtPrecio, txtCantidad;
    Button btnInsertarProd;
    String nombre=obtenerNombre();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        imgAdd = findViewById(R.id.imgAdd);
        txtTitulo = findViewById(R.id.edtTitulo);
        txtDescripcion = findViewById(R.id.edtDescripcion);
        txtPrecio = findViewById(R.id.edtPrecio);
        txtCantidad = findViewById(R.id.edtCantidad);
        btnInsertarProd = findViewById(R.id.btnAgregarCRUD);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mostrarDialog();
            }
        });

        btnInsertarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db conexionUsuario = new db(getApplicationContext(), "elRaton.db", null, 1);
                    Metodo x = new Metodo();
                    SQLiteDatabase basedato = x.Conectar(conexionUsuario);
                    if (txtTitulo.getText().toString().equals("") || txtDescripcion.getText().toString().equals("") || txtPrecio.getText().toString().equals("") || txtCantidad.getText().toString().equals("")) {
                        Toast.makeText(AgregarProductoActivity.this, "Debe rellenar los campos antes de continuar", Toast.LENGTH_SHORT).show();
                    } else {
                        String ruta= relativeLocation+nombre;
                        String TituloIng = txtTitulo.getText().toString();
                        String DescripcionIng = txtDescripcion.getText().toString();
                        int PrecioIng = Integer.parseInt(txtPrecio.getText().toString());
                        int CantidadIng = Integer.parseInt(txtCantidad.getText().toString());
                        ContentValues r = new ContentValues();
                        r.put("rutaImg", ruta);
                        r.put("titulo", TituloIng);
                        r.put("descripcion", DescripcionIng);
                        r.put("precio", PrecioIng);
                        r.put("cantidad", CantidadIng);
                        long i;
                        i = basedato.insert("producto", null, r);
                        Toast.makeText(getApplicationContext(), "Producto insertado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), EntradaAdminActivity.class);
                        startActivity(intent);
                    }
                } catch (SQLException ex) {
                    Toast.makeText(AgregarProductoActivity.this, "Error de la base de datos  " + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mostrarDialog()
    {
        final CharSequence[] opciones={"Elegir de la galería","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(AgregarProductoActivity.this);
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    if (opciones[which].equals("Elegir de la galería"))
                    {
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }
                    else
                    {
                        dialog.dismiss();
                    }
                }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case COD_SELECCIONA:
                Uri miPath = data.getData();
                imgAdd.setImageURI(miPath);
                imgAdd.setDrawingCacheEnabled(true);
                Bitmap bitmap = imgAdd.getDrawingCache();
                try
                {
                    //File file= new File();
                    //file =
                    MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, nombre, null);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                //https://stackoverflow.com/questions/56904485/how-to-save-an-image-in-android-q-using-mediastore
                //https://stackoverflow.com/questions/59511147/create-copy-file-in-android-q-using-mediastore
                break;
        }
    }

    private String obtenerNombre()
    {
        Long consecutivo= System.currentTimeMillis()/1000000;
        String nombre=consecutivo.toString()+".jpg";
        return nombre;
    }
}