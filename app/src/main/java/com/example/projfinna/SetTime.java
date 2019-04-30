package com.example.projfinna;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SetTime extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static String currentTime = Calendar.HOUR + ":" + Calendar.MINUTE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button = (Button) findViewById(R.id.setButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        TextView textView = (TextView)findViewById(R.id.alarmTime);
        textView.setText(FullscreenActivity.getALARMTIME());
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        String stringMinute = "" + minute;
        String amOrpm = "AM";
        if (hourOfDay >= 12) {
            amOrpm = "PM";
        }
        int hour = hourOfDay;
        if (hour == 0) {
            hour = 12;
        }
        if (hour > 12) {
            hour = hour % 12;
        }
        if (minute < 10) {
            stringMinute = "0" + minute;
        }
        android.content.SharedPreferences preferences = getSharedPreferences("sharedPrefs", 0);
        android.content.SharedPreferences.Editor pref = preferences.edit();
        FullscreenActivity.ALARMTIME = hour + ":" + stringMinute + " " + amOrpm;
        pref.putString("ALARMTIME", FullscreenActivity.ALARMTIME);
        TextView textView = (TextView)findViewById(R.id.alarmTime);
        textView.setText(FullscreenActivity.getALARMTIME());
        pref.commit();
    }
//f
    public static String getCurrentTime() {
        return currentTime;
    }

}
