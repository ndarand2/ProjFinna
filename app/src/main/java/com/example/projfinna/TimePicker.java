package com.example.projfinna;

import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Calendar;

public class TimePicker extends DialogFragment {
    @NonNull
    Calendar calendar = Calendar.getInstance();
    int currentMinute = Calendar.MINUTE;
    int currentHour = Calendar.HOUR_OF_DAY;
    public TimePicker() {
        super();
    }
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), currentHour, currentMinute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}
