package com.isaaclevi.event_app;

/*
 * Created by isaac levi on 30/12/2014.
 */

public class Event
{
    String EventId;
    String EventName;
    String UserName;
    String EventExplanation;
    String Time;
    String EventAddress;

    public Event(String eventId, String eventName, String userName, String eventExplanation, String time, String eventAddress)
    {
        EventId = eventId;
        EventName = eventName;
        UserName = userName;
        EventExplanation = eventExplanation;
        Time = time;
        EventAddress = eventAddress;
    }
}