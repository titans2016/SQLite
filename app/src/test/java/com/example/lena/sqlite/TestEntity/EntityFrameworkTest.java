package com.example.lena.sqlite.TestEntity;

import android.content.ContentValues;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

import com.example.lena.sqlite.ContactPOCO;
import com.example.lena.sqlite.DBHelper;
import com.example.lena.sqlite.EntityFramework;
import com.example.lena.sqlite.MainActivity;

import java.util.List;

/**
 * Created by Lena on 21.11.2015.
 */
public class EntityFrameworkTest extends InstrumentationTestCase {

    private DBHelper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //RenamingDelegatingContext context = new RenamingDelegatingContext(getInstrumentation().getContext(), "test_");
        //db = new DBHelper(context);
        //MainActivity.dbHelper=db;
    }

    @Override
    public void tearDown() throws Exception {

        super.tearDown();
    }

    public void testAddContact() throws Exception {
            Context context=new RenamingDelegatingContext(getInstrumentation().getTargetContext(),"test_");
            db = new DBHelper(context);
            MainActivity main=new MainActivity();
            MainActivity.dbHelper=db;

            EntityFramework entityFramework = new EntityFramework();

            ContentValues cv = new ContentValues();
            String name = "TestUser12345678";
            cv.put("name", name);
            cv.put("email", "test@mail.ru");

            entityFramework.AddContact(cv);
            List<ContactPOCO> list = entityFramework.GetAllContacts();

            assertEquals(1, CounterEqualsName(list, name));

            entityFramework = new EntityFramework();

            entityFramework.DeleteContact(name);

            list = entityFramework.GetAllContacts();
            db.close();
            assertEquals(0, CounterEqualsName(list, name));

    }
    private int CounterEqualsName(List<ContactPOCO> list, String name)
    {
        int counter=0;
        for (int i=0; i<list.size(); i++)
        {
            if(list.get(i).GetName().equals(name))
            {
                counter++;
            }
        }

        return counter;
    }

}