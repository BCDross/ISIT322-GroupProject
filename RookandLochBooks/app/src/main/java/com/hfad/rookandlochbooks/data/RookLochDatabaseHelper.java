package com.hfad.rookandlochbooks.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class
RookLochDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "rookLochBooks";
    private static final int DB_VERSION = 1;
    private static final String TAG = RookLochDatabaseHelper.class.getName();
    private Context context ;

    private static RookLochDatabaseHelper sInstance;

    // ...

    public static synchronized RookLochDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new RookLochDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public RookLochDatabaseHelper(Context context) {
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


    // This is the update method that does all the work. Needs to have a new if statement for each version to be able to do
    // incremental upgrades.
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
            //This creates a new database that is empty.
            RockLochDBOperations dbOperations= new RockLochDBOperations();

            if (oldVersion < 1) {
            db.execSQL("CREATE TABLE Book (BookID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "TITLE TEXT, "
            + "AUTHOR TEXT, "
            + "ISBN INTEGER, "
            + "GENRE TEXT, "
            + "DESCRIPTION TEXT);");
            dbOperations.insertBook(db, "The Iliad", "Homer, Robert Fitzgerald", "978-0374529055", "Mythology", "Homer's epic story of the Trojan War." );
            dbOperations.insertBook(db, "The Lord of the Rings: 50th Anniversary, One Vol. Edition", "J.R.R. Tolkien", "978-0618640157", "Fantasy", "The Masterpiece classic story of good versus evil, with a ring to decide it all.");
            dbOperations.insertBook(db, "The Divine Comedy", "Dante Alighieri", "978-0451208637", "Fiction", "The story of the travels Dante being guided by Virgil through Hell, Purgatory, and eventually Heaven.");
            dbOperations.insertBook(db, "A Darker Shade of Magic", "V.E. Schwab", "978-0765376459", "Fantasy", "A story of the four Londons, and a man that can walk between them, and the dangers that come from being the last of a dying race.");


            db.execSQL("CREATE TABLE User (UserID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FirstName TEXT, "
            + "LastName TEXT, "
            + "EmailAddress TEXT, "
            + "Deleted BOOL);");

            dbOperations.insertUser(db, "TestFirst", "TestLast", "test@bc.com", false);
            dbOperations.insertUser(db, "Andrew", "Lawson", "andrew.lawson@bellevuecollege.edu", false);

            db.execSQL("CREATE TABLE Security (SecurityID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Password TEXT, "
            + "UserID INTEGER);");

            dbOperations.insertSecurity(db, "LetMeIn", 1);
            dbOperations.insertSecurity(db, "LetMeIn", 2);

            db.execSQL("CREATE TABLE Bookshelf (BookshelfID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT, "
                    + "Description TEXT, "
                    + "UserID INTEGER);");

            dbOperations.insertBookshelf(db, "Test's Bookshelf", "This is a test bookshelf. It still works though.", 1);

            db.execSQL("CREATE TABLE Review (ReviewID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Title TEXT, "
                    + "Description TEXT, "
                    + "UserID NUMERIC);");

            dbOperations.insertReview(db, "Test Review", "This is a test Review for a book. Guess which book?", 1);

            db.execSQL("CREATE TABLE LinkReviewBook (LinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ReviewID INTEGER, "
                    + "BookID INTEGER);");

            dbOperations.insertLinkReviewBook(db, 1, 1);
            dbOperations.insertLinkReviewBook(db, 2, 1);
            dbOperations.insertLinkReviewBook(db, 3, 1);
            dbOperations.insertLinkReviewBook(db, 4, 1);


            db.execSQL("CREATE TABLE LinkBookBookshelf (LinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "BookID INTEGER, "
                    + "BookshelfID INTEGER);");

            dbOperations.insertBookBookshelf(db, 1, 1);
            dbOperations.insertBookBookshelf(db, 2, 1);
            dbOperations.insertBookBookshelf(db, 3, 1);
            dbOperations.insertBookBookshelf(db, 4, 1);


        }

        //This adds a new columns to the table. This is currently just a placeholder for future development.
//        if (oldVersion < 2) {
//            //db.execSQL("ALTER TABLE Book ADD COLUMN AUTHOR TEXT;");
//            Log.d(TAG, "updateMyDatabase: Where alter table call is made");
//        }

        //db.close();
    }

}
