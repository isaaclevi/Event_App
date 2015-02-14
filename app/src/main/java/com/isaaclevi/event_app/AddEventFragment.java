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
    EditText EventAddress;
    TextView UserName;

    Button StartDate, StartTime, EndDate, EndTime;

    User currentUser;
    boolean startOrEnd; //flag to know if start button or end button preset

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
        fragment.setStartOrEnd(startOrEnd);
    }

    public void showDatePickerDialog()
    {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "datePicker");
        fragment.setStartOrEnd(startOrEnd);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);
        UserName = (TextView)root.findViewById(R.id.add_user_name);
        EventName = (EditText) root.findViewById(R.id.add_event_name);
        EventExplanation = (EditText) root.findViewById(R.id.add_event_explanation);
        EventAddress = (EditText) root.findViewById(R.id.add_event_address);

        UserName.setText(currentUser.PersonName+"("+currentUser.NickName+")");

        StartDate = (Button) root.findViewById(R.id.add_event_start_date);
        StartTime = (Button) root.findViewById(R.id.add_event_start_time);
        EndDate = (Button) root.findViewById(R.id.add_event_end_date);
        EndTime = (Button) root.findViewById(R.id.add_event_end_time);

        StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrEnd = true;
                showDatePickerDialog();
                PickerHelper.getInstance().setStartDateButton(StartDate);
            }
        });

        StartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrEnd = true;
                showTimePickerDialog();
                PickerHelper.getInstance().setStartTimeButton(StartTime);
            }
        });

        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrEnd = false;
                showDatePickerDialog();
                PickerHelper.getInstance().setEndDateButton(EndDate);
            }
        });

        EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrEnd = false;
                showTimePickerDialog();
                PickerHelper.getInstance().setEndTimeButton(EndTime);
            }
        });

        return root;
    }
}
