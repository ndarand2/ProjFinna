package com.example.projfinna;

import android.annotation.SuppressLint;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        Button button = (Button) findViewById(R.id.setButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        int hour = hourOfDay;
        if (hour == 0) {
            hour = 12;
        }
        if (hour > 12) {
            hour = hour % 12;
        }
        String amOrpm = "AM";
        if (Calendar.AM == 0) {
            amOrpm = "PM";
        }
        TextView textView = (TextView)findViewById(R.id.alarmTime);
        textView.setText(hour + ":" + minute + " " + amOrpm);
    }
}
