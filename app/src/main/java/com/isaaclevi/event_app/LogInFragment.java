package com.isaaclevi.event_app;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        void setUser(User user);
    }

    public LogInFragment()
    {
        // Required empty public constructor
    }

    EditText phoneNumber;
    EditText password;
    Button register;
    Button login;

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

        password.setText("");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginDelegate != null)
                    loginDelegate.regClick();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmptyError(phoneNumber);
                setEmptyError(password);

                if(!phoneNumber.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if (!Model.getInstance().validateUser(phoneNumber.getText().toString(), password.getText().toString()))
                        phoneNumber.setError("Wrong Phone Number or Password!");
                    else {
                        if (loginDelegate != null) {
                            loginDelegate.setUser(Model.getInstance().getUser(phoneNumber.getText().toString()));
                            loginDelegate.loginClick();
                        }
                    }
                }
            }
        });

        return root;
    }

    public void setEmptyError(EditText text)
    {
        if(text.getText().toString().isEmpty()) {
            text.setError("This Field Cannot Be Empty!");
        }
    }
}
