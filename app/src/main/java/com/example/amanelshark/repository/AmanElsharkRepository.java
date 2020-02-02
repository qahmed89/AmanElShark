package com.example.amanelshark.repository;


import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.register.Register;
import com.example.amanelshark.model.subbrands.SubBreand;
import com.example.amanelshark.model.types.Types;
import com.example.amanelshark.remote.AmanElSharkServices;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AmanElsharkRepository {
    private AmanElSharkServices amanElSharkServices;

    @Inject
    public AmanElsharkRepository(AmanElSharkServices amanElSharkServices) {
        this.amanElSharkServices = amanElSharkServices;
    }
public Observable<Login> Login (String email , String password){
        return amanElSharkServices.loginRequest(email,password);
}
    public Observable<Register> Register (String email , String password,String c_password,String phone,String name){
        return amanElSharkServices.registerRequest(email,password,c_password,phone,name);
    }
    public Single<Brands> Brands (String id ){
        return amanElSharkServices.getbrands(id);
    }
    public Single<Model> Models (String token, String id ){
        return amanElSharkServices.getModels(token,id);
    }
    public Single<Types> Types (String token, String id ){
        return amanElSharkServices.getTypes(token,id);
    }
    public Single<Categories> Categories (String token, String id ){
        return amanElSharkServices.getCategories(token,id);
    }

//    public Single<RequestVocation> sendvocationRequestmodelSingle(String type , String description, String from , String to , String id){
  //      return crmServices.sendVocationRequest(type,description,from,to,id);
   // }



}
