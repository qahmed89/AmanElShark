package com.example.amanelshark.di.components;


import com.example.amanelshark.view.AddCarActivity;
import com.example.amanelshark.view.AddWarrantyPeriodActivity;
import com.example.amanelshark.view.DetailsServicesActivity;
import com.example.amanelshark.view.LoginActivity;
import com.example.amanelshark.di.modules.ContextModel;
import com.example.amanelshark.di.modules.NetworkModel;
import com.example.amanelshark.view.RegisterActivity;
import com.example.amanelshark.view.ServicesActivity;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModel.class, ContextModel.class})
public interface AppComponent {


  //  void inject(MainActivity mainActivity);


  void inject(LoginActivity loginActivity);
  void inject(RegisterActivity registerActivity);

  void inject(AddCarActivity addCarActivity);
  void inject(ServicesActivity servicesActivity);
  void inject(DetailsServicesActivity detailsServicesActivity);
  void inject(AddWarrantyPeriodActivity addWarrantyPeriodActivity);

//  //  void inject(SplashActivity splashActivity);
//    void injectFragment(RequestFragment requestFragment);
//   void injectFragment(TargetFragment targetFragment);








}
