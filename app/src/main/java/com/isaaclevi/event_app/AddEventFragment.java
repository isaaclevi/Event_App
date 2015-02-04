package com.isaaclevi.event_app;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


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

    public AddEventFragment()
    {
        // Required empty public constructor
    }

    public void getUserNameAndNickName(String UserName,String NickName)
    {
        this.PersonName = UserName;
        this.NickName = NickName;
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
        return root;
    }

}
