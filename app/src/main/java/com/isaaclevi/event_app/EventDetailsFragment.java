package com.isaaclevi.event_app;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment {

    interface EventDetailsFragmentDelegate {

    }

    Event event;

    EventDetailsFragmentDelegate eventDetailsDelegate;

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setEventDetailsDelegate(EventDetailsFragmentDelegate delegate) {
        this.eventDetailsDelegate = delegate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }
}
