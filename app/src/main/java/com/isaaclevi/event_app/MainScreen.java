package com.isaaclevi.event_app;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainScreen extends Activity {

    Model model;
    LogInFragment logInFragment;
    MenuItem AddEventButton;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        model = Model.getInstance();
        model.initializeModel(this);

        logInFragment = new LogInFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, logInFragment, "LogInFragment");
        transaction.addToBackStack(null);
        transaction.commit();
        OpenLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        AddEventButton = menu.findItem(R.id.add_event_to_list);
        AddEventButton.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.add_event_to_list:
                OpenAddEvent();
                break;
            case R.id.action_settings:
                viewSettings();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OpenLogin() {
        logInFragment.SetLoginDelegate(new LogInFragment.LoginDelegate() {
            @Override
            public void regClick() {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(logInFragment);
                transaction.add(R.id.fragment_container, registerFragment, "RegisterFragment");
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
                final ListOfEventsFragment eventsFragment = new ListOfEventsFragment();
                eventsFragment.SetListOfEventsDelegate(new ListOfEventsFragment.ListOfEventsDelegate() {
                    @Override
                    public void viewEvent(Event event) {
                        EventDetailsFragment detailsFragment = new EventDetailsFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.remove(eventsFragment);
                        transaction.add(R.id.fragment_container, detailsFragment, "EventDetailsFragment");
                        transaction.addToBackStack(null);
                        transaction.commit();
                        AddEventButton.setVisible(false);
                    }
                });
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(logInFragment);
                transaction.add(R.id.fragment_container, eventsFragment, "ListOfEventsFragment");
                transaction.addToBackStack(null);
                transaction.commit();
                AddEventButton.setVisible(true);
            }

            @Override
            public void setUser(User user) {
                currentUser = user;
            }
        });
    }

    private void OpenAddEvent() {
        AddEventFragment addEventFrag=new AddEventFragment();
        addEventFrag.setDelegate(new AddEventFragment.AddEventDelegate() {
            @Override
            public void add() {
                getFragmentManager().popBackStack();
                AddEventButton.setVisible(true);
            }
        });
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.remove(getFragmentManager().findFragmentByTag("ListOfEventsFragment"));
        transaction.add(R.id.fragment_container,addEventFrag,"AddEventFragment");
        transaction.addToBackStack("null");
        transaction.commit();
        AddEventButton.setVisible(false);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 1)
            this.finish();
        else {
            Fragment fragment = getFragmentManager().findFragmentByTag("AddEventFragment");
            if(fragment != null) {
                if (getFragmentManager().findFragmentByTag("AddEventFragment").isVisible())
                {
                    AddEventButton.setVisible(true);
                    //getFragmentManager().popBackStack(); ----> this line isn't needed in my humble opinion
                }
            }
        }
        super.onBackPressed(); //------------------------------> because this line does it automatically
    }

    public void viewSettings() {
        //Suppose we want to actually do this sometime
    }
}
