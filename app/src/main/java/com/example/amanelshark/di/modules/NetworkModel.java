package com.example.amanelshark.di.modules;


import com.example.amanelshark.remote.AmanElSharkServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module (includes = ViewModelModule.class)
public class NetworkModel {
    @Provides
    @Singleton
    static Retrofit provideRetrofit (){

        return  new Retrofit.Builder()

                .baseUrl("http://192.168.1.173:3200/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
    @Provides
    @Singleton
    static AmanElSharkServices provideUserServices(Retrofit retrofit){
        return retrofit.create(AmanElSharkServices.class);
    }
}
