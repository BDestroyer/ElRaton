package com.inacap.elraton.ui.Contacto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inacap.elraton.databinding.FragmentContactoBinding;

public class ContactoFragment extends Fragment {

    private FragmentContactoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContactoViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ContactoViewModel.class);
        binding = FragmentContactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}