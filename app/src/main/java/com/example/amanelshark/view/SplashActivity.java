package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.example.amanelshark.R;
import com.example.amanelshark.model.login.Login;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                 * if user login test is true on oncreate then redirect the user
                 * to result page
                 */

//                if (str_login_test != null && !str_login_test.toString().trim().equals("")) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                //   }
                /*
                 * if user login test is false on oncreate then redirect the
                 * user to login & registration page
                 */
                //  else {
//                    Intent send = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(send);
                //   }
            }
        },3000); // 3000 = 3seconds


    }
}
