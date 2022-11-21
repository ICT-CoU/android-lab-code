package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notification extends AppCompatActivity {
    Button notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notify = findViewById(R.id.notify);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(Notification.this, "Notification")
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle("You have a notification")
                                .setContentText("This is just a notification for you.")
                                .setAutoCancel(true);


//                Intent notificationIntent = new Intent(Notification.this, Dialog.class);
//                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                //notification message will get at NotificationView
//                notificationIntent.putExtra("message", "This is a notification message");
//
//                PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, notificationIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//                builder.setContentIntent(pendingIntent);

                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1, builder.build());
            }

        });
    }
}