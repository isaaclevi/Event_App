package com.isaaclevi.event_app;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment
{
    interface LoginDelegate
    {
        void regClick();
        void loginClick();
    }

    public LogInFragment()
    {
        // Required empty public constructor
    }

    EditText phoneNumber;
    EditText password;
    Button register;
    Button login;
    CheckBox rememberMe;

    private LoginDelegate loginDelegate;

    public void SetLoginDelegate(LoginDelegate delegate)
    {
        this.loginDelegate = delegate;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_log_in, container, false);

        phoneNumber = (EditText) root.findViewById(R.id.phone_text);
        password = (EditText) root.findViewById(R.id.password);
        register = (Button) root.findViewById(R.id.register_btn);
        login = (Button) root.findViewById(R.id.log_in_btn);
        rememberMe = (CheckBox) root.findViewById(R.id.remember_pass_box);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginDelegate != null)
                    loginDelegate.regClick();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Model.getInstance().validateUser(phoneNumber.getText().toString(), password.getText().toString());
                if(Model.getInstance().isValid())
                    phoneNumber.setError("Wrong Phone Number or Password!");
                else {
                    if (loginDelegate != null)
                    {
                        loginDelegate.loginClick();
                    }
                }
            }
        });

        //listener for remember box
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //do nothing at this point
            }
        });

        return root;
    }
}
