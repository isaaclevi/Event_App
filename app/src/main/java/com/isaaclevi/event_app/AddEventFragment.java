package com.isaaclevi.event_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

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
        fragment.setPreviousTime(TimeView.getText().toString());
    }

    public void showDatePickerDialog()
    {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "datePicker");
        fragment.setPreviousDate(DateView.getText().toString());
    }

    private void setEmptyError(TextView text)
    {
        if(text.getText().toString().isEmpty())
        {
            text.setError("This Field Cannot Be Empty!");
        }
    }

    private int[] ParsStringDateOrTime(String DateOrTime)
    {
        int[] Num=new int[5];
        String[] Str,s1,s2;
        Str=DateOrTime.split("[/ :]");
        for(int i=0;i<5;i++)
        {
            Num[i]=Integer.parseInt(Str[i]);
        }
        return Num;
    }

    /*
    private int[] ParsStringDateOrTime(String DateOrTime)
    {
        int[] Num=new int[5];
        String[] Str,s1,s2;
        Str=DateOrTime.split(" ");
        s1=Str[0].split("/");
        s2=Str[1].split(":");
        Num[0]=Integer.parseInt(s1[0]);
        Num[1]=Integer.parseInt(s1[1]);
        Num[2]=Integer.parseInt(s1[2]);
        Num[3]=Integer.parseInt(s1[3]);
        Num[4]=Integer.parseInt(s1[4]);
        return Num;
    }*/

    private boolean Validation()
    {
        int Day, Month, Year, Hours, Minuets;
        int[] Array;
        Array = ParsStringDateOrTime(DateView.getText().toString() + " " + TimeView.getText().toString());
        Day = Array[0];
        Month = Array[1];
        Year = Array[2];
        Hours = Array[3];
        Minuets = Array[4];
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minuets = c.get(Calendar.MINUTE);

        if (Year < year || (Year == year && Month < month) || (Year == year && Month == month && Day < day))
        {
            DateView.setError("Date Chosen is smaller then current date");
            return false;
        }

        if ((Year == year && Month == month && Day == day) && (Hours < hours || (Hours == hours && Minuets < minuets)))
        {
                TimeView.setError("Time Chosen is smaller then current time");
                return false;
        }
        return true;
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
            public void onClick(View v)
            {
                setEmptyError(EventName);
                setEmptyError(AddressView);
                if(Validation() && EventName.getText().toString().isEmpty() /*&& AddressView.getText().toString().isEmpty()*/)
                {
                    event.setEventName(EventName.getText().toString());
                    event.setEventExplanation(EventExplanation.getText().toString());
                    event.setEventAddress(AddressView.getText().toString());
                    event.setEventTime(DateView.getText().toString() + " " + TimeView.getText().toString());
                    Model.getInstance().saveEvent(event);
                    if (delegate != null)
                        delegate.add();
                }
            }
        });

        return root;
    }
}
