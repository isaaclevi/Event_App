package com.isaaclevi.event_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment
{

    interface AddEventDelegate
    {
        public void add();
    }

    EditText EventName;
    EditText EventExplanation;
    TextView UserName;
    Button EventAddress;
    Button AddEvent;

    Button Date, Time;

    User currentUser;

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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);
        UserName = (TextView)root.findViewById(R.id.add_user_name);
        EventName = (EditText) root.findViewById(R.id.add_event_name);
        EventExplanation = (EditText) root.findViewById(R.id.add_event_explanation);
        EventAddress = (Button) root.findViewById(R.id.add_event_address);
        AddEvent = (Button) root.findViewById(R.id.add_event_button);

        UserName.setText(currentUser.PersonName+"("+currentUser.NickName+")");

        Date = (Button) root.findViewById(R.id.add_event_date);
        Time = (Button) root.findViewById(R.id.add_event_time);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                PickerHelper.getInstance().setDateButton(Date);
            }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
                PickerHelper.getInstance().setTimeButton(Time);
            }
        });

        EventAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save to DB
            }
        });

        return root;
    }
}
