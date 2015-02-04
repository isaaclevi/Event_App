package com.isaaclevi.event_app;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by isaac on 24/01/2015.
 */
public class Model
{
    private final static Model instance = new Model();

    private Model()
    {}

    public static Model getModel()
    {
        return instance;
    }

    //-------------------------------------------------------

    public void InitDB(Activity activity)
    {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(activity);

        Parse.initialize(activity, "7d5fqJJFVBz6JCVELpK1hFY6BnDA9qBlgDoN6KrB", "kfXP7DUmsTjWp1ITgvKCQk6yabAz8E36HX2lVTer");
    }

    public void registerUser(User user) {
        ParseObject ParseUser = new ParseObject("UsersTable");
        ParseUser.put("Nickname", user.Nickname);
        ParseUser.put("PersonName", user.PersonName);
        ParseUser.put("PhoneNumber", user.PhoneNumber);
        ParseUser.put("Password", user.Password);
        ParseUser.saveInBackground();

        try
        {
            ParseUser.save();
        }

        catch (com.parse.ParseException e)
        {
            e.printStackTrace();
        }

    }
}
