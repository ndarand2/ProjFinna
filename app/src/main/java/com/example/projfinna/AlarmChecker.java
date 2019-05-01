package com.example.projfinna;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmChecker {
    public void onCreate() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (hour == cal.HOUR_OF_DAY) {
                    if (min == cal.MINUTE) {
                        FullscreenActivity.alarm.start();
                        break;
                    }
                }
            }
        },0,5000);
    }

/*
import java.util.Calendar;

import android.os.AsyncTask;

public class AlarmChecker extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        int hour = SetTime.hour;
        int min = SetTime.min;
        Calendar cal = Calendar.getInstance();
        while (true) {
            if (hour == cal.HOUR_OF_DAY) {
                if (min == cal.MINUTE) {
                    FullscreenActivity.alarm.start();
                    break;
                }
            }
        }
        return null;
    }
    protected void onPostExecute(Void x) {
    }
}
*/
/*
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class AlarmChecker extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(SetTime.TAG, "Checker test");
        FullscreenActivity.alarm.start();
    }
}*/
