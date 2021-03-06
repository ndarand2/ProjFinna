package com.example.projfinna;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AlarmNoise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_noise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        Log.d(SetTime.TAG, "close kids");
        FullscreenActivity.alarm.start();
        TextView textView = (TextView) findViewById(R.id.TimeShown);
        textView.setText(FullscreenActivity.getALARMTIME());
        textView.setTextSize(36);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        android.widget.Button snooze = findViewById(R.id.fakeButton);
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullscreenActivity.alarm.setVolume((float) (1 - (Math.log(0) / Math.log(1))), (float) (1 - (Math.log(0) / Math.log(1))));
            }
        });
        android.widget.Button kill = findViewById(R.id.KillAlarm);
        kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullscreenActivity.alarm.stop();
                finish();
            }
        });
    }


}
