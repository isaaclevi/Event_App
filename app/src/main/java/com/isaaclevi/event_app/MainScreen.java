package com.isaaclevi.event_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainScreen extends FragmentActivity {

    public static FragmentManager fragmentManager;

    Model model;
    LogInFragment logInFragment;
    MenuItem AddEventButton;
    MenuItem SaveLocationButton;
    User currentUser;
    EventDetailsFragment.EventDetailsFragmentDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        model = Model.getInstance();
        model.initializeModel(this);

        fragmentManager = getFragmentManager();

        logInFragment = new LogInFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, logInFragment, "LogInFragment");
        transaction.addToBackStack(null);
        transaction.commit();
        openLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        AddEventButton = menu.findItem(R.id.add_event_to_list);
        AddEventButton.setVisible(false);
        SaveLocationButton = menu.findItem(R.id.save_event_location);
        SaveLocationButton.setVisible(false);
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
            case R.id.save_event_location:
                saveLocation();
                break;
            case R.id.add_event_to_list:
                openAddEvent();
                break;
            case R.id.action_settings:
                viewSettings();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveLocation() {
        if(delegate != null)
            delegate.save();
    }

    public void openLogin() {
        logInFragment.SetLoginDelegate(new LogInFragment.LoginDelegate() {
            @Override
            public void regClick() {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.remove(logInFragment);
                transaction.add(R.id.fragment_container, registerFragment, "RegisterFragment");
                transaction.addToBackStack(null);
                transaction.commit();

                registerFragment.setRegisterDelegate(new RegisterFragment.RegisterDelegate() {
                    @Override
                    public void register() {
                        fragmentManager.popBackStack();
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
                    public void viewEventLocation(Event event) {
                        EventDetailsFragment detailsFragment = new EventDetailsFragment();
                        detailsFragment.setEvent(event);
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.remove(eventsFragment);
                        transaction.add(R.id.fragment_container, detailsFragment, "EventDetailsFragment");
                        transaction.addToBackStack(null);
                        transaction.commit();
                        AddEventButton.setVisible(false);
                    }
                });
                FragmentTransaction transaction = fragmentManager.beginTransaction();
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

    private void openAddEvent() {
        final AddEventFragment addEventFragment = new AddEventFragment();
        addEventFragment.SetCurrentUser(currentUser);
        addEventFragment.setDelegate(new AddEventFragment.AddEventDelegate() {
            @Override
            public void add() {
                fragmentManager.popBackStack();
                AddEventButton.setVisible(true);
                Context context = getApplicationContext();
                CharSequence text = "Event Saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void selectAddress(Event event) {
                EventDetailsFragment detailsFragment = new EventDetailsFragment();
                delegate = new EventDetailsFragment.EventDetailsFragmentDelegate() {
                    @Override
                    public void save() {
                        fragmentManager.popBackStack();
                        SaveLocationButton.setVisible(false);
                    }
                };
                detailsFragment.setEventDetailsDelegate(delegate);
                detailsFragment.setEvent(event);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.remove(addEventFragment);
                transaction.add(R.id.fragment_container, detailsFragment, "EventDetailsFragment");
                transaction.addToBackStack(null);
                transaction.commit();
                SaveLocationButton.setVisible(true);
            }
        });
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragmentManager.findFragmentByTag("ListOfEventsFragment"));
        transaction.add(R.id.fragment_container, addEventFragment, "AddEventFragment");
        transaction.addToBackStack(null);
        transaction.commit();
        AddEventButton.setVisible(false);
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() == 1)
            this.finish();
        else {
            Fragment fragment = fragmentManager.findFragmentByTag("AddEventFragment");
            if(fragment != null) {
                if (fragment.isVisible()) {
                    AddEventButton.setVisible(true);
                }
            }
            else {
                fragment = fragmentManager.findFragmentByTag("EventDetailsFragment");
                if(fragment != null) {
                    if(fragment.isVisible()) {
                        SaveLocationButton.setVisible(false);
                    }
                }
            }
        }
        super.onBackPressed();
    }

    public void viewSettings() {
        //Suppose we want to actually do this sometime
    }
}
