package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityAddWarrantyPeriodBinding;
import com.example.amanelshark.model.warranty.Warranty;
import com.example.amanelshark.model.warranty.Warrenty;
import com.example.amanelshark.model.warrantyresponse.WarrantyResponse;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class AddWarrantyPeriodActivity extends AppCompatActivity {
    private  static final String[] spinn1 = new String[] {"SELECT KM","10", "20", "30"};
    private  static final String[] spinn2= new String[]{"SELECT KM","40", "50", "60"};
    private  static final String[] spinn3 = new String[]{"SELECT KM","70", "80", "90"};
    String km;
    String todate;
    String warranty_period;
    Warranty warranty;
    String token;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityAddWarrantyPeriodBinding activityAddWarrantyPeriodBinding;
   Warrenty warrenties;

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

        activityAddWarrantyPeriodBinding.submitWarranty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.getWarrentyRequests(token,warrenties,v).observe(AddWarrantyPeriodActivity.this, new Observer<WarrantyResponse>() {
                    @Override
                    public void onChanged(WarrantyResponse warrantyResponse) {
                Intent intent = new Intent(AddWarrantyPeriodActivity.this,RegluarMainTenanceActivity.class);
                startActivity(intent);
                finish();
                    }
                });


            }
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,  spinn1){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,  spinn2){
        @Override
        public boolean isEnabled(int position){
            if(position == 0)
            {
                // Disable the first item from Spinner
                // First item will be use for hint
                return false;
            }
            else
            {
                return true;
            }
        }
        @Override
        public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            if(position == 0){
                // Set the hint text color gray
                tv.setTextColor(Color.GRAY);
            }
            else {
                tv.setTextColor(Color.BLACK);
            }
            return view;
        }
    };
        ArrayAdapter<String> arrayAdapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,  spinn3){
        @Override
        public boolean isEnabled(int position){
            if(position == 0)
            {
                // Disable the first item from Spinner
                // First item will be use for hint
                return false;
            }
            else
            {
                return true;
            }
        }
        @Override
        public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            if(position == 0){
                // Set the hint text color gray
                tv.setTextColor(Color.GRAY);
            }
            else {
                tv.setTextColor(Color.BLACK);
            }
            return view;
        }
    };
        activityAddWarrantyPeriodBinding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioone:
                        activityAddWarrantyPeriodBinding.spinnerone.setEnabled(true);
                        activityAddWarrantyPeriodBinding.spinnertwo.setEnabled(false);
                        activityAddWarrantyPeriodBinding.spinnerthree.setEnabled(false);
                        activityAddWarrantyPeriodBinding.spinnertwo.setSelection(-1);
                        activityAddWarrantyPeriodBinding.spinnerthree.setSelection(-1);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        activityAddWarrantyPeriodBinding.spinnerone.setAdapter(arrayAdapter);
                        activityAddWarrantyPeriodBinding.spinnertwo.setAdapter(null);
                        activityAddWarrantyPeriodBinding.spinnerthree.setAdapter(null);


break;

                    case R.id.radiotwo:
                        activityAddWarrantyPeriodBinding.spinnerone.setEnabled(false);
                        activityAddWarrantyPeriodBinding.spinnertwo.setEnabled(true);
                        activityAddWarrantyPeriodBinding.spinnerthree.setEnabled(false);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        activityAddWarrantyPeriodBinding.spinnertwo.setAdapter(arrayAdapter1);
                        activityAddWarrantyPeriodBinding.spinnerone.setAdapter(null);
                        activityAddWarrantyPeriodBinding.spinnerthree.setAdapter(null);

                        break;


                    case R.id.radiothree:
                        activityAddWarrantyPeriodBinding.spinnerone.setEnabled(false);
                        activityAddWarrantyPeriodBinding.spinnertwo.setEnabled(false);
                        activityAddWarrantyPeriodBinding.spinnerthree.setEnabled(true);
                        activityAddWarrantyPeriodBinding.spinnerone.setSelection(-1);
                        activityAddWarrantyPeriodBinding.spinnertwo.setSelection(-1);
                        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        activityAddWarrantyPeriodBinding.spinnerthree.setAdapter(arrayAdapter2);
                        activityAddWarrantyPeriodBinding.spinnerone.setAdapter(null);
                        activityAddWarrantyPeriodBinding.spinnertwo.setAdapter(null);

                        break;


                }
            }
        });

        activityAddWarrantyPeriodBinding.spinnerone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                km= activityAddWarrantyPeriodBinding.spinnerone.getItemAtPosition(position).toString();
                warranty_period="three_months";
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                todate= df.format(c);
                warranty=new Warranty(warranty_period,km,todate);
                warrenties = new Warrenty(warranty);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        activityAddWarrantyPeriodBinding.spinnertwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                km= activityAddWarrantyPeriodBinding.spinnertwo.getItemAtPosition(position).toString();
                warranty_period="six_months";
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                todate= df.format(c);
                warranty=new Warranty(warranty_period,km,todate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        activityAddWarrantyPeriodBinding.spinnerthree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                km= activityAddWarrantyPeriodBinding.spinnerthree.getItemAtPosition(position).toString();
                warranty_period="one_year";
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                todate= df.format(c);
                warranty=new Warranty(warranty_period,km,todate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }) ;


    }
}
