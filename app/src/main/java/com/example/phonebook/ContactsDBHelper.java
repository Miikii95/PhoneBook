package com.example.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactsDBHelper extends SQLiteOpenHelper {

    private static final String DBName = "Phonebook" ;
    private static final int DBVer = 1;


    public ContactsDBHelper( Context context) {
        super(context, DBName, null, DBVer);
    }

    private void updateDB (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion < 1)
        {
            String sCreateTable = "CREATE TABLE";
            String sTableName = "PhonebookContacts";
            String sTableDescription = "(_id INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,PhoneNumber TEXT,Email TEXT)";

            String sql = sCreateTable +" "+ sTableName +sTableDescription + ";" ;
            db.execSQL(sql);
        }
    }

    public static void UpdateContact (SQLiteDatabase db, int ID, String FName, String LName, String phoneNumber, String email)
    {
        ContentValues values = new ContentValues();
        values.put("FirstName", FName);
        values.put("LastName",LName);
        values.put("PhoneNumber",phoneNumber);
        values.put("Email",email);
        db.update("PhonebookContacts", values, "_id=?", new String[] {String.valueOf(ID)});
    }
    public static void InsertContact(SQLiteDatabase db, String FName, String LName, String phoneNumber, String email)
    {
        ContentValues values = new ContentValues();
        values.put("FirstName", FName);
        values.put("LastName",LName);
        values.put("PhoneNumber",phoneNumber);
        values.put("Email",email);
        db.insert("PhonebookContacts", null, values);
    }

    public static void deleteContact (SQLiteDatabase db, int ID)
    {
        db.delete("PhonebookContacts", "_id=?",new String[] { String.valueOf(ID) }) ; //DIFFRENT FROM EXAMPLE
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateDB(sqLiteDatabase, 0 , DBVer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateDB(sqLiteDatabase,oldVersion,newVersion);
    }
}
