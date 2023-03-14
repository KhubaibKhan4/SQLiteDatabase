package com.codespacepro.finaldatbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "contact_DB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "contacts";
    private static final String USERID = "user_id";
    private static final String USERNAME = "username";
    private static final String USER_PHONE_NO = "phone_no";

    public DBHelper(Context context) {
        super(context, DATABASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " ( " + USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + USER_PHONE_NO + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addContacts(String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, name);
        values.put(USER_PHONE_NO, number);
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<ContactModel> fetchContacts() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<ContactModel> arrContacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.id = cursor.getInt(0);
            contactModel.name = cursor.getString(1);
            contactModel.number = cursor.getString(2);
            arrContacts.add(contactModel);
        }
        return arrContacts;
    }

    public void UpdateContacts(ContactModel contactModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, contactModel.name);
        values.put(USER_PHONE_NO, contactModel.number);
        database.update(TABLE_NAME, values, USERID + "=" + contactModel.id, null);
    }

    public void DeleteContacts(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, USERID + " =? ", new String[]{String.valueOf(id)});
    }
}
