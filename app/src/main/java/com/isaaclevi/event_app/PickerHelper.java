package com.isaaclevi.event_app;

/*
 * Created by Ariel Tzentner on 2/13/2015.
 */

import android.widget.Button;

public class PickerHelper {

    private static PickerHelper instance = new PickerHelper();

    Button startDateButton;
    Button startTimeButton;
    Button endDateButton;
    Button endTimeButton;

    public PickerHelper() {
    }

    public static PickerHelper getInstance() {
        return instance;
    }

    public void setStartDate(int year, int month, int day)
    {
        startDateButton.setText(day + "/" + (month+1) + "/" + year);
    }

    public void setEndDate(int year, int month, int day) {
        endDateButton.setText(day + "/" + (month+1) + "/" + year);
    }

    public void setStartTime(int hourOfDay, int minute) {
        startTimeButton.setText(hourOfDay + ":" + minute);
    }

    public void setEndTime(int hourOfDay, int minute) {
        endTimeButton.setText(hourOfDay + ":" + minute);
    }

    public void setStartDateButton(Button startDateButton) {
        this.startDateButton = startDateButton;
    }

    public void setStartTimeButton(Button startTimeButton) {
        this.startTimeButton = startTimeButton;
    }

    public void setEndDateButton(Button endDateButton) {
        this.endDateButton = endDateButton;
    }

    public void setEndTimeButton(Button endTimeButton) {
        this.endTimeButton = endTimeButton;
    }
}
