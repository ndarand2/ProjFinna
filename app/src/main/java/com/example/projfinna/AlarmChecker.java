package com.example.projfinna;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmChecker extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("kek");
        FullscreenActivity.alarm.start();
    }
}
