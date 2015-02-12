package com.isaaclevi.event_app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by isaac on 30/12/2014.
 */

public class Event
{
    String EventId;
    String EventName;
    String UserName;
    String EventExplanation;
    String StartTime;

    public Event(String eventId, String eventName, String userName,String eventExplanation,String createDate, String startTime, String endTime)
    {
        EventId = eventId;
        EventName = eventName;
        UserName = userName;
        EventExplanation = eventExplanation;
        StartTime = startTime;
    }

    public String getEventId() {
        return EventId;
    }

    public void setEventId(String eventId) {
        EventId = eventId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEventExplanation() {
        return EventExplanation;
    }

    public void setEventExplanation(String eventExplanation) {
        EventExplanation = eventExplanation;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
}