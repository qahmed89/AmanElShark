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
import android.view.View;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityDetailsCarBinding;
import com.example.amanelshark.databinding.ActivityDetailsPaymentBinding;
import com.example.amanelshark.model.packagedetails.PackageDetails;
import com.example.amanelshark.model.requestpayment.Payment;
import com.example.amanelshark.model.requestpayment.RequestPayment;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import javax.inject.Inject;

public class DetailsPaymentActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityDetailsPaymentBinding activityDetailsPaymentBinding;
    String status , price,token;
    int package_id,request_id;
    RequestPayment requestPayment;
    Payment payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_payment);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityDetailsPaymentBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_payment);
        userViewModel = new ViewModelProvider(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");

        Intent intent = getIntent() ;
        status=intent.getStringExtra("status");
        request_id=intent.getIntExtra("id_request",0);
        package_id=intent.getIntExtra("package_id",0);

    userViewModel.getPackagesDetailsRequests(getApplication(),token,package_id).observe(this, new Observer<PackageDetails>() {
        @Override
        public void onChanged(PackageDetails details) {
            activityDetailsPaymentBinding.pricePaymentd.setText(String.valueOf(details.getData().getPrice()));
            activityDetailsPaymentBinding.requestidPaymentd.setText(String.valueOf(request_id));
            activityDetailsPaymentBinding.statusPaymentd.setText(status);
        }
    });

    activityDetailsPaymentBinding.paymentButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            payment=new Payment(package_id,request_id);
            requestPayment=new RequestPayment(payment);
            userViewModel.makePaymentRequests(getApplicationContext(),token,requestPayment).observe(DetailsPaymentActivity.this, new Observer<RequestPayment>() {
                @Override
                public void onChanged(RequestPayment requestPayment) {

                }
            });
        }
    });

        activityDetailsPaymentBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
