package com.example.amanelshark.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;

import com.example.amanelshark.R;
import com.example.amanelshark.Services;
import com.example.amanelshark.adapter.BottomBarAdapter;
import com.example.amanelshark.databinding.ActivityMainHomeBinding;
import com.example.amanelshark.view.fragment.ProfileFragment;
import com.example.amanelshark.view.fragment.RequestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.pushnotifications.PushNotifications;
import com.pusher.pushnotifications.PushNotificationsInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainHomeActivity extends AppCompatActivity {
    ProfileFragment profileFragment = new ProfileFragment();
    RequestFragment requestFragment= new RequestFragment();
    ActivityMainHomeBinding activityMainHomeBinding;
    String x,xx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

    // ...
        activityMainHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_home);

        BottomBarAdapter bottomBarAdapter = new BottomBarAdapter(getSupportFragmentManager());
        bottomBarAdapter.addFragments(profileFragment);
        bottomBarAdapter.addFragments(requestFragment);

        activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
        activityMainHomeBinding.viewPager2.setPagingEnabled(false);
        activityMainHomeBinding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        activityMainHomeBinding.viewPager2.setCurrentItem(0);
                        activityMainHomeBinding.floatingbutton.setVisibility(View.VISIBLE);

                        return true;
                    case R.id.request:
                        activityMainHomeBinding.viewPager2.setCurrentItem(1);
                        activityMainHomeBinding.floatingbutton.setVisibility(View.GONE);
                        return true;
//                    case R.id.tab3:
//                        Intent intent = new Intent(getApplicationContext() , Main2Activity.class);
//                        startActivity(intent);
//                        return true;


                }
                return false;
            }
        });
        PusherOptions options = new PusherOptions();
        options.setCluster("eu");
        Pusher pusher = new Pusher("db9e201a6ca245d31338", options);


        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                System.out.println("State changed from " + change.getPreviousState() +
                        " to " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                System.out.println("There was a problem connecting! " +
                        "\ncode: " + code +
                        "\nmessage: " + message +
                        "\nException: " + e
                );
            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe("my-channel");

        channel.bind("form-submitted", new SubscriptionEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onEvent(PusherEvent event) {
                JSONObject reader = null;
                try {
                    reader = new JSONObject(event.getData()).getJSONObject("aman");
                     x = reader.getString("title");
                     xx=reader.getString("action");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("Received event with data: " + event.toString());
//                String title = data.get("title");
//                String body = data.get("body");
//                Services services = new Services();
//                services.sss("asd","asss");
                // Create an Intent for the activity you want to start
                Intent resultIntent = new Intent(getApplicationContext(), MainHomeActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String NOTIFICATION_CHANNAL_ID = "my-channel";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNAL_ID, "Notifcation", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setDescription("AMAN Channel");
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.BLUE);
                    notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    notificationChannel.enableLights(true);
                    notificationChannel.setShowBadge(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                NotificationCompat.Builder notification_builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNAL_ID);
                notification_builder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(x)
                        .setContentText(xx)
                        .setContentIntent(resultPendingIntent)
                        .setContentInfo("info")
                        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
                notificationManager.notify(new Random().nextInt(), notification_builder.build());

            }} );

        String instanceId = "b9efe592-ffe7-4aeb-8ecc-c049a3d33448";

        String secretKey = "00A040A7E47979367AAB853122B211765CFC6763CBBB8C90E3505BEFC21FE9F4";




        PushNotificationsInstance b =new PushNotificationsInstance(getApplicationContext(),instanceId);
        b.start();
        PushNotifications.addDeviceInterest("hello");
        activityMainHomeBinding.floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddCarActivity.class);
                startActivity(intent);
            }
        });
    }
}
