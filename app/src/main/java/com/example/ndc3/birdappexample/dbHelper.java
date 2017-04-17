package com.example.ndc3.birdappexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ndc3 on 2/21/2017.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String  DB_NAME = "BIRD_DB";


    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String TABLE_BIRD = "birdTable";

    public dbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table birdtable (id Integer primary key, name text, description text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIRD);

        onCreate(db);
    }

    public void addContact(Bird bird) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bird.getName());
        values.put(KEY_DESCRIPTION, bird.getDescription());

        db.insert(TABLE_BIRD, null, values);
        db.close();
    }

    public List<Bird> getAllContacts() {
        List<Bird> contactList = new ArrayList<Bird>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BIRD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bird contact = new Bird();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setDescription(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
