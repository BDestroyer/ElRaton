package com.inacap.elraton.ui.Contacto;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;

import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.db;

public class ContactoFragment extends Fragment {
    private EditText txtConsulta;
    private Button btnEnviar;
    String Consulta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacto, container, false);
    }
    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        txtConsulta=view.findViewById(R.id.edtConsulta);
        btnEnviar=view.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    db conexionUsuario=new db(getContext(),"elRaton.db",null,1);
                    Metodo x=new Metodo();
                    SQLiteDatabase basedato=x.Conectar(conexionUsuario);
                    ContentValues r=new ContentValues();
                    Consulta=txtConsulta.getText().toString();
                    if(!(Consulta.equals("")))
                    {
                        Toast.makeText(getContext(), "Datos envíados al administrador", Toast.LENGTH_SHORT).show();
                        r.put("dato",Consulta);
                        long i;
                        i=basedato.insert("contacto",null,r);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Debe ingresar los datos válidos", Toast.LENGTH_SHORT).show();
                    }
                    txtConsulta.setText("");
                }catch (SQLException ex)
                {
                    Toast.makeText(getContext(), "Error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}