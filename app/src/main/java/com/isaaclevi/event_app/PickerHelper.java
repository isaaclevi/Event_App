package com.isaaclevi.event_app;

/*
 * Created by Ariel Tzentner on 2/13/2015.
 */

public class PickerHelper {

    private static PickerHelper instance = new PickerHelper();

    String startDate = "";
    String endDate = "";
    String startTime = "";
    String endTime = "";

    public PickerHelper() {
    }

    public static PickerHelper getInstance() {
        return instance;
    }

    public void setStartDate(int year, int month, int day) {
        startDate = day + "/" + month + "/" + year;
    }

    public void setEndDate(int year, int month, int day) {
        endDate = day + "/" + month + "/" + year;
    }

    public void setStartTime(int hourOfDay, int minute) {
        startTime = hourOfDay + ":" + minute;
    }

    public void setEndTime(int hourOfDay, int minute) {
        endTime = hourOfDay + ":" + minute;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
