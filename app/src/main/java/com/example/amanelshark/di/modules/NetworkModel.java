package com.example.amanelshark.di.modules;


import android.provider.SyncStateContract;

import com.example.amanelshark.remote.AmanElSharkServices;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class NetworkModel {
    @Provides
    @Singleton
    static Retrofit provideRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return new Retrofit.Builder()

                .baseUrl("http://192.168.1.172:5002/api/client/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    @Provides
    @Singleton
    static AmanElSharkServices provideUserServices(Retrofit retrofit) {
        return retrofit.create(AmanElSharkServices.class);
    }
}
