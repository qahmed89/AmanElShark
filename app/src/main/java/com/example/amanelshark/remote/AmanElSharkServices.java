package com.example.amanelshark.remote;


import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.login.Login;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AmanElSharkServices {

    @GET("brands")
    Single<Brands> getbrands(@Header("Authorization") String id );
    @GET("sub_brands")
    Single<Brands> getbrands(@Header("Authorization") String token , String id_brands);
//    @POST     ("requests")
//   @FormUrlEncoded
//    Single<RequestVocation> sendVocationRequest(@Field("type") String type, @Field("description") String description, @Field("from") String from, @Field("to") String to, @Field("employee_id") String token);
//    @POST("checkIn")
//    @FormUrlEncoded
//    Single<CheckIn> sendcheckin(@Header("Authorization") String id, @Field("date") String date);
//    @POST("checkOut")
//    @FormUrlEncoded
//    Single<CheckOut> sendcheckout(@Header("Authorization") String id, @Field("date") String date);
   @POST("login/mobile")
  @FormUrlEncoded
   Single<Login> loginRequest(@Field("email") String email, @Field("password") String password);
}
