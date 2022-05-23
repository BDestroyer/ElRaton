package com.inacap.elraton.ui.Contacto;

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
import androidx.fragment.app.Fragment;

import com.inacap.elraton.R;

public class ContactoFragment extends Fragment {
    private EditText txtNombreContc,txtConsulta,txtCorreoContc;
    private Button btnEnviar;
    String NombreContc,CorreoContc,Consulta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacto, container, false);
    }
    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        txtNombreContc=view.findViewById(R.id.edtNombreContc);
        txtCorreoContc=view.findViewById(R.id.edtCorreoContc);
        txtConsulta=view.findViewById(R.id.edtConsulta);
        btnEnviar=view.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NombreContc=txtNombreContc.getText().toString();
                CorreoContc=txtCorreoContc.getText().toString();
                Consulta=txtConsulta.getText().toString();
                if(!(Consulta.equals("") || NombreContc.equals("")|| CorreoContc.equals("")))
                {
                    Toast.makeText(getContext(), "Datos envíados al administrador", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Debe ingresar los datos válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}