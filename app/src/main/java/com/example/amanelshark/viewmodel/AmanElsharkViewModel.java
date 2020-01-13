package com.example.amanelshark.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.model.subbrands.SubBreand;
import com.example.amanelshark.repository.AmanElsharkRepository;
import com.example.amanelshark.view.LoginActivity;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.HttpException;

public class AmanElsharkViewModel extends ViewModel {
    Context context;
    private AmanElsharkRepository amanElsharkRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Login> loginModel = new MutableLiveData<>();
    private MutableLiveData<Brands> brandsModel = new MutableLiveData<>();
    private MutableLiveData<SubBreand> sub_brandsModel = new MutableLiveData<>();

    //   private MutableLiveData<Requests> modelMutableLiveRequests = new MutableLiveData<>();



    @Inject
    public AmanElsharkViewModel(AmanElsharkRepository amanElsharkRepository) {
        this.amanElsharkRepository = amanElsharkRepository;
    }

    public void clear(LifecycleOwner lifecycleOwner)
    {
      errorMessage.removeObservers(lifecycleOwner);

      errorMessage.postValue(null);
    }
    public LiveData<Boolean> isLoading() {

        return isLoading;
    }

    public LiveData<String> errorMessage() {

        return errorMessage;
    }

    public LiveData<Login> getloginRequests(Context context, String email, String password,View v) {
        requestLogin(context, email, password,v);
        return loginModel;

    }

    public LiveData<Brands> getbrandsRequests(Context context, String id) {
        requestBrands(context, id);
        return brandsModel;
    }
    public LiveData<SubBreand> getsubbrandsRequests(Context context,String token, String id) {
        requestSubBrands(context,token, id);
        return sub_brandsModel;
    }

    private void requestSubBrands(Context context,String token, String id) {
        disposable.add(amanElsharkRepository.subBrands(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SubBreand>() {
                    @Override
                    public void onSuccess(SubBreand subBreand) {
                        sub_brandsModel.setValue(subBreand);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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

                        brandsModel.setValue( brands);
                    }

                    @Override
                    public void onError(Throwable e) {
                       // Toast.makeText(context, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error", e.getCause().toString());


                    }
                })
        );
    }

    private void requestLogin(Context context, String email, String password,View v) {
        isLoading.setValue(true);
        disposable.add(amanElsharkRepository.Login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Login>() {


                    @Override
                    public void onNext(Login login) {
                        loginModel.setValue(login);
                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpException exception = (HttpException) e;

                        errorMessage.setValue(String.valueOf(exception.code()));
                        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Alert message to be shown");
                        alertDialog.setButton( AlertDialog.BUTTON_POSITIVE,"OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();


                        Button buttonbackground1 = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        buttonbackground1.setTextColor(Color.WHITE);

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
        if( disposable != null )
            disposable.clear();
    }

}
