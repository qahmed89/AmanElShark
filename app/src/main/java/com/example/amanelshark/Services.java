package com.example.amanelshark;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotifications;
import com.pusher.pushnotifications.PushNotificationsInstance;
import com.pusher.pushnotifications.fcm.MessagingService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class Services   extends MessagingService {

   int count=0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Here you can put any logic you want to execute when the notification arrives.
        // In the old SDK your logic would have looked something like this:


        Toast.makeText(this, "hiiiii", Toast.LENGTH_SHORT).show();
        if(remoteMessage.getData().isEmpty()){
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

            count = remoteMessage.getNotification().getNotificationCount();
            Log.d(Services.class.getSimpleName(), "Message received via custom handler!");

        }else {

            showNotification(remoteMessage.getData());
            count = remoteMessage.getNotification().getNotificationCount();
            Log.d(Services.class.getSimpleName(), "Message received via custom handler!");


        }
    }
public void sss(String title, String body){
    NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    String NOTIFICATION_CHANNAL_ID="my-channel";
    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNAL_ID, "Notifcation", NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription("AMAN Channel");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableLights(true);
        notificationChannel.setShowBadge(true);
        notificationManager.createNotificationChannel(notificationChannel);
    }
}
    private void showNotification(Map<String, String> data) {
        String title =data.get("title");
        String body=data.get("body");
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNAL_ID="my-channel";
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNAL_ID,"Notifcation",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("AMAN Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableLights(true);
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notification_builder= new  NotificationCompat.Builder(this,NOTIFICATION_CHANNAL_ID);
        notification_builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("info")
                .setNumber(count)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
        notificationManager.notify(new Random().nextInt(),notification_builder.build());
    }

    private void showNotification(String title, String body) {
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNAL_ID=getString(R.string.default_notification_channel_id);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNAL_ID,"Notifcation",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("AMAN Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableLights(true);
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notification_builder= new  NotificationCompat.Builder(this,NOTIFICATION_CHANNAL_ID);
        notification_builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("info")
                .setNumber(count)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
        notificationManager.notify(new Random().nextInt(),notification_builder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
