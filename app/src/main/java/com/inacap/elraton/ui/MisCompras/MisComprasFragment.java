package com.inacap.elraton.ui.MisCompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inacap.elraton.R;

public class MisComprasFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
     return inflater.inflate(R.layout.fragment_buscar,container,false);
    }
}