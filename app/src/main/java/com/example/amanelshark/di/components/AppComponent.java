package com.example.amanelshark.di.components;


import com.example.amanelshark.model.cardetails.CarDetails;
import com.example.amanelshark.view.AddCarActivity;
import com.example.amanelshark.view.AddWarrantyPeriodActivity;
import com.example.amanelshark.view.DetailsCarActivity;
import com.example.amanelshark.view.DetailsPaymentActivity;
import com.example.amanelshark.view.DetailsServicesActivity;
import com.example.amanelshark.view.LoginActivity;
import com.example.amanelshark.di.modules.ContextModel;
import com.example.amanelshark.di.modules.NetworkModel;
import com.example.amanelshark.view.RegisterActivity;
import com.example.amanelshark.view.ServicesActivity;
import com.example.amanelshark.view.fragment.ProfileFragment;
import com.example.amanelshark.view.fragment.RequestFragment;


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
  void inject(DetailsCarActivity detailsCarActivity);
  void inject(DetailsPaymentActivity detailsPaymentActivity);

  void inject(AddWarrantyPeriodActivity addWarrantyPeriodActivity);
  void injectFragment(ProfileFragment profileFragment);
  void injectFragment(RequestFragment requestFragment);

//  //  void inject(SplashActivity splashActivity);
//    void injectFragment(RequestFragment requestFragment);
//   void injectFragment(TargetFragment targetFragment);








}
