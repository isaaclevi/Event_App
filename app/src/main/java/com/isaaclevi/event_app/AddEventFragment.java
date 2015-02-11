package com.isaaclevi.event_app;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment {

    EditText EventName;
    EditText EventExplanation;
    EditText EventAddress;
    TextView UserName;
    String PersonName;
    String NickName;

    TextView date;
    DatePicker datePicker;

    private int day;
    private int month;
    private int year;

    static int date_dialog_id;

    public AddEventFragment()
    {
        // Required empty public constructor
    }

    public void getUserNameAndNickName(String UserName,String NickName)
    {
        this.PersonName = UserName;
        this.NickName = NickName;
    }

    public void setCurrentDateOnView(View root)
    {
        /*
        date = (TextView) root.findViewById(R.id.);
        datePicker = (DatePicker) root.findViewById(R.id.);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_add_event, container, false);
        UserName = (TextView)root.findViewById(R.id.user_name);
        UserName.setText(PersonName+"("+NickName+")");
        EventName = (EditText) root.findViewById(R.id.add_event_name);
        EventExplanation = (EditText) root.findViewById(R.id.add_event_explanation);
        EventAddress = (EditText) root.findViewById(R.id.add_event_address);

        setCurrentDateOnView(root);
        //addListenerOnButton();

        return root;
    }

}
