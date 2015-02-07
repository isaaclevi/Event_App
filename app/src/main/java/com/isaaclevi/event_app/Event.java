package com.isaaclevi.event_app;

/**
 * Created by isaac on 30/12/2014.
 */

public class Event
{
    String EventId;
    String EventName;
    String UserName;
    String EventExplanation;
    String Register;
    String StartTime;
    String EndTime;

    public Event(String eventId, String eventName, String userName,String eventExplanation,String register, String startTime, String endTime)
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