package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.amanelshark.R;

import java.util.List;

public class AddWarrantyPeriodActivity extends AppCompatActivity {
    private  static final String[] spinn1 = new String[] {"SELECT KM","110000", "120000", "130000"};
    private  static final String[] spinn2= new String[]{"SELECT KM","140000", "150000", "160000"};
    private  static final String[] spinn3 = new String[]{"SELECT KM","170000", "180000", "190000"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warranty_period);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWarrantyPeriodActivity.this,AddCarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Spinner spinner = findViewById(R.id.one);
        Spinner spinner1=findViewById(R.id.two);
        Spinner spinner2=findViewById(R.id.three);
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
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
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,  spinn2);
        ArrayAdapter<String> arrayAdapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,  spinn3);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioone:
                        spinner.setEnabled(true);
                        spinner1.setEnabled(false);
                        spinner2.setEnabled(false);
                        spinner1.setSelection(-1);
                        spinner2.setSelection(-1);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(arrayAdapter);
                        spinner1.setAdapter(null);
                        spinner2.setAdapter(null);
break;

                    case R.id.radiotwo:
                        spinner.setEnabled(false);
                        spinner1.setEnabled(true);
                        spinner2.setEnabled(false);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner1.setAdapter(arrayAdapter1);
                        spinner.setAdapter(null);
                        spinner2.setAdapter(null);

                        break;


                    case R.id.radiothree:
                        spinner.setEnabled(false);
                        spinner1.setEnabled(false);
                        spinner2.setEnabled(true);
                        spinner.setSelection(-1);
                        spinner1.setSelection(-1);
                        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(arrayAdapter2);
                        spinner.setAdapter(null);
                        spinner1.setAdapter(null);
                        break;


                }
            }
        });





    }
}
