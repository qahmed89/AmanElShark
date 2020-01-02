package com.example.amanelshark.repository;


import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.remote.AmanElSharkServices;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AmanElsharkRepository {
    private AmanElSharkServices amanElSharkServices;

    @Inject
    public AmanElsharkRepository(AmanElSharkServices amanElSharkServices) {
        this.amanElSharkServices = amanElSharkServices;
    }
public Single<Login> Login ( String email , String password){
        return amanElSharkServices.loginRequest(email,password);
}
    public Single<Brands> Brands (String id ){
        return amanElSharkServices.getbrands(id);
    }
//    public Single<RequestVocation> sendvocationRequestmodelSingle(String type , String description, String from , String to , String id){
  //      return crmServices.sendVocationRequest(type,description,from,to,id);
   // }



}
