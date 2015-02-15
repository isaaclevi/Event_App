package com.isaaclevi.event_app;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListOfEventsFragment extends Fragment
{
    interface ListOfEventsDelegate {
        void viewEventLocation(Event event);
    }

    private ListOfEventsDelegate listOfEventsDelegate;

    public void SetListOfEventsDelegate(ListOfEventsDelegate delegate) {
        this.listOfEventsDelegate = delegate;
    }

    Adapter adapter;

    public ListOfEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_of_events, container, false);
        ListView List = (ListView) root.findViewById(R.id.events_list);
        adapter = new Adapter();
        List.setAdapter(adapter);
        return root;
    }

    class Adapter extends BaseAdapter
    {
        Vector<Event> events;

        Adapter() {
            updateList();
        }

        @Override
        public int getCount()
        {
            return events.size();
        }

        @Override
        public Object getItem(int position) {
            return events.elementAt(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_event_layout, null);
            }
            final Event event = events.get(position);

            TextView EventName = (TextView) convertView.findViewById(R.id.event_name);
            EventName.setText(event.EventName);
            TextView UserName = (TextView) convertView.findViewById(R.id.user_name);
            UserName.setText(event.UserName);
            TextView EventExplanation = (TextView) convertView.findViewById(R.id.event_explanation);
            EventExplanation.setText(event.EventExplanation);
            TextView TimeStarts = (TextView) convertView.findViewById(R.id.time_started);
            TimeStarts.setText(event.EventTime);
            final Button LocationButton = (Button) convertView.findViewById(R.id.view_location_button);
            LocationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(event.EventAddress != null && !event.EventAddress.equals("")) {
                        LocationButton.setError("Error Fetching Address!");
                    }
                    else if(listOfEventsDelegate != null) {
                        listOfEventsDelegate.viewEventLocation(event);
                    }
                }
            });
            return convertView;
        }

        public void updateList() {
            events = Model.getInstance().getAllEvents();
        }
    }
}
