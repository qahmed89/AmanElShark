package com.example.amanelshark.repository;


import com.example.amanelshark.model.addcar.AddCars;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.cardetails.CarDetails;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.center.Centers;
import com.example.amanelshark.model.centerDetails.CenterDetails;
import com.example.amanelshark.model.detailsrequest.DetailsRequest;
import com.example.amanelshark.model.listcars.ListCars;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.notifications.Notifications;
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
import com.example.amanelshark.remote.AmanElSharkServices;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class AmanElsharkRepository {
    private AmanElSharkServices amanElSharkServices;

    @Inject
    public AmanElsharkRepository(AmanElSharkServices amanElSharkServices) {
        this.amanElSharkServices = amanElSharkServices;
    }

    public Observable<Login> Login(String email, String password) {
        return amanElSharkServices.loginRequest(email, password);
    }

    public Observable<Register> Register(String email, String password, String c_password, String phone, String name) {
        return amanElSharkServices.registerRequest(email, password, c_password, phone, name);
    }

    public Single<Brands> Brands(String id) {
        return amanElSharkServices.getbrands(id);
    }

    public Single<Model> Models(String token, String id) {
        return amanElSharkServices.getModels(token, id);
    }

    public Single<Types> Types(String token, String id) {
        return amanElSharkServices.getTypes(token, id);
    }

    public Single<Categories> Categories(String token, String id) {
        return amanElSharkServices.getCategories(token, id);
    }

    public Single<AddCars> AddCars(String token, String model_category_id, String motor_number, String chassis_number, String plate_number, String meter_reading, int year) {
        return amanElSharkServices.addCarsRequest(token, model_category_id, motor_number, chassis_number, plate_number, meter_reading, year);
    }

    public Single<Centers> Centers(String token) {
        return amanElSharkServices.getCenters(token);
    }

    public Single<CenterDetails> CentersDetails(String token, int id) {
        return amanElSharkServices.getCentersDetails(token, id);
    }
    public Single<Years> Years(String token) {
        return amanElSharkServices.getYears(token);
    }

    public Single<WarrantyResponse> Warrenty(String token, Warrenty warranty) {
        return amanElSharkServices.addWarrentyRequest(token, warranty);
    }

    public Single<Packages> Packages(String token) {
        return amanElSharkServices.getPackages(token);
    }

    public Single<Profile> Profile(String token) {
        return amanElSharkServices.getProfile(token);
    }

    public Single<ListCars> CarsList(String token) {
        return amanElSharkServices.getListCars(token);
    }
    public Single<CarDetails> CarDetails(String token,int id) {
        return amanElSharkServices.getcarDetails(token,id);
    }
    public Single<Notifications> Notifications(String token) {
        return amanElSharkServices.getNotifications(token);
    }
    public Single<RequestWarranty> ReqestWarranty(String token, int center_id,int package_id,int client_car_id) {
        return amanElSharkServices.makeWarrentyRequest(token,center_id,package_id,client_car_id);
    }
    public Single<ResponsRequest> ResponsRequest(String token) {
        return amanElSharkServices.getResponsRequest(token);
    }
    public Single<UploadImage> UpladFileRequest(String token, int id, MultipartBody.Part  image) {
        return amanElSharkServices.uploadInvoiceRequest(token,id,image);
    }
    public Single<PackageDetails> PackageDetails(String token, int id) {
        return amanElSharkServices.getPackageDetails(token,id);
    }
    public Single<RequestPayment> RequestPayment(String token, RequestPayment requestPayment) {
        return amanElSharkServices.addPaymentRequest(token,requestPayment);
    }
    public Single<DetailsRequest> DetailsRequest(String token, int id) {
        return amanElSharkServices.getRequestDetails(token,id);
    }
//    public Single<RequestVocation> sendvocationRequestmodelSingle(String type , String description, String from , String to , String id){
    //      return crmServices.sendVocationRequest(type,description,from,to,id);
    // }


}
