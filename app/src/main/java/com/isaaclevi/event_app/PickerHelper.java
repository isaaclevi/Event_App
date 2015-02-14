package com.isaaclevi.event_app;

/*
 * Created by Ariel Tzentner on 2/13/2015.
 */

import android.widget.Button;

public class PickerHelper {

    private static PickerHelper instance = new PickerHelper();

    Button dateButton;
    Button timeButton;

    public PickerHelper() {
    }

    public static PickerHelper getInstance() {
        return instance;
    }

    public void setDate(int year, int month, int day) {
        dateButton.setText(day + "/" + (month + 1) + "/" + year);
    }

    public void setTime(int hourOfDay, int minute) {
        timeButton.setText(hourOfDay + ":" + minute);
    }

    public void setDateButton(Button dateButton) {
        this.dateButton = dateButton;
    }

    public void setTimeButton(Button timeButton) {
        this.timeButton = timeButton;
    }
}
