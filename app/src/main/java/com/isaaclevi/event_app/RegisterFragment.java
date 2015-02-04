package com.isaaclevi.event_app;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

import java.text.ParseException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    Button regBTN;

    EditText PersonName;
    EditText Nickname;
    EditText PhoneNumber;
    EditText Password;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_register, container, false);
        regBTN= (Button) root.findViewById(R.id.sub_register_btn);
        PersonName= (EditText) root.findViewById(R.id.person_name);
        Nickname= (EditText) root.findViewById(R.id.nickname);
        PhoneNumber= (EditText) root.findViewById(R.id.phone_number);
        Password= (EditText) root.findViewById(R.id.password);
        regBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User user=new User(Nickname.getText().toString(),PersonName.getText().toString()
                        ,PhoneNumber.getText().toString(),Password.getText().toString());

                Model.getModel().registerUser(user);
            }
        });
        return root;
    }
}
