package com.example.amanelshark.remote;


import com.example.amanelshark.model.addcar.AddCars;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.cardetails.CarDetails;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.center.Centers;
import com.example.amanelshark.model.centerDetails.CenterDetails;
import com.example.amanelshark.model.listcars.ListCars;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.packagedetails.PackageDetails;
import com.example.amanelshark.model.packages.Packages;
import com.example.amanelshark.model.profile.Profile;
import com.example.amanelshark.model.register.Register;
import com.example.amanelshark.model.requestpayment.RequestPayment;
import com.example.amanelshark.model.requestwarranty.RequestWarranty;
import com.example.amanelshark.model.responsrequest.ResponsRequest;
import com.example.amanelshark.model.types.Types;
import com.example.amanelshark.model.uploadimage.UploadImage;
import com.example.amanelshark.model.warranty.Warrenty;
import com.example.amanelshark.model.warrantyresponse.WarrantyResponse;
import com.example.amanelshark.model.years.Years;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
                                   @Field("year_id") int year

    );


    @Headers("Content-Type: application/json")
    @POST("warranties")
    Single<WarrantyResponse> addWarrentyRequest(@Header("Authorization") String token,
                                                @Body Warrenty warranty


    );
    @Headers("Content-Type: application/json")
    @POST("payment")
    Single<RequestPayment> addPaymentRequest(@Header("Authorization") String token,
                                              @Body RequestPayment requestPayment


    );
    @POST("requests")
    @FormUrlEncoded
    Single<RequestWarranty> makeWarrentyRequest(@Header("Authorization") String token,
                                                @Field("package_id")int package_id,
                                                @Field("car_center_id")int center_id,
                                                @Field("client_car_id")int client_car_id



    );
    @Multipart
    @POST ("request/{id}/uploadfiles")
    Single<UploadImage> uploadInvoiceRequest(@Header("Authorization") String token,
                                             @Path(value = "id", encoded = true) int id,
                                             @Part MultipartBody.Part file
                                             //  @Part("image") RequestBody name
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
    Single<CenterDetails> getCentersDetails(@Header("Authorization") String token, @Path(value = "id", encoded = true) int id);

    @GET("years")
    Single<Years> getYears(@Header("Authorization") String token);

    @GET("packages")
    Single<Packages> getPackages(@Header("Authorization") String token);

    @GET("profile")
    Single<Profile> getProfile(@Header("Authorization") String token);

    @GET("listCar")
    Single<ListCars> getListCars(@Header("Authorization") String token);
    @GET("listCar/{id}")
    Single<CarDetails> getcarDetails(@Header("Authorization") String token, @Path(value = "id", encoded = true) int id);
    @GET("packages/{id}")
    Single<PackageDetails> getPackageDetails(@Header("Authorization") String token, @Path(value = "id", encoded = true) int id);
    @GET("requests")
    Single<ResponsRequest> getResponsRequest(@Header("Authorization") String token);
}
