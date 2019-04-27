package com.example.projfinna;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class TimePicker extends DialogFragment {
    @NonNull
    //@Override
    Calendar calendar = Calendar.getInstance();
    int currentHour = Calendar.HOUR_OF_DAY;
    int currentMinute = Calendar.MINUTE;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), currentHour, currentMinute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}
