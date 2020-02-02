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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.developer.kalert.KAlertDialog;
import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityAddCarBinding;
import com.example.amanelshark.model.addcar.AddCars;
import com.example.amanelshark.model.brands.Brands;
import com.example.amanelshark.model.brands.DataItemBrands;
import com.example.amanelshark.model.categories.Categories;
import com.example.amanelshark.model.categories.DataItemCategories;
import com.example.amanelshark.model.models.DataItemModels;
import com.example.amanelshark.model.models.Model;
import com.example.amanelshark.model.subbrands.SubBreand;
import com.example.amanelshark.model.subbrands.SubDataItem;
import com.example.amanelshark.model.types.DataItemTypes;
import com.example.amanelshark.model.types.Types;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AddCarActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    private AmanElsharkViewModel userViewModel;
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
    List<DataItemBrands> brandslist;
    String sid;
    List<DataItemModels> lis_model;
    List<DataItemTypes>list_types;
    List<DataItemCategories>list_categories;

    int ids;
    String token;
    ActivityAddCarBinding activityAddCarBinding;
    ArrayAdapter arrayAdapter;
    ArrayAdapter<String> arrayAdapter_namt;
    ArrayAdapter<String> arrayAdapter_year;
    ArrayAdapter arrayAdapter_models;
    ArrayAdapter arrayAdapter_type;
    ArrayAdapter arrayAdapter_categories;
    String years_string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityAddCarBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_car);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);

        brandslist = new ArrayList<>();
        lis_model = new ArrayList<>();
        list_types=new ArrayList<>();
        list_categories=new ArrayList<>();

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brandslist);
        arrayAdapter_models = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lis_model);
        arrayAdapter_type = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_types);
        arrayAdapter_categories = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_categories);

        arrayAdapter_year = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, yearTemp);

        brands_method();

        activityAddCarBinding.myProductionYear.setAdapter(arrayAdapter_year);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


       // arrayAdapter_namt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //activityAddCarBinding.namt.setAdapter(arrayAdapter_namt);
      //  activityAddCarBinding.model.setAdapter(arrayAdapter);
        activityAddCarBinding.brands.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lis_model.clear();
                activityAddCarBinding.model.setAdapter(null);
                arrayAdapter_models.clear();
                activityAddCarBinding.model.clearListSelection();
                ids = brandslist.get(position).getId();
                sid = String.valueOf(ids);
                activityAddCarBinding.model.setText("");
                activityAddCarBinding.layoutModel.setEnabled(true);
                activityAddCarBinding.model.setEnabled(true);
                activityAddCarBinding.myTypeLayout.setEnabled(false);
                activityAddCarBinding.myType.setEnabled(false);
                activityAddCarBinding.layoutCategories.setEnabled(false);
                activityAddCarBinding.categories.setEnabled(false);
                subbrandRequest();

            }



        });
        activityAddCarBinding.model.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list_types.clear();
                activityAddCarBinding.myType.setAdapter(null);
                arrayAdapter_type.clear();
                activityAddCarBinding.myType.clearListSelection();
                ids = lis_model.get(position).getId();
                sid = String.valueOf(ids);
                activityAddCarBinding.myType.setText("");
                activityAddCarBinding.categories.setText("");

                activityAddCarBinding.myTypeLayout.setEnabled(true);
                activityAddCarBinding.myType.setEnabled(true);
                activityAddCarBinding.categories.setEnabled(false);
                activityAddCarBinding.layoutCategories.setEnabled(false);
                typesRequest();
            }



        });

        activityAddCarBinding.myType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list_categories.clear();
                activityAddCarBinding.categories.setAdapter(null);
                arrayAdapter_categories.clear();
                activityAddCarBinding.categories.clearListSelection();
                ids = list_types.get(position).getId();
                sid = String.valueOf(ids);
                activityAddCarBinding.categories.setText("");
                activityAddCarBinding.layoutCategories.setEnabled(true);
               activityAddCarBinding.categories.setEnabled(true);
                categoriesRequest();
            }




        });
activityAddCarBinding.categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ids = list_categories.get(position).getId();
        sid = String.valueOf(ids);
    }
});

activityAddCarBinding.myProductionYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         years_string=arrayAdapter_year.getItem(position);
    }
});
            activityAddCarBinding.layoutModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               brands_method();
                arrayAdapter.notifyDataSetChanged()  ;

            }
        });
        activityAddCarBinding.brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   brands_method();

                activityAddCarBinding.brands.showDropDown();
            }
        });

        activityAddCarBinding.categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  subbrandRequest();
                activityAddCarBinding.categories.showDropDown();
            }
        });
        activityAddCarBinding.model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.model.showDropDown();

            }
        });

        activityAddCarBinding.myType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             //   subbrandRequest();
                activityAddCarBinding.myType.showDropDown();



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
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                userViewModel.getAddcarsRequests(v,token,sid,activityAddCarBinding.numMotor.getText().toString(),activityAddCarBinding.chassisNum.getText().toString(),activityAddCarBinding.plateNum.getText().toString(),activityAddCarBinding.kmReading.getText().toString(),years_string).observe(AddCarActivity.this, new Observer<AddCars>() {
                   @Override
                   public void onChanged(AddCars addCars) {

                   }
               });
//                Intent intent = new Intent(AddCarActivity.this,RegluarMainTenanceActivity.class);
//                startActivity(intent);
//                finish();
            }
        });


    }

    private void subbrandRequest() {
        userViewModel.getModelRequests(AddCarActivity.this, token, sid).observe(AddCarActivity.this, new Observer<Model>() {
            @Override
            public void onChanged(Model model) {
                activityAddCarBinding.model.clearListSelection();
                lis_model.clear();
                lis_model.addAll(model.getData());
                arrayAdapter_models.notifyDataSetChanged();

                activityAddCarBinding.model.setAdapter(arrayAdapter_models);


            }
        });
    }

    private void brands_method() {
        userViewModel.getbrandsRequests(AddCarActivity.this,token).observe(AddCarActivity.this, new Observer<Brands>() {
            @Override
            public void onChanged(Brands brands) {
                brandslist.addAll(brands.getData());
               activityAddCarBinding.brands.setAdapter(arrayAdapter);


            }


        });
    }
    private void typesRequest() {
        userViewModel.getTypeRequests(AddCarActivity.this, token, sid).observe(this, new Observer<Types>() {
            @Override
            public void onChanged(Types types) {
                activityAddCarBinding.myType.clearListSelection();
                list_types.clear();
                list_types.addAll(types.getData());
                arrayAdapter_type.notifyDataSetChanged();

                activityAddCarBinding.myType.setAdapter(arrayAdapter_type);
            }
        }) ;

    }
    private void categoriesRequest() {
        userViewModel.getCategoriesRequests(AddCarActivity.this, token, sid).observe(this, new Observer<Categories>() {
            @Override
            public void onChanged(Categories categories) {
                activityAddCarBinding.categories.clearListSelection();
                list_categories.clear();
                list_categories.addAll(categories.getData());
                arrayAdapter_categories.notifyDataSetChanged();

                activityAddCarBinding.categories.setAdapter(arrayAdapter_categories);
            }
        });
    }

}
