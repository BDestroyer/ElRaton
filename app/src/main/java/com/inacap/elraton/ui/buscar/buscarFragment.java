package com.inacap.elraton.ui.buscar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inacap.elraton.databinding.FragmentBuscarBinding;

public class buscarFragment extends Fragment {

    private FragmentBuscarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscarViewModel BuscarViewModel = new ViewModelProvider(this).get(buscarViewModel.class);
        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}