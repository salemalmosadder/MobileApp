package com.example.salemalmosadderd308.UI;

import static android.content.Context.NOTIFICATION_SERVICE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;


import com.example.salemalmosadderd308.R;

public class MyReceiver extends BroadcastReceiver {

    String channel_id = "test";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG).show();

        createNotificationChannel(context, channel_id);

        Notification n = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("NotificationTest").build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, n);

    }


    private void createNotificationChannel(Context context, String CHANNEL_ID ){
        CharSequence name = "vacationChannel";
        String description = "vacationAlerts";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setDescription(description);
        }


        NotificationManager notificationManager =  context.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }

    }
}