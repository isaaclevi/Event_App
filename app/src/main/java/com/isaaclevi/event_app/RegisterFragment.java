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
public class RegisterFragment extends Fragment {

    interface RegisterDelegate {
        public void register();
    }

    Button registerButton;
    EditText PersonName;
    EditText Nickname;
    EditText PhoneNumber;
    EditText Password;
    EditText Retype;

    boolean valid = true;

    RegisterDelegate registerDelegate;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public void setRegisterDelegate(RegisterDelegate delegate) {
        this.registerDelegate = delegate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        registerButton = (Button) root.findViewById(R.id.sub_register_btn);
        Nickname = (EditText) root.findViewById(R.id.nickname);
        PersonName = (EditText) root.findViewById(R.id.person_name);
        PhoneNumber = (EditText) root.findViewById(R.id.phone_number);
        Password = (EditText) root.findViewById(R.id.password);
        Retype = (EditText) root.findViewById(R.id.retype_password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValidity();
                if (valid)
                {
                    User user = new User(Nickname.getText().toString(),
                            PersonName.getText().toString(),
                            PhoneNumber.getText().toString(),
                            Password.getText().toString());

                    Model.getInstance().registerUser(user);

                    if (registerDelegate != null)
                        registerDelegate.register();
                }
            }
        });

        return root;
    }

    public boolean validatePhone(String phone) {
        return (phone.startsWith("050") ||
                phone.startsWith("052") ||
                phone.startsWith("053") ||
                phone.startsWith("054") ||
                phone.startsWith("055") ||
                phone.startsWith("057") ||
                phone.startsWith("058")) &&
                phone.length() == 10;
    }

    public void setValidity()
    {
        valid = true;

        setEmptyError(Nickname);
        setEmptyError(PersonName);
        setEmptyError(PhoneNumber);
        setEmptyError(Password);
        setEmptyError(Retype);

        if (!Model.getInstance().checkNickname(Nickname.getText().toString())) {
            Nickname.setError("This Nickname is already chosen. Try Another!");
            valid = false;
        }

        if (!validatePhone(PhoneNumber.getText().toString())) {
            PhoneNumber.setError("The Phone Number is Invalid. Try Again!");
            valid = false;
        }

        if (!Model.getInstance().checkPhone(PhoneNumber.getText().toString())) {
            PhoneNumber.setError("This Phone Number is already in use!");
            valid = false;
        }

        if (!Password.getText().toString().equals(Retype.getText().toString())) {
            Retype.setError("Passwords don't match!");
            valid = false;
        }
    }

    public void setEmptyError(EditText text)
    {
        if(text.getText().toString().isEmpty()) {
            text.setError("This Field Cannot Be Empty!");
            valid = false;
        }
    }
}
