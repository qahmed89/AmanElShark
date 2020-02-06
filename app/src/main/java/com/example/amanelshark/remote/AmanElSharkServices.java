package com.example.amanelshark.remote;


import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.RawRes;

import com.example.amanelshark.R;
import com.example.amanelshark.model.addcar.AddCars;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.center.Centers;
import com.example.amanelshark.model.centerDetails.CenterDetails;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.register.Register;
import com.example.amanelshark.model.types.Types;
import com.example.amanelshark.model.warranty.Warranty;
import com.example.amanelshark.model.warranty.Warrenty;
import com.example.amanelshark.model.warrantyresponse.WarrantyResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
    @POST("addCar")
    @FormUrlEncoded
    Single<AddCars> addCarsRequest(@Header("Authorization") String token,
                                   @Field("model_category_id") String model_category_id,
                                        @Field("motor_number") String motor_number,
                                        @Field("chassis_number") String chassis_number,
                                        @Field("plate_number") String plate_number,
                                        @Field("meter_reading") String meter_reading,
                                         @Field("year") String year

    );
    @Headers("Content-Type: application/json")
    @POST("warranties")
    Single<WarrantyResponse> addWarrentyRequest(@Header("Authorization") String token,
                                            @Body Warrenty warranty


    );
    @GET("brands")
    Single<Brands> getbrands(@Header("Authorization") String id);
    @GET("brand/{id}/models")
    Single<Model> getModels(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
    @GET("model/{id}/types")
    Single<Types> getTypes(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
    @GET("type/{id}/categories")
    Single<Categories> getCategories(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
    @GET("centers")
    Single<Centers> getCenters(@Header("Authorization") String token);
    @GET("centers/{id}")
    Single<CenterDetails> getCentersDetails(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);
}
