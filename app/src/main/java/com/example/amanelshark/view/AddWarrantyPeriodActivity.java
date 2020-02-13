package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.adapter.PackageAdapter;
import com.example.amanelshark.databinding.ActivityAddWarrantyPeriodBinding;
import com.example.amanelshark.model.packages.DataItemPackages;
import com.example.amanelshark.model.packages.Packages;
import com.example.amanelshark.model.warranty.Warranty;
import com.example.amanelshark.model.warranty.Warrenty;
import com.example.amanelshark.model.warrantyresponse.WarrantyResponse;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class AddWarrantyPeriodActivity extends AppCompatActivity  implements PackageAdapter.Onpackagelistener{

    String token;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityAddWarrantyPeriodBinding activityAddWarrantyPeriodBinding;
   Warrenty warrenties;
   Warranty warranty;
   List <DataItemPackages> itemPackagesList;
   PackageAdapter packageAdapter;
   String toDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warranty_period);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityAddWarrantyPeriodBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_warranty_period);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        activityAddWarrantyPeriodBinding.recycleviewPackage.setLayoutManager(layoutManager);
        userViewModel.getpackagesRequests(this,token).observe(this, new Observer<Packages>() {
            @Override
            public void onChanged(Packages packages) {
                itemPackagesList=new ArrayList<>();
                itemPackagesList.addAll(packages.getData());
                packageAdapter= new PackageAdapter(AddWarrantyPeriodActivity.this,itemPackagesList,getApplicationContext());
                activityAddWarrantyPeriodBinding.recycleviewPackage.setAdapter(packageAdapter);
            }
        });

    }

    @Override
    public void onPackageClick(int postion) {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        toDate= df.format(c);
        warranty=new Warranty(itemPackagesList.get(postion).getId(),toDate);
        warrenties=new Warrenty(warranty);
        userViewModel.getWarrentyRequests(token,warrenties,getCurrentFocus()).observe(this, new Observer<WarrantyResponse>() {
            @Override
            public void onChanged(WarrantyResponse warrantyResponse) {
                Intent intent = new Intent(getApplicationContext(),ServicesActivity.class);
                startActivity(intent);
            }
        });

    }
}
