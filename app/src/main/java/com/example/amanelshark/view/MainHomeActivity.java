package com.example.amanelshark.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.Toast;

import com.example.amanelshark.R;
import com.example.amanelshark.Services;
import com.example.amanelshark.adapter.BottomBarAdapter;
import com.example.amanelshark.databinding.ActivityMainHomeBinding;
import com.example.amanelshark.view.fragment.NotificationFragment;
import com.example.amanelshark.view.fragment.ProfileFragment;
import com.example.amanelshark.view.fragment.RequestFragment;
import com.example.amanelshark.view.fragment.SettingsFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;
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
    RequestFragment requestFragment = new RequestFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    ActivityMainHomeBinding activityMainHomeBinding;
    String x, xx;
    int viewpager_postion = 5;
    BottomBarAdapter bottomBarAdapter;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main_home);

        activityMainHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_home);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");

        bottomBarAdapter = new BottomBarAdapter(getSupportFragmentManager());
        bottomBarAdapter.addFragments(profileFragment);
        bottomBarAdapter.addFragments(requestFragment);
        bottomBarAdapter.addFragments(notificationFragment);
        bottomBarAdapter.addFragments(settingsFragment);
        activityMainHomeBinding.viewPager2.setOffscreenPageLimit(4);
        activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
        activityMainHomeBinding.viewPager2.setPagingEnabled(false);

        Intent intent = getIntent();
        viewpager_postion = intent.getIntExtra("viewpager_position", 5);

        activityMainHomeBinding.bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.home:
                    bottomBarAdapter.getRegisteredFragment(0);
                    activityMainHomeBinding.swip.setEnabled(true);

                    activityMainHomeBinding.viewPager2.setCurrentItem(0);
                    activityMainHomeBinding.floatingbutton.setVisibility(View.VISIBLE);
                    activityMainHomeBinding.toolbar.setTitle(R.string.home_title_bar);
                    BadgeDrawable badge = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(menuItem.getItemId());
                    badge.setVisible(false);
                    return true;
                case R.id.request:
                    bottomBarAdapter.getRegisteredFragment(1);
                    activityMainHomeBinding.swip.setEnabled(true);

                    activityMainHomeBinding.viewPager2.setCurrentItem(1);
                    activityMainHomeBinding.floatingbutton.setVisibility(View.GONE);
                    activityMainHomeBinding.toolbar.setTitle(R.string.requests_title_bar);

                    BadgeDrawable badges = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(menuItem.getItemId());
                    badges.setVisible(false);
                    //  BadgeDrawable badge = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(R.id.request);
                    //   badge.setVisible(true);
                    return true;
                case R.id.notification:
                    bottomBarAdapter.getRegisteredFragment(2);
                    activityMainHomeBinding.swip.setEnabled(true);
                    activityMainHomeBinding.viewPager2.setCurrentItem(2);
                    activityMainHomeBinding.floatingbutton.setVisibility(View.GONE);
                    activityMainHomeBinding.toolbar.setTitle(R.string.notification_title_bar);
                    BadgeDrawable badgess = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(menuItem.getItemId());
                    badgess.setVisible(false);

                    //  BadgeDrawable badge = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(R.id.request);
                    //   badge.setVisible(true);
                    return true;
                case R.id.settings:
                    bottomBarAdapter.getRegisteredFragment(3);

                    activityMainHomeBinding.viewPager2.setCurrentItem(3);
                    activityMainHomeBinding.floatingbutton.setVisibility(View.GONE);
                    activityMainHomeBinding.toolbar.setTitle(R.string.settings_title_bar);
                    activityMainHomeBinding.swip.setEnabled(false);
                    BadgeDrawable badgesss = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(menuItem.getItemId());
                    badgesss.setVisible(false);

                    //  BadgeDrawable badge = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(R.id.request);
                    //   badge.setVisible(true);
                    return true;
//                    case R.id.tab3:
//                        Intent intent = new Intent(getApplicationContext() , Main2Activity.class);
//                        startActivity(intent);
//                        return true;


            }
            return false;
        });
        if (viewpager_postion != 5) {

            viewpager_track(viewpager_postion);

        }
