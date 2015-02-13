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
        ParseQuery query = new ParseQuery("Events");
        try {
            List<ParseObject> result = query.find();
            for(ParseObject object: result){
                Event event = new Event(object.getString("EventId"),
                        object.getString("EventName"),
                        object.getString("UserName"),
                        object.getString("EventExplanation"),
                        object.getString("CreateDate"),
                        object.getString("StartTime"),
                        object.getString("EndTime"));
                events.add(event);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void registerUser(User user) {
        ParseObject ParseUser = new ParseObject("UsersTable");
        ParseUser.put("Nickname", user.Nickname);
        ParseUser.put("PersonName", user.PersonName);
        ParseUser.put("PhoneNumber", user.PhoneNumber);
        ParseUser.put("Password", user.Password);
        ParseUser.saveInBackground();
    }

    public boolean checkNickname(final String nickname) {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("Nickname", nickname);
        try {
            List<ParseObject> parseObjects = query.find();
            if (parseObjects.size() == 0)
                return true;
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
            if (parseObjects.size() == 0)
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateUser(String phoneNumber, final String password) {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phoneNumber);
        try {
            List<ParseObject> parseObjects = query.find();
            if (parseObjects.size() == 0)
                return false;
            else {
                ParseObject parseObject = parseObjects.get(0);
                return !(password.equals(parseObject.getString("Password")));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}