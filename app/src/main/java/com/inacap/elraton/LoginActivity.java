package com.inacap.elraton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String admin="admin";
        String contrasenna="admin";
        img=findViewById(R.id.img);
        img.setImageResource(R.drawable.descarga);
    }
    Intent a=new Intent();

}