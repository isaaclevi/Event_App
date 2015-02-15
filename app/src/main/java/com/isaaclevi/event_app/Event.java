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
    String EventTime;
    String EventAddress;

    public Event()
    {}

    public Event(String eventId,String eventName, String userName, String eventExplanation, String eventTime, String eventAddress)
    {
        EventId = eventId;
        EventName = eventName;
        UserName = userName;
        EventExplanation = eventExplanation;
        EventTime = eventTime;
        EventAddress = eventAddress;
    }

    public String getEventId() {return EventId;}

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setEventExplanation(String eventExplanation) {
        EventExplanation = eventExplanation;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }

    public void setEventAddress(String eventAddress) {
        EventAddress = eventAddress;
    }
}