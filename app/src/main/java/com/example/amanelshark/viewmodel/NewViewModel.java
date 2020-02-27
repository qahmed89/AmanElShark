package com.example.amanelshark.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amanelshark.remote.AmanElSharkServices;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NewViewModel extends ViewModel {
    private AmanElSharkServices amanElSharkServices;
    Context context;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    @Inject
    public NewViewModel(AmanElSharkServices amanElSharkServices) {
        this.amanElSharkServices = amanElSharkServices;
    }

}
