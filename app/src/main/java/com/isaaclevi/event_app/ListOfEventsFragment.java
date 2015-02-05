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


    interface ListOfEvent
    {
        void OpenFragment();
    }

    private ListOfEvent ListOfEventDelegate;

    public void SetListOfEventsDelegate(ListOfEvent ListOfEventDelegate)
    {
        this.ListOfEventDelegate=ListOfEventDelegate;
    }

    Vector<Event> Events;
    ConvertListAdaptor adaptor;

    public ListOfEventsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_of_events, container, false);
        ListView List= (ListView) root.findViewById(R.id.events_list);
        adaptor=new ConvertListAdaptor();
        List.setAdapter(adaptor);
        return root;
    }

    class ConvertListAdaptor extends BaseAdapter
    {
        @Override
        public int getCount()
        {
            return Events.size();
        }

        @Override
        public Object getItem(int position) {
            return Events.elementAt(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if(convertView == null)
            {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_event_layout,null);
            }
            Event temp=Events.elementAt(position);
            TextView EventName=(TextView)convertView.findViewById(R.id.event_name);
            EventName.setText(temp.EventName);
            TextView UserNmae= (TextView) convertView.findViewById(R.id.user_name);
            UserNmae.setText(temp.UserName);
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
            return null;
        }
    }
}
