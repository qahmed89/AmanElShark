package com.example.amanelshark;

import android.app.Application;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import com.example.amanelshark.di.components.AppComponent;
import com.example.amanelshark.di.components.DaggerAppComponent;
import com.example.amanelshark.view.LoginActivity;

import java.util.Locale;


public class BaseApplication extends Application {

    private AppComponent appComponent;
    String mLanguageCode = "ar";


    @Override
    public void onCreate() {
        super.onCreate();
        LocaleHelper.setLocale(getApplicationContext(), mLanguageCode);

        appComponent = DaggerAppComponent.create();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}