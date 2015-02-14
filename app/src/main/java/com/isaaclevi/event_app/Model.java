package com.isaaclevi.event_app;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Vector;

/**
 * Created by isaac on 24/01/2015.
 * Singleton Model Class
 */

public class Model
{
    private static Model instance = new Model();

    private Model()
    {}

    public static Model getInstance()
    {
        return instance;
    }

    //-------------------------------------------------------

    public void initializeModel(Context context) {
        Parse.initialize(context, "7d5fqJJFVBz6JCVELpK1hFY6BnDA9qBlgDoN6KrB", "kfXP7DUmsTjWp1ITgvKCQk6yabAz8E36HX2lVTer");
    }

    public Vector<Event> getAllEvents() {
        Vector<Event> events = new Vector<>();
        ParseQuery query = ParseQuery.getQuery("Events");
        try {
            List<ParseObject> parseObjects = query.find();
            for(ParseObject object: parseObjects){
                Event event = new Event(object.getString("EventName"),
                        object.getString("UserName"),
                        object.getString("EventExplanation"),
                        object.getString("EventTime"),
                        object.getString("EventAddress"));
                events.add(event);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void registerUser(User user) {
        ParseObject ParseUser = new ParseObject("UsersTable");
        ParseUser.put("Nickname", user.NickName);
        ParseUser.put("PersonName", user.PersonName);
        ParseUser.put("PhoneNumber", user.PhoneNumber);
        ParseUser.put("Password", user.Password);
        ParseUser.saveInBackground();
    }

    public boolean checkNickname(final String nickname) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("Nickname", nickname);
        try {
            List<ParseObject> parseObjects = query.find();
            return (parseObjects.size() == 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPhone(final String phone) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phone);
        try {
            List<ParseObject> parseObjects = query.find();
            return (parseObjects.size() == 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateUser(String phoneNumber, final String password) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phoneNumber);
        try {
            List<ParseObject> parseObjects = query.find();
            if (parseObjects.size() == 0)
                return false;
            else {
                ParseObject parseObject = parseObjects.get(0);
                return (password.equals(parseObject.getString("Password")));

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String phoneNumber) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phoneNumber);
        try {
            ParseObject parseObject = query.getFirst();
            return new User(parseObject.getString("Nickname"),
                    parseObject.getString("PersonName"),
                    parseObject.getString("PhoneNumber"),
                    parseObject.getString("Password"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveEvent(Event event) {
        ParseObject object = new ParseObject("Events");
        object.put("EventName", event.EventName);
        object.put("UserName", event.UserName);
        object.put("EventExplanation", event.EventExplanation);
        object.put("EventTime", event.EventTime);
        object.put("EventAddress", event.EventAddress);
        try {
            object.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}