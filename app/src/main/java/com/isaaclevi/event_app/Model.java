package com.isaaclevi.event_app;

import android.app.Activity;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
    }

    public boolean checkNickname(final String nickname) {
        final boolean[] valid = new boolean[1];
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("Nickname", nickname);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                valid[0] = (object == null) || !nickname.equals(object.getString("Nickname"));
            }
        });
        return valid[0];
    }

    public boolean checkPhone(final String phone) {
        final boolean[] valid = new boolean[1];
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phone);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                valid[0] = (object == null) || !phone.equals(object.getString("PhoneNumber"));
            }
        });
        return valid[0];
    }
}
