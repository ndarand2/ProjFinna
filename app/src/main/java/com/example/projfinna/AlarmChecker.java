package com.example.projfinna;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmChecker {
    public static java.util.Date temp;
    public static int hour;
    public static int min;
    public static int delay;
    public void onCreate() {
    }
    public void alarmTrigger() {
        Timer timer = new Timer();
        hour = SetTime.hour;
        min = SetTime.min;
        System.out.println("running1");
        timer.schedule(new TimerTask() {
            public void run() {
                temp = Calendar.getInstance().getTime();
                System.out.println("running" + " " + hour + " " + temp.getHours() + " " + min + " " + temp.getMinutes());
                if (hour == temp.getHours()) {
                    if (min == temp.getMinutes()) {
                        if (delay <= 0) {
                            FullscreenActivity.alarm.start();
                            System.out.println("alarmTriggerCall");
                            delay = 6;
                        } else {
                            delay--;
                        }
                    }
                }
            }
        }, 0, 10000);
    }
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
