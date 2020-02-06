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
import android.view.Display;
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

import static com.google.gson.reflect.TypeToken.get;

public class AddCarActivity extends AppCompatActivity {
    private static final String[] vans = new String[]{"فان", "هاتشباك", "كابورليه", "ستيشن", "سيدان", "كوبيه", "رياضية", "مينى-باص", "كروس-أوفر"};
    @Inject
    ViewModelProvider.Factory viewModelProvider;
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
    List<DataItemTypes> list_types;
    List<DataItemCategories> list_categories;
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
    private AmanElsharkViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityAddCarBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_car);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);

        InputMethodManager imm = getInputMethodManager();

        brands_method();

        activityAddCarBinding.myProductionYear.setAdapter(arrayAdapter_year);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


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
                years_string = arrayAdapter_year.getItem(position);
            }
        });
        activityAddCarBinding.layoutModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brands_method();
                arrayAdapter.notifyDataSetChanged();

            }
        });
        activityAddCarBinding.brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   brands_method();

                activityAddCarBinding.brands.showDropDown();
                activityAddCarBinding.brands.setError(null);

            }
        });

        activityAddCarBinding.categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  subbrandRequest();
                activityAddCarBinding.categories.showDropDown();
                activityAddCarBinding.categories.setError(null);
            }
        });
        activityAddCarBinding.model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.model.showDropDown();
                activityAddCarBinding.model.setError(null);


            }
        });

        activityAddCarBinding.myType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //   subbrandRequest();
                activityAddCarBinding.myType.showDropDown();
                activityAddCarBinding.myType.setError(null);



            }
        });
        activityAddCarBinding.layoutProductionYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.myProductionYear.showDropDown();
                activityAddCarBinding.myProductionYear.setError(null);

            }
        });
        activityAddCarBinding.myProductionYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAddCarBinding.myProductionYear.showDropDown();
                activityAddCarBinding.myProductionYear.setError(null);

            }
        });

        activityAddCarBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                vaildationtion();
//
//                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                userViewModel.getAddcarsRequests(v, token, sid, activityAddCarBinding.numMotor.getText().toString(), activityAddCarBinding.chassisNum.getText().toString(), activityAddCarBinding.plateNum.getText().toString(), activityAddCarBinding.kmReading.getText().toString(), years_string).observe(AddCarActivity.this, new Observer<AddCars>() {
//                    @Override
//                    public void onChanged(AddCars addCars) {
//
//                    }
//                });
                Intent intent = new Intent(AddCarActivity.this,AddWarrantyPeriodActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }

    private InputMethodManager getInputMethodManager() {
        brandslist = new ArrayList<>();
        lis_model = new ArrayList<>();
        list_types = new ArrayList<>();
        list_categories = new ArrayList<>();

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brandslist);
        arrayAdapter_models = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lis_model);
        arrayAdapter_type = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_types);
        arrayAdapter_categories = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_categories);

        arrayAdapter_year = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, yearTemp);
        return imm;
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
        userViewModel.getbrandsRequests(AddCarActivity.this, token).observe(AddCarActivity.this, new Observer<Brands>() {
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
        });

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

    public void vaildationtion() {
        String brands, model, types, category, motor_number, chassis_number, plate_number, meter_reading, year;
        category = activityAddCarBinding.categories.getText().toString();
        types = activityAddCarBinding.myType.getText().toString();
        brands = activityAddCarBinding.brands.getText().toString();
        model = activityAddCarBinding.model.getText().toString();
        motor_number = activityAddCarBinding.numMotor.getText().toString();
        chassis_number = activityAddCarBinding.chassisNum.getText().toString();
        plate_number = activityAddCarBinding.plateNum.getText().toString();
        meter_reading = activityAddCarBinding.kmReading.getText().toString();
        year = activityAddCarBinding.myProductionYear.getText().toString();
        if (category.isEmpty()) {
            activityAddCarBinding.categories.setError("Empty Field");
        } else {
            activityAddCarBinding.categories.setError(null);
        }


        if (brands.isEmpty()) {
            activityAddCarBinding.brands.setError("Empty Field");
        } else {
            activityAddCarBinding.brands.setError(null);
        }

        if (types.isEmpty()) {
            activityAddCarBinding.myType.setError("Empty Field");
        } else {
            activityAddCarBinding.myType.setError(null);
        }
        if (model.isEmpty()) {
            activityAddCarBinding.model.setError("Empty Field");
        } else {
            activityAddCarBinding.model.setError(null);
        }
        if (motor_number.isEmpty()) {
            activityAddCarBinding.numMotor.setError("Empty Field");
        } else {
            activityAddCarBinding.numMotor.setError(null);
        }
        if (chassis_number.isEmpty()) {
            activityAddCarBinding.chassisNum.setError("Empty Field");
        } else {
            activityAddCarBinding.chassisNum.setError(null);
        }
        if (plate_number.isEmpty()) {
            activityAddCarBinding.plateNum.setError("Empty Field");
        } else {
            activityAddCarBinding.plateNum.setError(null);

        }
        if (meter_reading.isEmpty()) {
            activityAddCarBinding.kmReading.setError("Empty Field");
        } else {
            activityAddCarBinding.kmReading.setError(null);
        }
        if (year.isEmpty()) {
            activityAddCarBinding.myProductionYear.setError(null);
        } else {
            activityAddCarBinding.myProductionYear.setError(null);
        }
    }

}
