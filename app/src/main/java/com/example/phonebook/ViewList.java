package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ViewList extends ListActivity {

    private SQLiteDatabase  db;
    private Cursor          cursor;
    private ListView        contactsList;
    private CursorAdapter   listAdapter;    //what a nice idea to use tab instead of space :)

    @Override
    protected void onRestart() {
        super.onRestart();
        try{
            cursor = db.query("PhonebookContacts",new String[] {"_id","FirstName","LastName"},
                    null,null,null,null,"LastName");
            listAdapter.changeCursor(cursor);
        }
        catch(SQLiteException e)
        {
            Toast.makeText(getApplicationContext(),"Data base connection error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(ViewList.this, ContactInfo.class);
        intent.putExtra(ContactInfo.Contact_Info, (int)id ) ;
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_list);

        try {
            SQLiteOpenHelper contactsDBHelper = new ContactsDBHelper(this);
            db = contactsDBHelper.getReadableDatabase();
            cursor = db.query("PhonebookContacts",new String[] {"_id","FirstName","LastName"},
                        null,null,null,null,"LastName");

            listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
                    new String[] {"LastName" , "FirstName"},
                    new int[] {android.R.id.text1,android.R.id.text2}, 0) ;

            contactsList = getListView();
            contactsList.setAdapter(listAdapter);
        }
        catch(SQLiteException e)
        {
            Toast.makeText(getApplicationContext(),"Data base connection error",Toast.LENGTH_SHORT).show();
        }



    }
}