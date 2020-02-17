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
import com.example.amanelshark.databinding.ActivityRegisterBinding;
import com.example.amanelshark.model.cardetails.CarDetails;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import javax.inject.Inject;

public class DetailsCarActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    ActivityDetailsCarBinding activityDetailsCarBinding;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String token;
    int id_car;
    private AmanElsharkViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_car);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        Intent intent = getIntent();
        id_car = intent.getIntExtra("id_car", 1);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);

        activityDetailsCarBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_car);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        userViewModel.getCarDetailsRequests(this, token, id_car).observe(this, new Observer<CarDetails>() {
            @Override
            public void onChanged(CarDetails carDetails) {
                activityDetailsCarBinding.brandCar.setText(carDetails.getData().getBrand());
                activityDetailsCarBinding.modelCar.setText(carDetails.getData().getModel());
                activityDetailsCarBinding.categoryCar.setText(carDetails.getData().getCategory());
                activityDetailsCarBinding.typeCar.setText(carDetails.getData().getType());
                activityDetailsCarBinding.chassiaCar.setText(carDetails.getData().getChassisNumber());
                activityDetailsCarBinding.plateCar.setText(carDetails.getData().getPlateNumber());
                activityDetailsCarBinding.motorNmCar.setText(carDetails.getData().getMotorNumber());
                activityDetailsCarBinding.meterreadingCar.setText(carDetails.getData().getMeterReading());
                activityDetailsCarBinding.yearCar.setText(String.valueOf(carDetails.getData().getYear()));

                if (carDetails.getData().getWarranty() == null) {
                    activityDetailsCarBinding.startCar.setText("you dont have Warranty Yet");
                    activityDetailsCarBinding.endCar.setText("you dont have Warranty Yet");
                    activityDetailsCarBinding.warrantyDetailscar.setVisibility(View.VISIBLE);


                }else {
                    activityDetailsCarBinding.endCar.setText(carDetails.getData().getWarranty().getPackages().getName());
                    activityDetailsCarBinding.startCar.setText(carDetails.getData().getWarranty().getPackages().getCreatedAt());

                }


            }
        });
        activityDetailsCarBinding.warrantyDetailscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddWarrantyPeriodActivity.class);
                intent.putExtra("id_car",id_car);
                startActivity(intent);
            }
        });



    }
}
