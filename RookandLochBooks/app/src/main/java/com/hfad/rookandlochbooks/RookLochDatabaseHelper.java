package com.hfad.rookandlochbooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RookLochDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "rookLochBooks";
    private static final int DB_VERSION = 1;

    RookLochDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertBook(SQLiteDatabase db, String title, String isbn, String genre, String description) {
        ContentValues bookValues = new ContentValues();
        bookValues.put("Title", title);
        bookValues.put("ISBN", isbn);
        bookValues.put("Genre", genre);
        bookValues.put("Description", description);
        db.insert("Book", null, bookValues);
    }

    // This is the update method that does all the work. Needs to have a new if statement for each version to be able to do
    // incremental upgrades.
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This creates a new database that is empty.
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE Book (BookID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "TITLE TEXT, "
            + "ISBN INTEGER, "
            + "GENRE TEXT, "
            + "DESCRIPTION TEXT);");

            db.execSQL("CREATE TABLE User (UserID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FirstName TEXT, "
            + "LastName TEXT, "
            + "EmailAddress TEXT, "
            + "Deleted BOOL);");

            db.execSQL("CREATE TABLE Security (SecurityID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Password TEXT, "
            + "UserID INTEGER);");

            db.execSQL("CREATE TABLE Bookshelf (BookshelfID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT, "
                    + "Description TEXT, "
                    + "UserID INTEGER);");

            db.execSQL("CREATE TABLE Review (ReviewID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "FirstName TEXT, "
                    + "LastName TEXT, "
                    + "EmailAddress TEXT, "
                    + "Deleted BOOL);");

            db.execSQL("CREATE TABLE LinkReviewBook (LinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ReviewID INTEGER, "
                    + "BookID INTEGER);");

            db.execSQL("CREATE TABLE LinkBookBookshelf (LinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "BookID INTEGER, "
                    + "BookshelfID INTEGER);");
        }
        //This adds a new columns to the table. This is currently just a placeholder for future development.
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE BOOK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
