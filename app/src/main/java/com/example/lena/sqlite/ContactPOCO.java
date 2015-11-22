package com.example.lena.sqlite;

import android.test.InstrumentationTestCase;

/**
 * Created by Lena on 20.11.2015.
 */
public class ContactPOCO  extends InstrumentationTestCase {
    private String Name;
    private String email;

    public String GetName()
    {
        return Name;
    }

    public String GetEmail()
    {
        return email;
    }

    public void SetName(String name)
    {
        //Log.d("Test log","Is name null: "+Boolean.toString(name!=null));
        //Log.d("Test log","Is name not empty: "+Boolean.toString(!name.isEmpty()));
        //Log.d("Test log","Is name big length: "+Boolean.toString(name.length()<100));
        if(name!=null && !name.isEmpty() && name.length()<100)
        {
            //Log.d("Test log","The name was added!");
            Name=name;
        }

    }

    public void SetEmail(String email)
    {
        if(email!=null && !email.isEmpty() && email.length()<100)
        {
            this.email=email;
        }
    }
}
