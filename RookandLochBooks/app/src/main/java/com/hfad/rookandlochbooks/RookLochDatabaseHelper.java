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

    private static void insertBook(SQLiteDatabase db, String title, String author, String isbn, String genre, String description) {
        ContentValues bookValues = new ContentValues();
        bookValues.put("Title", title);
        bookValues.put("Author", author);
        bookValues.put("ISBN", isbn);
        bookValues.put("Genre", genre);
        bookValues.put("Description", description);
        db.insert("Book", null, bookValues);
    }

    private static void insertUser(SQLiteDatabase db, String firstName, String lastName, String emailAddress, boolean deleted) {
        ContentValues userValues = new ContentValues();
        userValues.put("FirstName", firstName);
        userValues.put("LastName", lastName);
        userValues.put ("EmailAddress", emailAddress);
        userValues.put ("Deleted", deleted);
        db.insert("User", null, userValues);
    }

    private static void insertSecurity(SQLiteDatabase db, String password, int userID) {
        ContentValues userValues = new ContentValues();
        userValues.put("Password", password);
        userValues.put("UserID", userID);
        db.insert("User", null, userValues);
    }

    private static void insertBookshelf(SQLiteDatabase db, String name, String description, int userID) {
        ContentValues userValues = new ContentValues();
        userValues.put("Name", name);
        userValues.put("Description", description);
        userValues.put ("UserId", userID);
        db.insert("User", null, userValues);
    }

    private static void insertReview(SQLiteDatabase db, String title, String description, String userID) {
        ContentValues userValues = new ContentValues();
        userValues.put("Title", title);
        userValues.put("Description", description);
        userValues.put ("UserID", userID);
        db.insert("User", null, userValues);
    }

    private static void insertLinkReviewBook(SQLiteDatabase db, int bookID, int reviewID) {
        ContentValues userValues = new ContentValues();
        userValues.put("BookID", bookID);
        userValues.put("ReviewID", reviewID);
        db.insert("User", null, userValues);
    }

    private static void insertBookBookshelf(SQLiteDatabase db, int bookID, int bookshelfID) {
        ContentValues userValues = new ContentValues();
        userValues.put("BookID", bookID);
        userValues.put("BookshelfID", bookshelfID);
        db.insert("User", null, userValues);
    }

    // This is the update method that does all the work. Needs to have a new if statement for each version to be able to do
    // incremental upgrades.
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This creates a new database that is empty.
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE Book (BookID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "TITLE TEXT, "
            + "AUTHOR TEXT, "
            + "ISBN INTEGER, "
            + "GENRE TEXT, "
            + "DESCRIPTION TEXT);");

            insertBook(db, "The Iliad", "Homer, Robert Fitzgerald", "978-0374529055", "Mythology", "Homer's epic story of the Trojan War." );
            insertBook(db, "The Lord of the Rings: 50th Anniversary, One Vol. Edition", "J.R.R. Tolkien", "978-0618640157", "Fantasy", "The Masterpiece classic story of good versus evil, with a ring to decide it all.");
            insertBook(db, "The Divine Comedy", "Dante Alighieri", "978-0451208637", "Fiction", "The story of the travels Dante being guided by Virgil through Hell, Purgatory, and eventually Heaven.");
            insertBook(db, "A Darker Shade of Magic", "V.E. Schwab", "978-0765376459", "Fantasy", "A story of the four Londons, and a man that can walk between them, and the dangers that come from being the last of a dying race.");


            db.execSQL("CREATE TABLE User (UserID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FirstName TEXT, "
            + "LastName TEXT, "
            + "EmailAddress TEXT, "
            + "Deleted BOOL);");

            insertUser(db, "TestFirst", "TestLast", "test@bc.com", false);

            db.execSQL("CREATE TABLE Security (SecurityID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Password TEXT, "
            + "UserID INTEGER);");

            insertSecurity(db, "Test", 1);

            db.execSQL("CREATE TABLE Bookshelf (BookshelfID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT, "
                    + "Description TEXT, "
                    + "UserID INTEGER);");

            insertBookshelf(db, "Test's Bookshelf", "This is a test bookshelf. It still works though.", 1);

            db.execSQL("CREATE TABLE Review (ReviewID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Title TEXT, "
                    + "Description TEXT, "
                    + "UserID NUMERIC);");

            insertReview(db, "Test Review", "This is a test Review for a book. Guess which book?", 1);

            db.execSQL("CREATE TABLE LinkReviewBook (LinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ReviewID INTEGER, "
                    + "BookID INTEGER);");

            insertLinkReviewBook("db", 1, 1);
            insertLinkReviewBook("db", 2, 1);
            insertLinkReviewBook("db", 3, 1);
            insertLinkReviewBook("db", 4, 1);


            db.execSQL("CREATE TABLE LinkBookBookshelf (LinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "BookID INTEGER, "
                    + "BookshelfID INTEGER);");

            insertBookBookshelf(db, 1, 1);
            insertBookBookshelf(db, 2, 1);
            insertBookBookshelf(db, 3, 1);
            insertBookBookshelf(db, 4, 1);
        }

        //This adds a new columns to the table. This is currently just a placeholder for future development.
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE Book ADD COLUMN AUTHOR TEXT;");
        }
    }
}
