package com.isaaclevi.event_app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

/**
 * Created by isaac on 12/02/2015.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    boolean startOrEnd;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        if(startOrEnd)
            PickerHelper.getInstance().setStartDate(year, month, day);
        else
            PickerHelper.getInstance().setEndDate(year, month, day);
    }

    public void setStartOrEnd(boolean startOrEnd) {
        this.startOrEnd = startOrEnd;
    }
}
