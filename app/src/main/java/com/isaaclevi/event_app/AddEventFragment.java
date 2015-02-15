package com.isaaclevi.event_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddEventFragment extends Fragment
{
    interface AddEventDelegate {
        public void add();
        public void selectAddress(Event event);
    }

    EditText EventName;
    EditText EventExplanation;
    TextView UserName;
    TextView AddressView;
    TextView DateView, TimeView;
    Button EventAddress;
    Button AddEvent;
    Button Date, Time;

    User currentUser;
    Event event;

    AddEventDelegate delegate;

    public AddEventFragment()
    {
        // Required empty public constructor
    }

    public void SetCurrentUser(User user)
    {
        currentUser = user;
    }

    public void setDelegate(AddEventDelegate delegate) {
        this.delegate = delegate;
    }

    public void showTimePickerDialog()
    {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog()
    {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        event = new Event();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);

        UserName = (TextView)root.findViewById(R.id.add_user_name);
        EventName = (EditText) root.findViewById(R.id.add_event_name);
        EventExplanation = (EditText) root.findViewById(R.id.add_event_explanation);
        EventAddress = (Button) root.findViewById(R.id.add_event_address);
        AddressView = (TextView) root.findViewById(R.id.add_event_address_view);
        DateView = (TextView) root.findViewById(R.id.add_event_date_view);
        TimeView = (TextView) root.findViewById(R.id.add_event_time_view);
        AddEvent = (Button) root.findViewById(R.id.add_event_button);

        UserName.setText(currentUser.PersonName + "(" + currentUser.NickName + ")");
        event.setUserName(currentUser.NickName);

        Date = (Button) root.findViewById(R.id.add_event_date);
        Time = (Button) root.findViewById(R.id.add_event_time);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                PickerHelper.getInstance().setDateView(DateView);
            }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
                PickerHelper.getInstance().setTimeView(TimeView);
            }
        });

        EventAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delegate != null) {
                    delegate.selectAddress(event);
                }
            }
        });

        if(event.EventAddress != null)
            AddressView.setText(event.EventAddress);

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.setEventName(EventName.getText().toString());
                event.setEventExplanation(EventExplanation.getText().toString());
                event.setEventAddress(AddressView.getText().toString());
                event.setEventTime(DateView.getText().toString() + " " + TimeView.getText().toString());
                Model.getInstance().saveEvent(event);
                if(delegate != null)
                    delegate.add();
            }
        });

        return root;
    }
}
