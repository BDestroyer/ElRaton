package com.inacap.elraton.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.inacap.elraton.R;

public class Carrito extends AppCompatActivity {
    ListView Array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
    Array=findViewById(R.id.ArrayContenedor);
    }
}