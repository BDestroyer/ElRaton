package com.inacap.elraton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.inacap.elraton.ui.LoginActivity;

public class RegistroActivity extends AppCompatActivity {
    EditText nom, ape, email,pass;
    Button regre, inser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        String n="",a="",e="",p="";
        nom=findViewById(R.id.edtNombre);
        n=nom.toString();
        ape=findViewById(R.id.edtApellido);
        a=ape.toString();
        email=findViewById(R.id.edtEmail);
        e=email.toString();
        pass=findViewById(R.id.edtPass);
        p=pass.toString();
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

            }
        });
    }

}