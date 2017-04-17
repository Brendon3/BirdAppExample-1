package com.example.ndc3.birdappexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper db = new dbHelper(this);
        String logTag = "Birds";

        Log.d(logTag, "Inserting");

        db.addContact(new Bird(1, "Crow", "Description"));
        db.addContact(new Bird(2, "BlueBird", "Description"));
        db.addContact(new Bird(3, "Hummingbird", "Description"));

        Log.d("Reading: ", "Reading all contacts..");
        List<Bird> birds = db.getAllContacts();

        for (Bird b : birds) {
            String log = "Id: " + b.getId() + " ,Name: " + b.getName() + " ,Phone: " + b.getDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }
    }
}
