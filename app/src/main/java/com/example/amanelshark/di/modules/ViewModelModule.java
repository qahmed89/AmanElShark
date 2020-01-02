package com.example.amanelshark.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.amanelshark.di.ViewModelKey;
import com.example.amanelshark.remote.AmanElSharkServices;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.example.amanelshark.viewmodel.NewViewModel;
import com.example.amanelshark.viewmodel.ViewModelFactory;


import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AmanElsharkViewModel.class)


    abstract ViewModel bindViewModel(AmanElsharkViewModel amanElsharkViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(NewViewModel.class)
    abstract ViewModel bindViewModel1(NewViewModel newViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);
}
