package com.isaaclevi.event_app;

/*
 * Created by Ariel Tzentner on 2/13/2015.
 */

import android.widget.TextView;

public class PickerHelper {

    private static PickerHelper instance = new PickerHelper();

    TextView dateView;
    TextView timeView;

    public PickerHelper() {
    }

    public static PickerHelper getInstance() {
        return instance;
    }

    public void setDate(int year, int month, int day) {
        dateView.setText(day + "/" + (month + 1) + "/" + year);
    }

    public void setTime(int hourOfDay, int minute) {
        timeView.setText(hourOfDay + ":" + ((minute < 10) ? "0" : "") + minute);
    }

    public void setDateView(TextView dateView) {
        this.dateView = dateView;
    }

    public void setTimeView(TextView timeView) {
        this.timeView = timeView;
    }
}
