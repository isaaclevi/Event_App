package com.isaaclevi.event_app;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListOfEventsFragment extends Fragment
{
    interface ListOfEventsDelegate {
        void viewEvent(Event event);
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
        public View getView(int position, View convertView, ViewGroup parent)
        {
            /*
            if(convertView == null)
            {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_event_layout,null);
            }
            Event temp= events.elementAt(position);
            TextView EventName=(TextView)convertView.findViewById(R.id.event_name);
            EventName.setText(temp.EventName);
            TextView UserName = (TextView) convertView.findViewById(R.id.user_name);
            UserName.setText(temp.UserName);
            TextView EventExplanation= (TextView) convertView.findViewById(R.id.event_explanation);
            EventExplanation.setText(temp.EventExplanation);
            TextView TimeStarts= (TextView) convertView.findViewById(R.id.time_started);
            TimeStarts.setText(temp.StartTime.toString());
            TextView TimeEnds= (TextView) convertView.findViewById(R.id.time_ends);
            TimeEnds.setText(temp.EndTime.toString());
            TextView EventTime = (TextView) convertView.findViewById(R.id.time_left);
            Calendar thatDay = Calendar.getInstance();
            
            SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
            Date Sday,Eday;
            try
            {
                //Sday=format.parse(temp.StartTime);
            }
            catch (Exception e)
            {}
            //EventTime.setText();
            */
            return null;
        }

        public void updateList() {
            events = Model.getInstance().getAllEvents();
        }
    }
}
