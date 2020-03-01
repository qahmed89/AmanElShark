package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.example.amanelshark.R;
import com.example.amanelshark.model.login.Login;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.pushnotifications.PushNotifications;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    String lang_Defualt = "ar";
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");

        PushNotifications.start(getApplicationContext(), "b9efe592-ffe7-4aeb-8ecc-c049a3d33448");
        PushNotifications.addDeviceInterest("hello");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                 * if user login test is true on oncreate then redirect the user
                 * to result page
                 */
                if (!token.equals("null")) {
//                if (str_login_test != null && !str_login_test.toString().trim().equals("")) {
                    setLocale(lang_Defualt);
                    Intent refresh = new Intent(getApplicationContext(), MainHomeActivity.class);
                    finish();
                    startActivity(refresh);
//                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
                } else {
                    setLocale(lang_Defualt);

                    Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                    finish();
                    startActivity(refresh);
                }

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
        }, 2000); // 3000 = 3seconds


    }

    public void setLocale(String lang) {
//        Locale myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
                        Locale locale = new Locale(lang);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }
}
