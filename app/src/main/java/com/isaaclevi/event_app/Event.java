package com.isaaclevi.event_app;

import android.text.format.Time;

import java.util.Calendar;

/**
 * Created by isaac on 30/12/2014.
 */

public class Event
{
    String EventId;
    String EventName;
    String UserName;
    String EventExplanation;
    Calendar Register;
    Calendar StartTime;
    Calendar EndTime;

    public Event(String eventId, String eventName, String userName,String eventExplanation,Calendar register, Calendar startTime, Calendar endTime)
    {
        EventId = eventId;
        EventName = eventName;
        UserName = userName;
        EventExplanation = eventExplanation;
        Register = register;
        StartTime = startTime;
        EndTime = endTime;
    }
}
