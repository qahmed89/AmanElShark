package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityDetailsCarBinding;
import com.example.amanelshark.databinding.ActivityDetailsPaymentBinding;
import com.example.amanelshark.model.packagedetails.PackageDetails;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import javax.inject.Inject;

public class DetailsPaymentActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityDetailsPaymentBinding activityDetailsPaymentBinding;
    String status , price,request_id,token;
    int package_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_payment);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityDetailsPaymentBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_payment);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");

        Intent intent = getIntent() ;
        status=intent.getStringExtra("status");
        request_id=String.valueOf(intent.getIntExtra("id_request",0));
        package_id=intent.getIntExtra("package_id",0);

    userViewModel.getPackagesDetailsRequests(getApplication(),token,package_id).observe(this, new Observer<PackageDetails>() {
        @Override
        public void onChanged(PackageDetails details) {
            activityDetailsPaymentBinding.pricePaymentd.setText(String.valueOf(details.getData().getPrice()));
            activityDetailsPaymentBinding.requestidPaymentd.setText(request_id);
            activityDetailsPaymentBinding.statusPaymentd.setText(status);
        }
    });
    }
}
