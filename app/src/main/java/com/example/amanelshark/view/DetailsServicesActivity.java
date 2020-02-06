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
import android.widget.Button;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityDetailsServicesBinding;
import com.example.amanelshark.model.centerDetails.CenterDetails;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import javax.inject.Inject;

public class DetailsServicesActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityDetailsServicesBinding activityDetailsServicesBinding;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_services);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Intent intent = getIntent() ;
        String ids=intent.getStringExtra("id_event");
        token = sharedPref.getString(getString(R.string.token), "null");
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityDetailsServicesBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_services);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
    userViewModel.getCentersDetailsRequests(getApplicationContext(),token,ids).observe(this, new Observer<CenterDetails>() {
        @Override
        public void onChanged(CenterDetails centerDetails) {
            activityDetailsServicesBinding.nameDetails.setText(centerDetails.getDataCenter().getName());
            activityDetailsServicesBinding.ratingstarDetails.setRating(centerDetails.getDataCenter().getRate());
            activityDetailsServicesBinding.toolbarServiestext.setText(centerDetails.getDataCenter().getName());
            activityDetailsServicesBinding.phoneDetails.setText(centerDetails.getDataCenter().getPhone());
            activityDetailsServicesBinding.descriptionDetails.setText(centerDetails.getDataCenter().getDescription());
            activityDetailsServicesBinding.workinghourDeatils.setText(centerDetails.getDataCenter().getWorkingDays()+"  -  " +centerDetails.getDataCenter().getWorkingHours());
        }
    });
    activityDetailsServicesBinding.imagebackDetails.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            finish();

        }
    });
    }
}
