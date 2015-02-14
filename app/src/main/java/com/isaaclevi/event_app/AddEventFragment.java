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
    Button Sdate, Stime, Edate, Etime;
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

        Sdate = (Button) root.findViewById(R.id.add_event_start_date);
        Stime = (Button) root.findViewById(R.id.add_event_start_time);
        Edate = (Button) root.findViewById(R.id.add_event_end_date);
        Etime = (Button) root.findViewById(R.id.add_event_end_time);

        Sdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startOrEnd = true;
                showDatePickerDialog();
                Sdate.setText("Start Date:"+PickerHelper.getInstance().getStartDate());
            }
        });

        Stime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startOrEnd = true;
                showTimePickerDialog();
                Stime.setText("Start Time:"+PickerHelper.getInstance().getStartTime());
            }
        });

        Edate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startOrEnd = false;
                showDatePickerDialog();
                Edate.setText("End Date:"+PickerHelper.getInstance().getEndDate());
            }
        });

        Etime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startOrEnd = false;
                showTimePickerDialog();
                Etime.setText("End Time:"+PickerHelper.getInstance().getEndTime());
            }
        });

        return root;
    }
}
