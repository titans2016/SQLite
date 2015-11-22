package com.example.lena.sqlite;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnRead,hideList;
    EditText etName, etEmail;
    ListView listView;
    public static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.AddContactButton);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.ViewAllButton);
        btnRead.setOnClickListener(this);

        hideList = (Button) findViewById(R.id.HideList);
        hideList.setOnClickListener(this);
        hideList.setVisibility(View.INVISIBLE);

        etName = (EditText) findViewById(R.id.editName);
        etEmail = (EditText) findViewById(R.id.editEmail);
        listView=(ListView) findViewById(R.id.listView);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        etName.setText("");
        String email = etEmail.getText().toString();
        etEmail.setText("");
        EntityFramework entity=null;

        switch (v.getId()) {
            case R.id.HideList:
                Log.d("My Logs", "Hide list");
                hideList.setVisibility(View.INVISIBLE);
                listView.setAdapter(null);
                break;
            case R.id.AddContactButton:
                Log.d("My Logs", "Click button contact");
                ContentValues cv = new ContentValues();
                cv.put("name", name);
                cv.put("email", email);


                entity=new EntityFramework();
                entity.AddContact(cv);

                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("The contact was added!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();*/

                Toast toast= Toast.makeText(getApplicationContext(),"The "+name+" was added!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();

                break;
            case R.id.ViewAllButton:
                Log.d("My Logs", "Click button view all");

                entity=new EntityFramework();
                List<ContactPOCO> list=entity.GetAllContacts();

                String[] names = new String[list.size()];
                for (int i=0; i<list.size(); i++)
                {
                    names[i]="Name:"+list.get(i).GetName()+"\r\nEmail: "+list.get(i).GetEmail();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);

                // присваиваем адаптер списку
                listView.setAdapter(adapter);

                hideList.setVisibility(View.VISIBLE);

                break;
        }
    }
}
