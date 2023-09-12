package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClose (View v)
    {

        finish();
    }
    public void onAddPerson (View v)
    {
        Intent intent = new Intent(MainActivity.this, AddContact.class);
        startActivity(intent);
    }

    public void onBrowseList (View v)
    {
        Intent intent = new Intent (MainActivity.this, ViewList.class);
        startActivity(intent);
    }
}