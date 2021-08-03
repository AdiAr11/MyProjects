package com.example.database;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database.data.DatabaseHandler;
import com.example.database.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
        Contact contact = new Contact();
        contact.setName("Rajnish");
        contact.setPhone_number("989993934");
        //databaseHandler.addOneContact(contact);
        //databaseHandler.addOneContact(new Contact("Rajni", "9873377434"));
        List<Contact> contacts = databaseHandler.getAllContacts();
        for(Contact contact1:contacts) {
            Log.d("Contacts", contact1.getName());
        }
    }
}