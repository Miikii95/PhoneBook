package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactInfo extends AppCompatActivity {

    public static final String Contact_Info = "Contact_Info";
    private int contactID;
    private static SQLiteDatabase db;
    private static ContactsDBHelper contactsDB;
    private String sFName = "First name";
    private String sLName = "Last name";
    private String sPhone = "Phone number";
    private String sMail  = "E-mail adress";


    public void onDelete(View v)
    {
        try {
            contactsDB.deleteContact(db,contactID);
            Toast.makeText(getApplicationContext(),"Contact deleted",Toast.LENGTH_SHORT).show();
            finish();
        }
       catch (SQLiteException e)
       {
           Toast.makeText(getApplicationContext(),"Unable to delete",Toast.LENGTH_SHORT).show();
       }

    }
    public void onEdit(View v)
    {
        EditText editText = findViewById(R.id.editTextFirstName2);
        sFName = editText.getText().toString();
        editText = findViewById(R.id.editTextLastName2);
        sLName = editText.getText().toString();
        editText = findViewById(R.id.editTexteMail2);
        sMail= editText.getText().toString();
        editText = findViewById(R.id.editTextPhone2);
        sPhone= editText.getText().toString();

        try {
            contactsDB.UpdateContact(db, contactID, sFName, sLName, sPhone,sMail);
            Toast.makeText(getApplicationContext(),"Contact was modified",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e)
        {
            Toast.makeText(getApplicationContext(),"Unable to save changes",Toast.LENGTH_SHORT).show();
        }
    }
    public void onCall(View v)
    {
        //Toast.makeText(getApplicationContext(),"Nie działa",Toast.LENGTH_SHORT).show();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+sPhone));//change the number
        startActivity(callIntent);
    }
    public void onMail(View v)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:"
                + sMail
                + "?subject=" + "Feedback" + "&body=" + "");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        contactID = (int) getIntent().getExtras().get(Contact_Info);

        try {
            contactsDB = new ContactsDBHelper(this);
            db = contactsDB.getWritableDatabase();
            Cursor cursor = db.query("PhonebookContacts",new String[] {"_id","FirstName","LastName","PhoneNumber","Email"},
                    "_id = ?",new String[] {String.valueOf(contactID)},null,null,null);


            if (cursor.moveToFirst())
            {
                 sFName = cursor.getString(1);
                 sLName = cursor.getString(2);
                 sPhone = cursor.getString(3);
                 sMail = cursor.getString(4);

                cursor.close();
            }
            else {Toast.makeText(getApplicationContext(),"Data base indexing error",Toast.LENGTH_SHORT).show();}

            EditText editText = findViewById(R.id.editTextFirstName2);
            editText.setText(sFName);
            editText = findViewById(R.id.editTextLastName2);
            editText.setText(sLName);
            editText = findViewById(R.id.editTexteMail2);
            editText.setText(sMail);
            editText = findViewById(R.id.editTextPhone2);
            editText.setText(sPhone);

        }
        catch (SQLiteException e)
        {
            Toast.makeText(getApplicationContext(),"Data base connection error",Toast.LENGTH_SHORT).show();
        }
    }
}