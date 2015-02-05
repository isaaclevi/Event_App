package com.isaaclevi.event_app;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by isaac on 24/01/2015.
 * Singleton Model Class
 */

public class Model
{
    private final static Model instance = new Model();
    private boolean valid = true;
    private ErrorCode errorCode;

    private Model()
    {}

    public static Model getInstance()
    {
        return instance;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public enum ErrorCode {OK, NO_USER, NO_MATCH}

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    //-------------------------------------------------------

    public void initializeModel(Context context)
    {
        Parse.initialize(context, "7d5fqJJFVBz6JCVELpK1hFY6BnDA9qBlgDoN6KrB", "kfXP7DUmsTjWp1ITgvKCQk6yabAz8E36HX2lVTer");
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
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Model.getInstance().setValid(parseObjects.size() == 0);
            }
        });
        return isValid();
    }

    public boolean checkPhone(final String phone) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phone);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Model.getInstance().setValid(parseObjects.size() == 0);
            }
        });
        return isValid();
    }

    public void validateUser(String phoneNumber, final String password) {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UsersTable");
        query.whereEqualTo("PhoneNumber", phoneNumber);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects.size() == 0)
                    Model.getInstance().setValid(false);
                else {
                    ParseObject parseObject = parseObjects.get(0);
                    if(password.equals(parseObject.getString("Password")))
                        Model.getInstance().setValid(false);
                    else
                        Model.getInstance().setValid(true);
                }
            }
        });
    }
}
