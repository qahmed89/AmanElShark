package com.example.amanelshark.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
import com.example.amanelshark.model.requestwarranty.RequestWarranty;
import com.example.amanelshark.model.responsrequest.ResponsRequest;
import com.example.amanelshark.model.types.Types;
import com.example.amanelshark.model.uploadimage.UploadImage;
import com.example.amanelshark.model.warranty.Warrenty;
import com.example.amanelshark.model.warrantyresponse.WarrantyResponse;
import com.example.amanelshark.model.years.Years;
import com.example.amanelshark.repository.AmanElsharkRepository;
import com.google.android.material.snackbar.Snackbar;


import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class AmanElsharkViewModel extends ViewModel {
    Context context;
    private AmanElsharkRepository amanElsharkRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Login> loginModel = new MutableLiveData<>();
    private MutableLiveData<Register> registerModel = new MutableLiveData<>();
    private MutableLiveData<AddCars> addCarsModel = new MutableLiveData<>();
    private MutableLiveData<WarrantyResponse> warrentyModel = new MutableLiveData<>();
    private MutableLiveData<RequestWarranty> makewarrentyModel = new MutableLiveData<>();

    private MutableLiveData<Brands> brandsModel = new MutableLiveData<>();
    private MutableLiveData<Model> ModelsModel = new MutableLiveData<>();
    private MutableLiveData<Types> TypesModel = new MutableLiveData<>();
    private MutableLiveData<Categories> CategoriesModel = new MutableLiveData<>();
    private MutableLiveData<Centers> centersModel = new MutableLiveData<>();
    private MutableLiveData<Years> yearsModel = new MutableLiveData<>();
    private MutableLiveData<CarDetails> carDetailsModel = new MutableLiveData<>();
    private MutableLiveData<PackageDetails> packageDetailsModel = new MutableLiveData<>();

    private MutableLiveData<CenterDetails> centersDetailsModel = new MutableLiveData<>();
    private MutableLiveData<Packages> packagesModel = new MutableLiveData<>();
    private MutableLiveData<Profile> profileModel = new MutableLiveData<>();
    private MutableLiveData<ListCars> listCarsModel = new MutableLiveData<>();
    private MutableLiveData<ResponsRequest> responseRequestModel = new MutableLiveData<>();
    private MutableLiveData<UploadImage> uploadInvoiceModel = new MutableLiveData<>();

    //   private MutableLiveData<Requests> modelMutableLiveRequests = new MutableLiveData<>();


    @Inject
    public AmanElsharkViewModel(AmanElsharkRepository amanElsharkRepository) {
        this.amanElsharkRepository = amanElsharkRepository;
    }

    public void clear(LifecycleOwner lifecycleOwner) {
        errorMessage.removeObservers(lifecycleOwner);

        errorMessage.postValue(null);
    }

    public LiveData<Boolean> isLoading() {

        return isLoading;
    }

    public LiveData<String> errorMessage() {

        return errorMessage;
    }

    public LiveData<Login> getloginRequests(View v, String email, String password) {
        requestLogin(email, password, v);
        return loginModel;

    }

    public LiveData<Register> getregisterRequests(String email, String password, String c_password, String phone, String name, View v) {
        requestRegister(email, password, c_password, phone, name, v);
        return registerModel;

    }

    public LiveData<WarrantyResponse> getWarrentyRequests(String token, Warrenty warranty, View v) {
        requestWarrenty(token, warranty, v);
        return warrentyModel;

    }


    public LiveData<AddCars> getAddcarsRequests(View v, String token, String model_category_id, String motor_number, String chassis_number, String plate_number, String meter_reading, int year) {
        requestAddCars(v, token, model_category_id, motor_number, chassis_number, plate_number, meter_reading, year);
        return addCarsModel;

    }


    public LiveData<Brands> getbrandsRequests(Context context, String id) {
        requestBrands(context, id);
        return brandsModel;
    }

    public LiveData<Model> getModelRequests(Context context, String token, String id) {
        requestModels(context, token, id);
        return ModelsModel;
    }

    public LiveData<Types> getTypeRequests(Context context, String token, String id) {
        requestTypes(context, token, id);
        return TypesModel;
    }

    public LiveData<Categories> getCategoriesRequests(Context context, String token, String id) {
        requestCategories(context, token, id);
        return CategoriesModel;
    }

    public LiveData<Years> getyearsRequests(Context context, String token) {
        requestYears(context, token);
        return yearsModel;
    }


    public LiveData<Centers> getCentersRequests(Context context, String token) {
        requestCenters(context, token);
        return centersModel;
    }

    public LiveData<CenterDetails> getCentersDetailsRequests(Context context, String token, int id) {
        requestCentersDetails(context, token, id);
        return centersDetailsModel;
    }

    public LiveData<CarDetails> getCarDetailsRequests(Context context, String token, int id) {
        requestCarDetails(context, token, id);
        return carDetailsModel;
    }


    public LiveData<Packages> getpackagesRequests(Context context, String token) {
        requestPackages(context, token);
        return packagesModel;
    }

    public LiveData<Profile> getpofileRequests(Context context, String token) {
        requestProfile(context, token);
        return profileModel;
    }

    public LiveData<ListCars> getLisCarsRequests(Context context, String token) {
        requestListCars(context, token);
        return listCarsModel;
    }

    public LiveData<RequestWarranty> makeWarantyRequests(Context context, String token, int package_id, int center_id,int client_car_id) {
        makeWarrantyRequest(context, token, package_id, center_id,client_car_id);
        return makewarrentyModel;
    }
    public LiveData<ResponsRequest> getRespnseRequests(Context context, String token) {
        respnseRequests(context, token);
        return responseRequestModel;
    }
    public LiveData<UploadImage> uploadImageRequests(Context context, String token, int id, MultipartBody.Part file) {
        uploadImageRequest(context, token,id,file);
        return uploadInvoiceModel;
    }
    public LiveData<PackageDetails> getPackagesDetailsRequests(Context context, String token, int id) {
        PackagesDetailsRequests(context, token,id);
        return packageDetailsModel;
    }

    private void PackagesDetailsRequests(Context context, String token, int id) {
        disposable.add(amanElsharkRepository.PackageDetails(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PackageDetails>() {
                    @Override
                    public void onSuccess(PackageDetails details) {
                        packageDetailsModel.setValue(details);
                        Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
                }


    private void uploadImageRequest(Context context, String token, int id, MultipartBody.Part file) {
        disposable.add(amanElsharkRepository.UpladFileRequest(token, id, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UploadImage>() {


                    @Override
                    public void onSuccess(UploadImage uploadImage) {
                        uploadInvoiceModel.setValue(uploadImage);
                        Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }


    private void respnseRequests(Context context, String token) {
        disposable.add(amanElsharkRepository.ResponsRequest(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponsRequest>() {
                    @Override
                    public void onSuccess(ResponsRequest responsRequest) {
                        responseRequestModel.setValue(responsRequest);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void makeWarrantyRequest(Context context, String token, int package_id, int center_id,int client_car_id ){
        disposable.add(amanElsharkRepository.ReqestWarranty(token, package_id, center_id,client_car_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<RequestWarranty>() {
                    @Override
                    public void onSuccess(RequestWarranty requestWarranty) {
                        makewarrentyModel.setValue(requestWarranty);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestCarDetails(Context context, String token, int id) {
        disposable.add(amanElsharkRepository.CarDetails(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CarDetails>() {
                    @Override
                    public void onSuccess(CarDetails carDetails) {
                        carDetailsModel.setValue(carDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestYears(Context context, String token) {
        disposable.add(amanElsharkRepository.Years(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Years>() {
                    @Override
                    public void onSuccess(Years years) {
                        yearsModel.setValue(years);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestListCars(Context context, String token) {
        disposable.add(amanElsharkRepository.CarsList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ListCars>() {
                    @Override
                    public void onSuccess(ListCars listCars) {

                        listCarsModel.setValue(listCars);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestPackages(Context context, String token) {
        disposable.add(amanElsharkRepository.Packages(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Packages>() {
                    @Override
                    public void onSuccess(Packages packages) {
                        packagesModel.setValue(packages);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestCentersDetails(Context context, String token, int id) {
        disposable.add(amanElsharkRepository.CentersDetails(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CenterDetails>() {
                    @Override
                    public void onSuccess(CenterDetails centerDetails) {
                        centersDetailsModel.setValue(centerDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestCenters(Context context, String token) {
        disposable.add(amanElsharkRepository.Centers(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Centers>() {
                    @Override
                    public void onSuccess(Centers centers) {
                        centersModel.setValue(centers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    private void requestModels(Context context, String token, String id) {
        disposable.add(amanElsharkRepository.Models(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Model>() {
                    @Override
                    public void onSuccess(Model model) {
                        if (model.getData().isEmpty()) {
                            Toast.makeText(context, "No DataCenter in ModelList Choose Another choice ", Toast.LENGTH_SHORT).show();

                        } else ModelsModel.setValue(model);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();
                        Log.e("error", e.getCause().toString());

                    }
                })
        );
    }

    private void requestCategories(Context context, String token, String id) {
        disposable.add(amanElsharkRepository.Categories(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Categories>() {
                    @Override
                    public void onSuccess(Categories categories) {
                        if (categories.getData().isEmpty()) {
                            Toast.makeText(context, "No DataCenter in CategoriesList Choose Another choice ", Toast.LENGTH_SHORT).show();

                        } else
                            CategoriesModel.setValue(categories);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();
                        Log.e("error", e.getCause().toString());

                    }
                })
        );
    }

    private void requestBrands(Context context, String id) {
        disposable.add(amanElsharkRepository.Brands(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Brands>() {


                    @Override
                    public void onSuccess(Brands brands) {

                        brandsModel.setValue(brands);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                        Log.e("error requestBrands", "Maybe You lost Your Connection");


                    }
                })
        );
    }

    private void requestTypes(Context context, String token, String id) {
        disposable.add(amanElsharkRepository.Types(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Types>() {
                    @Override
                    public void onSuccess(Types types) {
                        if (types.getData().isEmpty()) {
                            Toast.makeText(context, "No DataCenter in typesList Choose Another choice ", Toast.LENGTH_SHORT).show();

                        } else


                            TypesModel.setValue(types);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();
                        Log.e("error", e.getCause().toString());

                    }
                })
        );
    }

    private void requestAddCars(View v, String token, String model_category_id, String motor_number, String chassis_number, String plate_number, String meter_reading, int year) {
        isLoading.setValue(true);
        disposable.add(amanElsharkRepository.AddCars(token, model_category_id, motor_number, chassis_number, plate_number, meter_reading, year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AddCars>() {
                    @Override
                    public void onSuccess(AddCars addCars) {
                        addCarsModel.setValue(addCars);
                        Snackbar.make(v, "Success", Snackbar.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.setValue("Error");
                        Snackbar.make(v, "Something wrong happen", Snackbar.LENGTH_LONG).show();
                    }
                }));
    }

    private void requestRegister(String email, String password, String c_password, String phone, String name, View v) {
        isLoading.setValue(true);
        disposable.add(amanElsharkRepository.Register(email, password, c_password, phone, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Register>() {
                    @Override
                    public void onNext(Register register) {
                        isLoading.setValue(false);
                        errorMessage.setValue(null);
                        if (register.getError() != null) {
                            if (register.getError().getEmail() != null) {
                                Snackbar.make(v, "The email has already been taken.", Snackbar.LENGTH_LONG).show();

                            }
                            if (register.getError().getPhone() != null) {
                                Snackbar.make(v, "The phone has already been taken.", Snackbar.LENGTH_LONG).show();
                            }
                        } else {

                            registerModel.setValue(register);
                            Snackbar.make(v, "Success", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        errorMessage.setValue("Maybe You lost Your Connection");
                        isLoading.setValue(false);
                        Log.e("error requestRegister", e.getLocalizedMessage());

                        Snackbar.make(v, "Maybe You lost Your Connection", Snackbar.LENGTH_LONG);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void requestLogin(String email, String password, View v) {
        isLoading.setValue(true);
        disposable.add(amanElsharkRepository.Login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Login>() {


                    @Override
                    public void onNext(Login login) {
                        try {
                            isLoading.setValue(false);
                            errorMessage.setValue(null);
                            if (login.getMessage().equals("Unauthorised")) {
                                Snackbar.make(v, "Maybe Your Email or Password is Wrong", Snackbar.LENGTH_LONG).show();
                            } else if (login.getMessage().equals("success")) {
                                Snackbar.make(v, "success", Snackbar.LENGTH_LONG).show();
                                loginModel.setValue(login);

                            }
                        } catch (java.lang.NullPointerException exception) {
                            Log.e("viewmodel", exception.getLocalizedMessage());
                        } catch (Throwable exception) {

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        //   HttpException exception = (HttpException) e;

                        errorMessage.setValue("Maybe You lost Your Connection");
                        isLoading.setValue(false);
                        //   DynamicToast.makeSuccess(v.getContext(), "Maybe You lost Your Connection").show();
                        Snackbar.make(v, "Maybe You lost Your Connection", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                        Log.e("error requestLogin", e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {
                    }
                })

        );
    }

    private void requestWarrenty(String token, Warrenty warrenty, View v) {

        isLoading.setValue(true);
        disposable.add(amanElsharkRepository.Warrenty(token, warrenty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<WarrantyResponse>() {

                    @Override
                    public void onSuccess(WarrantyResponse warrantyResponse) {
                        warrentyModel.setValue(warrantyResponse);
                      //  Snackbar.make(v, "Success", Snackbar.LENGTH_LONG).setBackgroundTint(Color.GREEN).show();


                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(v, "Maybe You lost Your Connection", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                    }
                })

        );
    }

    private void requestProfile(Context context, String token) {
        disposable.add(amanElsharkRepository.Profile(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Profile>() {
                    @Override
                    public void onSuccess(Profile profile) {
                        profileModel.setValue(profile);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Maybe You lost Your Connection", Toast.LENGTH_SHORT).show();

                    }
                })
        );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null)
            disposable.clear();
    }

}
