package com.isaaclevi.event_app;

import android.app.Activity;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.parse.Parse;
import com.parse.ParseObject;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class MainScreen extends Activity {

    Model modelDB;
    LogInFragment loginFrag;
    RegisterFragment regFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        modelDB=Model.getModel();
        modelDB.InitDB(this);
        loginFrag = new LogInFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, loginFrag);
        transaction.addToBackStack("login");
        transaction.commit();
        AppManager();
    }

    public void AppManager()
    {
        //open registration
        loginFrag.SetLogInDataDelegate(new LogInFragment.LogInData()
        {
            @Override
            public void regClick()
            {
                regFrag=new RegisterFragment();
                if(regFrag != null)
                {
                    regFrag.setDelegate(new RegisterFragment.RegisterDelegate() {
                        @Override
                        public void register() {
                            getFragmentManager().popBackStack();
                        }
                    });
                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.remove(loginFrag);
                    transaction.add(R.id.fragment_container, regFrag);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        //---------------------------------------------------------------------------
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 1)
            this.finish();
        super.onBackPressed();
    }
}
