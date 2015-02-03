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
    }

    public LogInFragment()
    {
        // Required empty public constructor
    }

    Button regBTN;

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
        regBTN= (Button) root.findViewById(R.id.register_btn);
        regBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LogInDataDelegate.regClick();
            }
        });
        return root;
    }
}
