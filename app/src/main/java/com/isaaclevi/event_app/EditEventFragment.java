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


/**
 * A simple {@link Fragment} subclass.
 */
public class EditEventFragment extends Fragment {

    interface EditEventDelegate {
        public void Edit();
    }

    public EditEventFragment() {
        // Required empty public constructor
    }

    TextView EventName;
    TextView EventExplanation;
    TextView UserName;
    TextView AddressView;
    TextView DateView, TimeView;
    Button UpdateEvent;
    Button Date, Time;

    User currentUser;
    Event event;

    EditEventDelegate delegate;

    public void SetCurrentUser(User user) {
        currentUser = user;
    }

    public void SetDelegate(EditEventDelegate delegate) {
        this.delegate = delegate;
    }

    public void showTimePickerDialog() {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.show(getFragmentManager(), "timePicker");
        fragment.setPreviousTime(TimeView.getText().toString());
    }

    public void showDatePickerDialog() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "datePicker");
        fragment.setPreviousDate(DateView.getText().toString());
    }

    private boolean setEmptyError(TextView text)
    {
        if(text.getText().toString().isEmpty())
        {
            text.setError("This Field Cannot Be Empty!");
            return false;
        }
        return true;
    }

    private int[] ParsStringDateOrTime(String DateOrTime)
    {
        int[] Num=new int[5];
        String[] Str;
        if(DateOrTime.split("[/ :]").length==5)
        {
            Str = DateOrTime.split("[/ :]");
            for (int i = 0; i < 5; i++) {
                Num[i] = Integer.parseInt(Str[i]);
            }
            return Num;
        }
        return null;
    }

    //validating time and day by current date and time
    private boolean Validation()
    {
        int Day, Month, Year, Hours, Minuets;
        int[] Array;
        Array = ParsStringDateOrTime(DateView.getText().toString() + " " + TimeView.getText().toString());

        if(Array == null)
        {
            if(DateView.getText().toString().isEmpty()) {
                DateView.setError("Date Field Cannot Be Empty!");
            }
            if(TimeView.getText().toString().isEmpty()) {
                TimeView.setError("Time Field Cannot Be Empty!");
            }

            return false;
        }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        event = new Event();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);

        UserName = (TextView) root.findViewById(R.id.edit_user_name);
        EventName = (TextView) root.findViewById(R.id.edit_event_name);
        EventExplanation = (EditText) root.findViewById(R.id.edit_event_explanation);
        AddressView = (TextView) root.findViewById(R.id.add_event_address_view);
        DateView = (TextView) root.findViewById(R.id.add_event_date_view);
        TimeView = (TextView) root.findViewById(R.id.add_event_time_view);
        UpdateEvent = (Button) root.findViewById(R.id.update_event_button);

        UserName.setText(currentUser.PersonName + "(" + currentUser.NickName + ")");
        event.setUserName(currentUser.NickName);

        Date = (Button) root.findViewById(R.id.edit_event_date);
        Time = (Button) root.findViewById(R.id.edit_event_time);

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

        if (event.EventAddress != null)
            AddressView.setText(event.EventAddress);

        UpdateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flagEventName = setEmptyError(EventName);
                setEmptyError(AddressView);
                if(Validation() && flagEventName /*&& AddressView.getText().toString().isEmpty()*/) {
                    event.setEventName(EventName.getText().toString());
                    event.setEventExplanation(EventExplanation.getText().toString());
                    event.setEventAddress(AddressView.getText().toString());
                    event.setEventTime(DateView.getText().toString() + " " + TimeView.getText().toString());
                    Model.getInstance().saveEvent(event);
                    if (delegate != null)
                        delegate.Edit();
                }
            }
        });

        return root;
    }
}


















