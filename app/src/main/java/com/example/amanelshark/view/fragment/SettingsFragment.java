package com.example.amanelshark.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amanelshark.LocaleHelper;
import com.example.amanelshark.R;
import com.example.amanelshark.view.LoginActivity;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String xx="ar";
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
       //token = sharedPref.getString(getString(R.string.token), "null");
        Button button = view.findViewById(R.id.logout_settings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().apply();
                Intent intent = new Intent (getActivity(), LoginActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        Button button1 = view.findViewById(R.id.lang_settings);
        button1.setOnClickListener(v -> {
          xx = LocaleHelper.getLanguage(getContext());

            if( xx.equals("ar")){
          //      LocaleHelper.setLocale(getContext(),"ar");

                button1.setText("english");
              //  xx="en";

                setLocale(xx);

               // LocaleHelper.updateResources(getContext(),"en");

           }else {
              //  LocaleHelper.setLocale(getContext(),"en");

                button1.setText("Arabic");
           //     xx="ar";

                setLocale(xx);
               // LocaleHelper.updateResources(getContext(),"ar");

            }
        });

    }
    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,  getActivity().getBaseContext().getResources().getDisplayMetrics());
getActivity().recreate();

    }
}
