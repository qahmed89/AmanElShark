package com.example.amanelshark.remote;


import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.register.Register;
import com.example.amanelshark.model.subbrands.SubBreand;
import com.example.amanelshark.model.types.Types;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AmanElSharkServices {
    @POST("login")
    @FormUrlEncoded
    Observable<Login> loginRequest(@Field("email") String email,
                                   @Field("password") String password);
    @POST("register")
    @FormUrlEncoded
    Observable<Register> registerRequest(@Field("email") String email,
                                         @Field("password") String password,
                                         @Field("c_password") String c_password,
                                         @Field("mobile") String phone,
                                         @Field("name") String name);
    @GET("brands")
    Single<Brands> getbrands(@Header("Authorization") String id);

    @GET("brand/{id}/models")
    Single<Model> getModels(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
    @GET("model/{id}/types")
    Single<Types> getTypes(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
    @GET("type/{id}/categories")
    Single<Categories> getCategories(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
}
