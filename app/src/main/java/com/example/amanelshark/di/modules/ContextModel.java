package com.example.amanelshark.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ContextModel {
    private final Context context;

    public ContextModel(Context context) {
        this.context = context;
    }
    @Binds
    abstract Context bindContext (Application application);

}
