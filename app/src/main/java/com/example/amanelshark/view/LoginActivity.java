package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.LocaleHelper;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityLoginBinding;
import com.example.amanelshark.model.login.Login;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.example.amanelshark.viewmodel.NewViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class LoginActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    Context context;
    String mLanguageCode = "ar";
    TextInputEditText email;
    TextInputEditText password;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    List<Login> login;
    ActivityLoginBinding activityLoginBinding;
    int i = 0;
    String x = "";
    private AmanElsharkViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.setLocale(LoginActivity.this, mLanguageCode);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        //It is required to recreate the activity to reflect the change in UI.
        activityLoginBinding.button.setOnClickListener(v -> {
            activityLoginBinding.progressBar.setVisibility(View.VISIBLE);
            userViewModel.isLoading().observe(this, isLoading -> {
                if(isLoading == true) {
                    activityLoginBinding.progressBar.setVisibility(View.VISIBLE);
                }else   activityLoginBinding.progressBar.setVisibility(View.GONE);

            });
            userViewModel.errorMessage().observe(this, errorMessage -> {
                if (errorMessage != null) {
                   // activityLoginBinding.progressBar.setVisibility(View.VISIBLE);
                    Snackbar.make(v, "error", Snackbar.LENGTH_LONG).show();


                } else { //activityLoginBinding.progressBar.setVisibility(View.GONE);
                Snackbar.make(v, "done", Snackbar.LENGTH_LONG).show();}
            });

//            userViewModel.errorMessage().observe(this, new Observer<String>() {
//
//                @Override
//                public void onChanged(String s) {
//                    if (s != null) {
//                        x = s;
//                        Snackbar.make(v, "error", Snackbar.LENGTH_LONG).show();
//                        userViewModel.clear(LoginActivity.this);
//                        activityLoginBinding.progressBar.setVisibility(View.GONE);
//                        userViewModel.errorMessage().removeObservers(LoginActivity.this);
//                    } else activityLoginBinding.progressBar.setVisibility(View.GONE);
//
//                }
//            });
            userViewModel.getloginRequests(getApplicationContext(), activityLoginBinding.emailLogin.getText().toString(), activityLoginBinding.passwordLogin.getText().toString(), getCurrentFocus()).observe(this, new Observer<Login>() {
                @Override
                public void onChanged(Login login) {

                    editor.putString(getString(R.string.token), "Bearer " + login.getToken()).apply();
                    i++;
                    activityLoginBinding.progressBar.setVisibility(View.GONE);
                     Snackbar.make(v, "sccess", Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,OnBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        });
        activityLoginBinding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
}
