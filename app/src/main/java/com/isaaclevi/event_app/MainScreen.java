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

        logInFragment = new LogInFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
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

    public void saveLocation() {
        if(delegate != null)
            delegate.save();
    }

    public void openLogin() {
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
                    public void viewEventLocation(Event event) {
                        EventDetailsFragment detailsFragment = new EventDetailsFragment();
                        detailsFragment.setEvent(event);
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
                Context context = getApplicationContext();
                CharSequence text = "Login Successful.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void setUser(User user) {
                currentUser = user;
            }
        });
    }

    public void openAddEvent() {
        final AddEventFragment addEventFragment = new AddEventFragment();
        addEventFragment.SetCurrentUser(currentUser);
        addEventFragment.setDelegate(new AddEventFragment.AddEventDelegate() {
            @Override
            public void add() {
                getFragmentManager().popBackStack();
                AddEventButton.setVisible(true);
                Context context = getApplicationContext();
                CharSequence text = "Event Saved.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void selectAddress(final Event event) {
                final EventDetailsFragment detailsFragment = new EventDetailsFragment();
                delegate = new EventDetailsFragment.EventDetailsFragmentDelegate() {
                    @Override
                    public void save() {
                        detailsFragment.saveAddress(event);
                        getFragmentManager().popBackStack();
                        SaveLocationButton.setVisible(false);
                    }
                };
                detailsFragment.setEventDetailsDelegate(delegate);
                detailsFragment.setEvent(event);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(addEventFragment);
                transaction.add(R.id.fragment_container, detailsFragment, "EventDetailsFragment");
                transaction.addToBackStack(null);
                transaction.commit();
                SaveLocationButton.setVisible(true);
            }
        });
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(getFragmentManager().findFragmentByTag("ListOfEventsFragment"));
        transaction.add(R.id.fragment_container, addEventFragment, "AddEventFragment");
        transaction.addToBackStack(null);
        transaction.commit();
        AddEventButton.setVisible(false);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            this.finish();
        }
        else {
            if(getFragmentManager().findFragmentByTag("ListOfEventsFragment") != null) {
                if (getFragmentManager().findFragmentByTag("ListOfEventsFragment").isVisible()) {
                    AddEventButton.setVisible(false);
                }
            }
            else {
                if(getFragmentManager().findFragmentByTag("EventDetailsFragment") != null) {
                    if(getFragmentManager().findFragmentByTag("EventDetailsFragment").isVisible()) {
                        SaveLocationButton.setVisible(false);
                    }
                }
                else {
                    if(getFragmentManager().findFragmentByTag("AddEventFragment") != null) {
                        if (getFragmentManager().findFragmentByTag("AddEventFragment").isVisible()) {
                            AddEventButton.setVisible(true);
                        }
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
