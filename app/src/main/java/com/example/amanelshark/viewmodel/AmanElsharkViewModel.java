package com.example.amanelshark.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amanelshark.remote.AmanElSharkServices;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AmanElsharkViewModel extends ViewModel {
    private AmanElSharkServices amanElSharkServices;
    Context context;
    private CompositeDisposable disposable = new CompositeDisposable();
 //   private MutableLiveData<Requests> modelMutableLiveRequests = new MutableLiveData<>();

    @Inject
    public AmanElsharkViewModel(AmanElSharkServices amanElSharkServices) {
        this.amanElSharkServices = amanElSharkServices;
    }




//    public LiveData<Requests> getModelMutableLiveRequests(Context context) {
//        loadDats(context);
//        return modelMutableLiveRequests;
//    }
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
