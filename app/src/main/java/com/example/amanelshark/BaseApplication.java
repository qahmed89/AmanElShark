package com.example.amanelshark;

import android.app.Application;

import com.example.amanelshark.di.components.AppComponent;
import com.example.amanelshark.di.components.DaggerAppComponent;


public class BaseApplication extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}