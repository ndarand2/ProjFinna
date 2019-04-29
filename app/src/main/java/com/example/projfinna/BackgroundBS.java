package com.example.projfinna;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
public class BackgroundBS extends Service {

    private void createNotificationChannel() {

    }

    private  static final int ALERT_ID = 1;
    private static final  String ALERT_CHANNEL_ID = "Channel_Id";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        if (SetTime.getAlarmTime().equals(SetTime.getCurrentTime())) {
            //Make noise
        }
       startForeground();

       return super.onStartCommand(intent, flags, startId);


    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, FullscreenActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        startForeground(ALERT_ID, new NotificationCompat.Builder(this, ALERT_CHANNEL_ID)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running in Background")
                .setContentIntent(pendingIntent)
                .build());
    }
}
*/


public class BackgroundBS extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public String textTitle = "Alarm";
    public String CHANNEL_ID = "Alarm";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("one", "Notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(textTitle)
                .setContentText(textTitle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
        return super.onStartCommand(intent, flags, startId);
    }
}