//        HashMap<String, String> headers = new HashMap<>();
//       headers.put("Authorization", token);
//        headers.put("Content-Type", "application/x-www-form-urlencoded");
//        headers.put("Accept", "application/json");
//        final HttpAuthorizer authorizer = new HttpAuthorizer("wss://ws-eu.pusher.com:443/app/db9e201a6ca245d31338?protocol=7");
//        authorizer.setHeaders(headers);
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

        channel.bind("form-submitted", event -> {
            JSONObject reader = null;
            try {
                reader = new JSONObject(event.getData()).getJSONObject("aman");
                x = reader.getString("title");
                xx = reader.getString("description");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("Received event with data: " + event.toString());

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

        });
        activityMainHomeBinding.swip.setOnRefreshListener(() -> {
            if (activityMainHomeBinding.viewPager2.getCurrentItem() == 0) {
                bottomBarAdapter.setFragmentsFragments(profileFragment, 0);
                activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
                activityMainHomeBinding.swip.setRefreshing(false);
                activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.home);
            }
            if (activityMainHomeBinding.viewPager2.getCurrentItem() == 1) {
                bottomBarAdapter.setFragmentsFragments(requestFragment, 1);
                activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
                activityMainHomeBinding.swip.setRefreshing(false);
                activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.request);
            }
            if (activityMainHomeBinding.viewPager2.getCurrentItem() == 2) {
                bottomBarAdapter.setFragmentsFragments(notificationFragment, 2);
                activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
                activityMainHomeBinding.swip.setRefreshing(false);
                activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.notification);
            }

        });

        String instanceId = "b9efe592-ffe7-4aeb-8ecc-c049a3d33448";

        String secretKey = "00A040A7E47979367AAB853122B211765CFC6763CBBB8C90E3505BEFC21FE9F4";

        //<<<<<<<<<<<<<<<<<<<<< subscribeToChannel();
        PushNotificationsInstance b = new PushNotificationsInstance(getApplicationContext(), instanceId);
        b.start();
        PushNotifications.addDeviceInterest("hello");
        activityMainHomeBinding.floatingbutton.setOnClickListener(v -> {

            Intent intent1 = new Intent(getApplicationContext(), AddCarActivity.class);
            startActivity(intent1);
        });
    }

    public void viewpager_track(int viewpager_postion) {
        activityMainHomeBinding.viewPager2.setCurrentItem(viewpager_postion);
        BadgeDrawable badge = activityMainHomeBinding.bottomNavigationView.getOrCreateBadge(R.id.request);
        switch (viewpager_postion) {
            case 0:
                activityMainHomeBinding.viewPager2.setCurrentItem(0);
                activityMainHomeBinding.bottomNavigationView.setSelectedItemId(0);
                break;
            case 1:
                activityMainHomeBinding.viewPager2.setCurrentItem(1);
                activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.request);
                badge.setVisible(true);
                break;

            case 2:
                activityMainHomeBinding.viewPager2.setCurrentItem(2);
                activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.notification);
                break;

//                    case R.id.tab3:
//                        Intent intent = new Intent(getApplicationContext() , Main2Activity.class);
//                        startActivity(intent);
//                        return true;

//               activityMainHomeBinding.swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        if (activityMainHomeBinding.viewPager2.getCurrentItem()==0) {
//                            bottomBarAdapter.setFragmentsFragments(profileFragment, 0);
//                            activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
//                            activityMainHomeBinding.swip.setRefreshing(false);
//                            activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.home);
//                        }
//                        if (activityMainHomeBinding.viewPager2.getCurrentItem()==1) {
//                            bottomBarAdapter.setFragmentsFragments(requestFragment, 1);
//                            activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
//                            activityMainHomeBinding.swip.setRefreshing(false);
//                            activityMainHomeBinding.bottomNavigationView.setSelectedItemId(R.id.request);
//                    }
//                }});


        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (activityMainHomeBinding.viewPager2.getCurrentItem() == 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
//    void subscribeToChannel() {
//        channel = pusher.subscribePrivate("private-my-channel", new PrivateChannelEventListener() {
//            @Override
//            public void onEvent(PusherEvent event) {
//                Toast.makeText(MainHomeActivity.this, "done", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSubscriptionSucceeded(String s) {
//                bindToEvents();
//            }
//            @Override
//            public void onAuthenticationFailure(String s, Exception e) {
//                Toast.makeText(MainHomeActivity.this, "error "+s, Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//        pusher.connect();
//    }
//
//    void bindToEvents() {
//        channel.bind("form-submitted", new PrivateChannelEventListener() {
//            @Override
//            public void onEvent(PusherEvent event) {
//                Toast.makeText(MainHomeActivity.this, "error "+event, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onAuthenticationFailure(String string, Exception ex) {
//                Toast.makeText(MainHomeActivity.this, "error "+string, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onSubscriptionSucceeded(String string) {
//                Toast.makeText(MainHomeActivity.this, "error "+string, Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//    }

}
