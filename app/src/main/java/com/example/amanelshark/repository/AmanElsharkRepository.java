package com.example.amanelshark.repository;


import com.example.amanelshark.remote.AmanElSharkServices;

import javax.inject.Inject;

import io.reactivex.Single;

public class AmanElsharkRepository {
    private AmanElSharkServices amanElSharkServices;

    @Inject
    public AmanElsharkRepository(AmanElSharkServices amanElSharkServices) {
        this.amanElSharkServices = amanElSharkServices;
    }


//    public Single<RequestVocation> sendvocationRequestmodelSingle(String type , String description, String from , String to , String id){
  //      return crmServices.sendVocationRequest(type,description,from,to,id);
   // }



}
