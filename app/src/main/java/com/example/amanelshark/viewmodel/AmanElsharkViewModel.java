package com.example.amanelshark.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.repository.AmanElsharkRepository;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AmanElsharkViewModel extends ViewModel {
    Context context;
    private AmanElsharkRepository amanElsharkRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Login> loginModel = new MutableLiveData<>();
    private MutableLiveData<Brands> brandsModel = new MutableLiveData<>();

    //   private MutableLiveData<Requests> modelMutableLiveRequests = new MutableLiveData<>();



    @Inject
    public AmanElsharkViewModel(AmanElsharkRepository amanElsharkRepository) {
        this.amanElsharkRepository = amanElsharkRepository;
    }


    public LiveData<Boolean> isLoading() {

        return isLoading;
    }

    public LiveData<String> errorMessage() {

        return errorMessage;
    }

    public LiveData<Login> getloginRequests(Context context, String email, String password) {
        requestLogin(context, email, password);
        return loginModel;
    }

    public LiveData<Brands> getbrandsRequests(Context context, String id) {
        requestBrands(context, id);
        return brandsModel;
    }

    private void requestBrands(Context context, String id) {
        disposable.add(amanElsharkRepository.Brands(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Brands>() {


                    @Override
                    public void onSuccess(Brands brands) {
                        brandsModel.setValue( brands);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        );
    }

    private void requestLogin(Context context, String email, String password) {
        disposable.add(amanElsharkRepository.Login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Login>() {
                    @Override
                    public void onSuccess(Login login) {
                        loginModel.setValue(login);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }
//
//    public LiveData<RequestVocation> getmodelMutableLiveVocationRequest(final Context context,String description, String type, String from, String to, String id) {
//        sendVocation(context,type, description, from, to, id);
//        return modelMutableLiveVocationRequest;
//    }
//
//    public LiveData<Login> getmodelMutableLiveLoginRequest(final Context context,String email, String password) {
//        LoginRequest(context,email, password);
//        return modelMutableLiveLoginRequest;
//    }
//    public LiveData<CheckIn> getmodelMutableLiveCheckinRequest(final Context context,String id, String date) {
//        checkInRequest(context,id, date);
//        return modelMutableLiveCheckinRequest;
//    }
//    public LiveData<CheckOut> getmodelMutableLiveCheckoutRequest(String id, String date) {
//        checkOutRequest(context,id, date);
//        return modelMutableLiveCheckoutRequest;
//    }

    //  private void checkOutRequest(final Context context,String id, String date) {

}
