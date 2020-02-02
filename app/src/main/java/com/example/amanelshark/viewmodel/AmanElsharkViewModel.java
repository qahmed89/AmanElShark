package com.example.amanelshark.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amanelshark.model.addcar.AddCars;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.register.Register;
import com.example.amanelshark.model.types.Types;
import com.example.amanelshark.repository.AmanElsharkRepository;
import com.google.android.material.snackbar.Snackbar;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AmanElsharkViewModel extends ViewModel {
    Context context;
    private AmanElsharkRepository amanElsharkRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Login> loginModel = new MutableLiveData<>();
    private MutableLiveData<Register> registerModel = new MutableLiveData<>();
    private MutableLiveData<AddCars> addCarsModel = new MutableLiveData<>();

    private MutableLiveData<Brands> brandsModel = new MutableLiveData<>();
    private MutableLiveData<Model> ModelsModel = new MutableLiveData<>();
    private MutableLiveData<Types> TypesModel = new MutableLiveData<>();
    private MutableLiveData<Categories> CategoriesModel = new MutableLiveData<>();


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

    public LiveData<AddCars> getAddcarsRequests(View v, String token, String model_category_id, String motor_number, String chassis_number, String plate_number, String meter_reading, String year) {
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

    private void requestModels(Context context, String token, String id) {
        disposable.add(amanElsharkRepository.Models(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Model>() {
                    @Override
                    public void onSuccess(Model model) {
                        if (model.getData().isEmpty()) {
                            Toast.makeText(context, "No Data in ModelList Choose Another choice ", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(context, "No Data in CategoriesList Choose Another choice ", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(context, "No Data in typesList Choose Another choice ", Toast.LENGTH_SHORT).show();

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

    private void requestAddCars(View v, String token, String model_category_id, String motor_number, String chassis_number, String plate_number, String meter_reading, String year) {
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
                        Snackbar.make(v, "Maybe You lost Your Connection", Snackbar.LENGTH_LONG).show();
                        Log.e("error requestLogin", e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {
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
