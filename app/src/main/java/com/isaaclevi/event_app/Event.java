package com.isaaclevi.event_app;

import android.text.format.Time;

/**
 * Created by isaac on 30/12/2014.
 */
public class Event
{
    String EventId;
    String EventName;
    String UserName;
    String EventExplanation;
    Time StartTime;
    Time EndTime;

    public Event(String eventId, String eventName, String userName,String eventExplanation, Time startTime, Time endTime)
    {
        EventId = eventId;
        EventName = eventName;
        UserName = userName;
        EventExplanation = eventExplanation;
        StartTime = startTime;
        EndTime = endTime;
    }




}
