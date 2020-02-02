package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;

import com.developer.kalert.KAlertDialog;
import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityLoginBinding;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.shreyaspatil.MaterialDialog.AbstractDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$");
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityLoginBinding activityLoginBinding;
    int i = 0;
    String x = "";
    private AmanElsharkViewModel userViewModel;

    private static void disable(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                disable((ViewGroup) child);
            } else {
                child.setEnabled(false);
            }
        }
    }

    private static void enable(ViewGroup layout) {
        layout.setEnabled(true);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                enable((ViewGroup) child);
            } else {
                child.setEnabled(true);
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        activityLoginBinding.button.setOnClickListener(v -> {
                    if (!validateEmail() | !validatePassword()) {
                        activityLoginBinding.placeholderLogin.layout.setVisibility(View.GONE);
                        return;
                    } else
                        activityLoginBinding.placeholderLogin.layout.setVisibility(View.VISIBLE);
                    userViewModel.isLoading().observe(this, isLoading -> {
                        if (isLoading == true) {
                            activityLoginBinding.placeholderLogin.layout.setVisibility(View.VISIBLE);
                            disable(activityLoginBinding.loginConstraint);
                        } else {
                            activityLoginBinding.placeholderLogin.layout.setVisibility(View.GONE);
                            enable(activityLoginBinding.loginConstraint);
                        }

                    });
                    userViewModel.errorMessage().observe(this, errorMessage -> {
                        if (errorMessage != null) {
                            activityLoginBinding.placeholderLogin.layout.setVisibility(View.VISIBLE);


                        } else {
                            activityLoginBinding.placeholderLogin.layout.setVisibility(View.GONE);
                        }
                    });


                    userViewModel.getloginRequests(v, activityLoginBinding.emailLogin.getText().toString(), activityLoginBinding.passwordLogin.getText().toString()).observe(this, new Observer<Login>() {
                        @Override
                        public void onChanged(Login login) {

                            if (login.getClientAccount().getToken() != null) {
                                editor.putString(getString(R.string.token), "Bearer " + login.getClientAccount().getToken()).apply();
                                i++;
                                activityLoginBinding.placeholderLogin.layout.setVisibility(View.GONE);
                                Intent intent = new Intent(LoginActivity.this, OnBoardActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });
//            new KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
//                    .setTitleText("Good job!")
//                    .show();
       });


        activityLoginBinding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, LoginActivity.class);
        finish();
        startActivity(refresh);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();


    }

    private boolean validateEmail() {
        String emailInput = activityLoginBinding.emailLogin.getText().toString().trim();

        if (emailInput.isEmpty()) {
            activityLoginBinding.emailLogin.setError(getString(R.string.field_empty_email));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            activityLoginBinding.emailLogin.setError(getString(R.string.vaild_email));
            return false;
        } else {
            activityLoginBinding.emailLogin.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = activityLoginBinding.passwordLogin.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            activityLoginBinding.passwordLogin.setError(getString(R.string.field_empty_password));
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            activityLoginBinding.passwordLogin.setError(getString(R.string.password_8_charaters));
            return false;
        } else {
            activityLoginBinding.passwordLogin.setError(null);
            return true;
        }
    }
}
