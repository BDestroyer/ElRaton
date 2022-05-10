package com.inacap.elraton.ui.buscar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class buscarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public buscarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Buscar fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}