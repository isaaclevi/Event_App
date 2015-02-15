package com.isaaclevi.event_app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    String previousDate;

    public void setPreviousDate(String previousDate) {
        this.previousDate = previousDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        int year, month, day;
        if(previousDate == null) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }
        else {
            String date[] = previousDate.split("/");
            day = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]) - 1;
            year = Integer.parseInt(date[2]);
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        PickerHelper.getInstance().setDate(year, month, day);
    }
}
