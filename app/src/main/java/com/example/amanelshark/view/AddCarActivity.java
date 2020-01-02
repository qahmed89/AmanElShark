package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityAddCarBinding;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.brands.DataItem;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AddCarActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    private AmanElsharkViewModel userViewModel;
private  static final String[] color = new String[]{"red","green"};
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    List <DataItem> brandslist;
    String token ;
    ActivityAddCarBinding activityAddCarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityAddCarBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        brandslist = new ArrayList<>();
        sharedPref =getSharedPreferences( getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor=sharedPref.edit();
        token=sharedPref.getString(getString(R.string.token),"null");
        AutoCompleteTextView x = findViewById(R.id.my_spinner_dropdown);
        TextInputLayout xx = findViewById(R.id.my_spinner_which_isnt_a_spinner);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        userViewModel.getbrandsRequests(getApplicationContext(),token).observe(this, new Observer<Brands>() {
            @Override
            public void onChanged(Brands brands) {
                brandslist.addAll(brands.getData());

            }


        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,brandslist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        x.setAdapter(arrayAdapter);

        x.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ids = brandslist.get(position).getId();
                activityAddCarBinding.myTypeLayout.setEnabled(true);
                activityAddCarBinding.myType.setEnabled(true);
            }
        });
        xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.showDropDown();
            }
        });
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.showDropDown();
            }
        });

    }
}
