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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityAddCarBinding;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.brands.DataItem;
import com.example.amanelshark.model.subbrands.SubBreand;
import com.example.amanelshark.model.subbrands.SubDataItem;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AddCarActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    private AmanElsharkViewModel userViewModel;
    private static final String[] color = new String[]{"red", "green"};
    private static final String[] vans = new String[]{"فان", "هاتشباك", "كابورليه", "ستيشن", "سيدان", "كوبيه", "رياضية", "مينى-باص", "كروس-أوفر"};
    String[] yearTemp = new String[]
            {"1970",
                    "1971", "1972", "1973", "1974",
                    "1975", "1976", "1977", "1978",
                    "1979", "1980", "1981", "1982",
                    "1983", "1984", "1985", "1987",
                    "1988", "1989", "1990", "1991",
                    "1992", "1993", "1994", "1995",
                    "1996", "1997", "1998", "1999",
                    "2000", "2001", "2002", "2003",
                    "2004", "2005", "2006", "2007",
                    "2008", "2009", "2010", "2011",
                    "2012", "2013", "2014", "2015",
                    "2016"};
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    List<DataItem> brandslist;
    String sid;
    List<SubDataItem> sub_brandslist;
    int ids;
    String token;
    ActivityAddCarBinding activityAddCarBinding;
    ArrayAdapter arrayAdapter;
    ArrayAdapter<String> arrayAdapter_namt;
    ArrayAdapter<String> arrayAdapter_year;
    ArrayAdapter arrayAdapter_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityAddCarBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_car);

        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        brandslist = new ArrayList<>();
        sub_brandslist = new ArrayList<>();
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        Button button = findViewById(R.id.button);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item
                , brandslist);
        arrayAdapter_namt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item
                , vans);
        arrayAdapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item
                , yearTemp);
        arrayAdapter_type = new ArrayAdapter(AddCarActivity.this, android.R.layout.simple_spinner_dropdown_item, sub_brandslist);


        brands_method();

        activityAddCarBinding.myProductionYear.setAdapter(arrayAdapter_year);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter_namt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAddCarBinding.namt.setAdapter(arrayAdapter_namt);
      //  activityAddCarBinding.model.setAdapter(arrayAdapter);
        activityAddCarBinding.model.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sub_brandslist.clear();
                activityAddCarBinding.myType.setAdapter(null);
                arrayAdapter_type.clear();
                activityAddCarBinding.myType.clearListSelection();
                ids = brandslist.get(position).getId();
                sid = String.valueOf(ids);
                activityAddCarBinding.myType.setText("");
                activityAddCarBinding.myTypeLayout.setEnabled(true);
                activityAddCarBinding.myType.setEnabled(true);
                subbrandRequest();

            }


        });





            activityAddCarBinding.layoutModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               brands_method();
                arrayAdapter.notifyDataSetChanged()  ;

            }
        });
        activityAddCarBinding.model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   brands_method();

                activityAddCarBinding.model.showDropDown();
            }
        });
        activityAddCarBinding.layoutNamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.namt.showDropDown();
            }
        });
        activityAddCarBinding.namt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  subbrandRequest();
                activityAddCarBinding.namt.showDropDown();
            }
        });
        activityAddCarBinding.myTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    subbrandRequest();

                activityAddCarBinding.myType.showDropDown();
            }
        });
        activityAddCarBinding.myType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             //   subbrandRequest();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        activityAddCarBinding.myType.showDropDown();
                    }
                }, 500);

            }
        });
        activityAddCarBinding.layoutProductionYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.myProductionYear.showDropDown();
            }
        });
        activityAddCarBinding.myProductionYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.myProductionYear.showDropDown();
            }
        });

        activityAddCarBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCarActivity.this,RegluarMainTenanceActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void subbrandRequest() {
        userViewModel.getsubbrandsRequests(AddCarActivity.this, token, sid).observe(AddCarActivity.this, new Observer<SubBreand>() {
            @Override
            public void onChanged(SubBreand subBreand) {
                activityAddCarBinding.myType.clearListSelection();
                sub_brandslist.clear();
                sub_brandslist.addAll(subBreand.getData());
                arrayAdapter_type.notifyDataSetChanged();

                activityAddCarBinding.myType.setAdapter(arrayAdapter_type);



            }
        });
    }

    private void brands_method() {
        userViewModel.getbrandsRequests(AddCarActivity.this,token).observe(AddCarActivity.this, new Observer<Brands>() {
            @Override
            public void onChanged(Brands brands) {
                brandslist.addAll(brands.getData());
               activityAddCarBinding.model.setAdapter(arrayAdapter);


            }


        });
    }

}
