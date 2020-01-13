package com.example.amanelshark.remote;


import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.subbrands.SubBreand;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AmanElSharkServices {

    @GET("brands")
    Single<Brands> getbrands(@Header("Authorization") String id );
    @GET("sub_brands/{id}")
    Single<SubBreand> getsub_brands(@Header("Authorization") String token , @Path(value = "id", encoded = true) String id);
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
   Observable<Login> loginRequest(@Field("email") String email, @Field("password") String password);
}
