package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class AddContact extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    public void onInsert (View v)
    {
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName) ;
        String sFName = editText.getText().toString();
        editText.setText("");

        editText = (EditText) findViewById((R.id.editTextTextPersonName2)) ;
        String sSName = editText.getText().toString();
        editText.setText("");

        editText = (EditText) findViewById((R.id.editTextPhone)) ;
        String sPhone = editText.getText().toString(); //!!!PHONE NUMBER IS STORED AS A STRING!!! bcs why not?
        editText.setText("");

        editText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String sMail = editText.getText().toString();
        editText.setText("");

        try {
            ContactsDBHelper contactsDB = new ContactsDBHelper(this);
            db = contactsDB.getWritableDatabase();
            contactsDB.InsertContact(db,sFName,sSName,sPhone,sMail);
            Toast.makeText(getApplicationContext(),"New contact added",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e)
        {
            Toast.makeText(getApplicationContext(),"Data base connection error",Toast.LENGTH_SHORT).show();
        }


    }
}