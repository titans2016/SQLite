package com.example.lena.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lena on 20.11.2015.
 */
public class EntityFramework extends InstrumentationTestCase {

    private SQLiteDatabase db;

    public EntityFramework()
    {
        db = MainActivity.dbHelper.getWritableDatabase();
    }

    public long AddContact(ContentValues cv)
    {
        try
        {
            long rowID = db.insert("mytable", null, cv);
            Log.d("My Log", "row inserted, ID = " + rowID);
            return rowID;
        }catch (Exception ex)
        {
            Log.d("My Log", "Error:  " + ex);
        }finally {
            db.close();
        }
        return -1;
    }

    public List<ContactPOCO> GetAllContacts()
    {
        List<ContactPOCO> contacts=new ArrayList<ContactPOCO>();
        Cursor c = null;
        try {
            Log.d("My log", "--- Rows in mytable: ---");
            // делаем запрос всех данных из таблицы mytable, получаем Cursor
            c = db.query("mytable", null, null, null, null, null, null);

            // ставим позицию курсора на первую строку выборки
            // если в выборке нет строк, вернется false
            if (c.moveToFirst()) {

                // определяем номера столбцов по имени в выборке
                int idColIndex = c.getColumnIndex("id");
                int nameColIndex = c.getColumnIndex("name");
                int emailColIndex = c.getColumnIndex("email");

                do {
                    // получаем значения по номерам столбцов и пишем все в лог
                    Log.d("My Log",
                            "ID = " + c.getInt(idColIndex) +
                                    ", name = " + c.getString(nameColIndex) +
                                    ", email = " + c.getString(emailColIndex));
                    ContactPOCO poco=new ContactPOCO();
                    poco.SetEmail(c.getString(emailColIndex));
                    poco.SetName(c.getString(nameColIndex));
                    contacts.add(poco);
                    // переход на следующую строку
                    // а если следующей нет (текущая - последняя), то false - выходим из цикла
                } while (c.moveToNext());
            } else
                Log.d("My Log", "0 rows");

        }catch (Exception ex)
        {
            Log.d("My Log", "Error:  " + ex);
        }finally {
            if(c!=null)
            c.close();
            db.close();
        }


        return contacts;
    }

    public void DeleteContact(String userName)
    {
        try
        {
            db.delete("mytable", "username = ?", new String[]{userName});
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.close();
        }
    }
}
