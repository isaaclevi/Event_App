package com.isaaclevi.event_app;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment
{
    RegisterFragment regFrag;


    interface LogInData
    {
        void regClick();
        void loginClick();
    }

    public LogInFragment()
    {
        // Required empty public constructor
    }

    Button regBTN;
    Button loginBTN;

    private LogInData LogInDataDelegate;

    public void SetLogInDataDelegate(LogInData logInDataDelegate)
    {
        this.LogInDataDelegate=logInDataDelegate;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_log_in, container, false);
        regBTN = (Button) root.findViewById(R.id.register_btn);
        loginBTN = (Button) root.findViewById(R.id.log_in_btn);
        //listener for reg btn
        regBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LogInDataDelegate.regClick();
            }
        });
        //listener for login btn
        loginBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LogInDataDelegate.loginClick();
            }
        });
        return root;
    }
}
