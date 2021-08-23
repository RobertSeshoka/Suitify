package com.example.suitify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String database = "SuitifyDB.db";


    public static final String tableAction = "tblActionGames";
    public static final String tableWarGames = "tblWarGames";
    public static final String tableRacingGames = "tblRacingGames";
    public static final String tableRegiserMyUser = "tblUserRegister";





    public Database(@Nullable Context context) {
        super(context, "SuitifyDB", null, 1);
    }

    //Implemented methods

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create the tables and columns
        db.execSQL("CREATE TABLE " + tableAction + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, gameName  TEXT, gameSize TEXT, gameYear TEXT)");
        db.execSQL("CREATE TABLE " + tableWarGames + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, gameName TEXT, gameSize TEXT, gameYear TEXT)");
        db.execSQL("CREATE TABLE " + tableRacingGames + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, gameName TEXT, gameSize TEXT, gameYear TEXT)");        db.execSQL("create Table tblRegister(email TEXT  primary key, password TEXT)");//This is the table for registering user
        db.execSQL("create Table " + tableRegiserMyUser + "(email TEXT  primary key, password TEXT)");//Table for registering user

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblGames1");
        db.execSQL("DROP TABLE IF EXISTS " + tableAction);
        db.execSQL("DROP TABLE IF EXISTS " + tableWarGames);
        db.execSQL("DROP TABLE IF EXISTS " + tableRacingGames);
        db.execSQL("DROP TABLE IF EXISTS " + tableRegiserMyUser);
    }


    //Insert data to war table
    public void insertWarTable(String myGameName, String myGameSize, String myGameYear) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("gameName", myGameName);
        contentValues.put("gameSize", myGameSize);
        contentValues.put("gameYear", myGameYear);

        SQLiteDatabase DB = this.getWritableDatabase();

        long l = DB.insert(Database.tableWarGames, null, contentValues);

    }



    //Insert data to the action games table
    public void insertToActionTable(String myGameName, String myGameSize, String myGameYear) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("gameName", myGameName);
        contentValues.put("gameSize", myGameSize);
        contentValues.put("gameYear", myGameYear);
        SQLiteDatabase DB = this.getWritableDatabase();

        long l = DB.insert(Database.tableAction, null, contentValues);
    }

    //Insert data to racing table

    public void inserRacingTable(String myGameName, String myGameSize, String myGameYear) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("gameName", myGameName);
        contentValues.put("gameSize", myGameSize);
        contentValues.put("gameYear", myGameYear);
        SQLiteDatabase DB = this.getWritableDatabase();

        long l = DB.insert(Database.tableRacingGames, null, contentValues);

    }


    public Boolean registerMyUser(String email, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Inserting data to the tables columns
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = DB.insert( tableRegiserMyUser, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    //Checking email
    public Boolean checkingEmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + tableRegiserMyUser +" where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;

        } else {
            return true;
        }
    }

    public Boolean checkEmailPasswordOfUser(String email, String password) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM " + tableRegiserMyUser + " WHERE email=? and password=?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }








    public Cursor getdataRegister() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from " + tableRegiserMyUser, null);
        return cursor;

    }





    //Gets all the data from the action games table
    public Cursor getdataAction() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from " + tableAction, null);
        return cursor;

    }

    //Gets all the data from the war games table
    public Cursor getdataWarGames() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from " + tableWarGames, null);
        return cursor;

    }


    //Gets all the data from the racing games table
    public Cursor getdataRacingGames() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from " + tableRacingGames, null);
        return cursor;

    }
}
