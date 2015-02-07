package com.isaaclevi.event_app;

import android.app.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainScreen extends Activity {

    Model model;
    LogInFragment logInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        model = Model.getInstance();
        model.initializeModel(this);

        logInFragment = new LogInFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, logInFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        AppManager();
    }

    public void AppManager() {
        logInFragment.SetLoginDelegate(new LogInFragment.LoginDelegate() {
            @Override
            public void regClick() {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(logInFragment);
                transaction.add(R.id.fragment_container, registerFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                registerFragment.setRegisterDelegate(new RegisterFragment.RegisterDelegate() {
                    @Override
                    public void register() {
                        getFragmentManager().popBackStack();
                        Context context = getApplicationContext();
                        CharSequence text = "Register Complete.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }

            @Override
            public void loginClick() {
                ListOfEventsFragment eventsFragment = new ListOfEventsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(logInFragment);
                transaction.add(R.id.fragment_container, eventsFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                eventsFragment.SetListOfEventsDelegate(new ListOfEventsFragment.ListOfEventsDelegate() {
                    @Override
                    public void viewEvent(Event event) {
                        EventDetailsFragment detailsFragment = new EventDetailsFragment();

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            viewSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 1)
            this.finish();
        super.onBackPressed();
    }

    public void viewSettings() {
        //Suppose we want to actually do this sometime
    }
}
