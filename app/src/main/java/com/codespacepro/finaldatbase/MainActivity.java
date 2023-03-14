package com.codespacepro.finaldatbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);

        dbHelper.addContacts("A", "123");
        dbHelper.addContacts("B", "123");
        dbHelper.addContacts("C", "123");
        dbHelper.addContacts("D", "123");
        dbHelper.addContacts("E", "123");
        dbHelper.addContacts("F", "123");
        dbHelper.addContacts("G", "123");
        dbHelper.addContacts("H", "123");
        dbHelper.addContacts("I", "123");

        ArrayList<ContactModel> contactModels = dbHelper.fetchContacts();
        for (int i = 0; i < contactModels.size(); i++) {
            Log.d("RESULTS", "NAME: " + contactModels.get(i).name + ", Phone No: " + contactModels.get(i).number);
        }

        //update
        ContactModel contactModel = new ContactModel();
        contactModel.id = 1;
        contactModel.name = "SOFTWARE ENINGEER";
        contactModel.number = "+923047373533";
        dbHelper.UpdateContacts(contactModel);

        //Delete Contacts
        dbHelper.DeleteContacts(40);


    }
}