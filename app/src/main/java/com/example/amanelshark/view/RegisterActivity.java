package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityLoginBinding;
import com.example.amanelshark.databinding.ActivityRegisterBinding;
import com.example.amanelshark.model.register.Register;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {
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
    ActivityRegisterBinding activityRegisterBinding;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);


        activityRegisterBinding.submitRegister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                activityRegisterBinding.placeholderRegister.layout.setVisibility(View.VISIBLE);

                if (!validateEmail() | !validatePassword()) {
                    activityRegisterBinding.placeholderRegister.layout.setVisibility(View.GONE);
                    return;
                } else
                    userViewModel.isLoading().observe(RegisterActivity.this, isLoading -> {
                        if (isLoading == true) {
                            activityRegisterBinding.placeholderRegister.layout.setVisibility(View.VISIBLE);
                            disable(activityRegisterBinding.registerLayout);
                        } else {
                            activityRegisterBinding.placeholderRegister.layout.setVisibility(View.GONE);
                            enable(activityRegisterBinding.registerLayout);
                        }

                    });
                userViewModel.errorMessage().observe(RegisterActivity.this, errorMessage -> {
                    if (errorMessage != null) {
                        activityRegisterBinding.placeholderRegister.layout.setVisibility(View.VISIBLE);


                    } else {
                        activityRegisterBinding.placeholderRegister.layout.setVisibility(View.GONE);
                    }
                });
                userViewModel.getregisterRequests(activityRegisterBinding.emailRegister.getText().toString(), activityRegisterBinding.passwordRegister.getText().toString(), activityRegisterBinding.cPasswordRegister.getText().toString(), activityRegisterBinding.phoneRegister.getText().toString(), activityRegisterBinding.nameRegister.getText().toString(), v).observe(RegisterActivity.this, new Observer<Register>() {
                    @Override
                    public void onChanged(Register register) {
                        Intent intent = new Intent(getApplicationContext(), OnBoardActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });

    }

    private boolean validateEmail() {
        String emailInput = activityRegisterBinding.emailRegister.getText().toString().trim();

        if (emailInput.isEmpty()) {
            activityRegisterBinding.emailRegister.setError(getString(R.string.field_empty_email));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            activityRegisterBinding.emailRegister.setError(getString(R.string.vaild_email));
            return false;
        } else {
            activityRegisterBinding.emailRegister.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = activityRegisterBinding.passwordRegister.getText().toString().trim();
        String passwordInput2 = activityRegisterBinding.cPasswordRegister.getText().toString().trim();

        if (passwordInput.isEmpty() | passwordInput2.isEmpty()) {
            activityRegisterBinding.passwordRegister.setError(getString(R.string.field_empty_password));
            activityRegisterBinding.cPasswordRegister.setError(getString(R.string.field_empty_password));

            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches() | !PASSWORD_PATTERN.matcher(passwordInput2).matches()) {
            activityRegisterBinding.passwordRegister.setError(getString(R.string.password_8_charaters));
            activityRegisterBinding.cPasswordRegister.setError(getString(R.string.password_8_charaters));

            return false;
        } else if (!passwordInput.equals(passwordInput2) || !passwordInput2.equals(passwordInput)) {
            activityRegisterBinding.passwordRegister.setError(getString(R.string.pasword_match));
            activityRegisterBinding.cPasswordRegister.setError(getString(R.string.pasword_match));

            return false;
        } else {
            activityRegisterBinding.passwordRegister.setError(null);
            activityRegisterBinding.cPasswordRegister.setError(null);

            return true;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
