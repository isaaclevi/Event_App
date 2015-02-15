package com.isaaclevi.event_app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    String previousTime;

    public void setPreviousTime(String previousTime) {
        this.previousTime = previousTime;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour, minute;
        if(previousTime == null) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        }
        else {
            String date[] = previousTime.split(":");
            hour = Integer.parseInt(date[0]);
            minute = Integer.parseInt(date[1]);
        }

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        PickerHelper.getInstance().setTime(hourOfDay, minute);
    }
}