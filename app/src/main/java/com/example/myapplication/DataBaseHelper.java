package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;


public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "Login.db";


    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table user(username Text primary Key, password text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists user");
        onCreate(MyDatabase); // Recreate the table after dropping
    }


    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDatabase.insert("user", null, contentValues); // Changed "users" to "user"


        return result != -1; // Simplified return statement
    }


    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase(); // Changed to getReadableDatabase()
        Cursor cursor = MyDatabase.rawQuery("Select * from user where username = ?", new String[]{username});


        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Close the cursor
        return exists;
    }


    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase(); // Changed to getReadableDatabase()
        Cursor cursor = MyDatabase.rawQuery("Select * from user where username = ? and password = ?", new String[]{username, password});


        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Close the cursor
        return exists;
    }
}

