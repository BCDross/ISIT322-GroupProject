package com.hfad.rookandlochbooks.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.hfad.rookandlochbooks.data.model.DBBook;
import com.hfad.rookandlochbooks.data.model.DBBookShelf;
import com.hfad.rookandlochbooks.data.model.DBLinkedBookBookshelf;

public class RookLochDBOperations {

    private RookLochDatabaseHelper dbHelper;


    public static void insertBook(SQLiteDatabase db, String title, String author, String isbn, String genre, String description) {
        ContentValues bookValues = new ContentValues();
        bookValues.put("Title", title);
        bookValues.put("Author", author);
        bookValues.put("ISBN", isbn);
        bookValues.put("Genre", genre);
        bookValues.put("Description", description);
        db.insert("Book", null, bookValues);
    }

    public static void insertUser(SQLiteDatabase db, String firstName, String lastName, String emailAddress, boolean deleted) {
        ContentValues userValues = new ContentValues();
        userValues.put("FirstName", firstName);
        userValues.put("LastName", lastName);
        userValues.put ("EmailAddress", emailAddress);
        userValues.put ("Deleted", deleted);
        db.insert("User", null, userValues);
    }

    public static void insertSecurity(SQLiteDatabase db, String password, int userID) {
        ContentValues userValues = new ContentValues();
        userValues.put("Password", password);
        userValues.put("UserID", userID);
        db.insert("Security", null, userValues);
    }

    public static void insertBookshelf(SQLiteDatabase db, String name, String description, int userID) {
        ContentValues userValues = new ContentValues();
        userValues.put("Name", name);
        userValues.put("Description", description);
        userValues.put ("UserId", userID);
        db.insert("Bookshelf", null, userValues);
    }

    public static void insertReview(SQLiteDatabase db, String title, String description, int userID) {
        ContentValues userValues = new ContentValues();
        userValues.put("Title", title);
        userValues.put("Description", description);
        userValues.put ("UserID", userID);
        db.insert("Review", null, userValues);
    }

    public static void insertLinkReviewBook(SQLiteDatabase db, int bookID, int reviewID) {
        ContentValues userValues = new ContentValues();
        userValues.put("BookID", bookID);
        userValues.put("ReviewID", reviewID);
        db.insert("LinkReviewBook", null, userValues);
    }

    public static void insertBookBookshelf(SQLiteDatabase db, int bookID, int bookshelfID) {
        ContentValues userValues = new ContentValues();
        userValues.put("BookID", bookID);
        userValues.put("BookshelfID", bookshelfID);
        db.insert("LinkBookBookshelf", null, userValues);
    }

    public static void AssociateBookToShelf (SQLiteDatabase db, DBBook dbBook, DBBookShelf dbBookShelf, DBLinkedBookBookshelf dbLinkedBookBookshelf){

        insertBook(db,dbBook.getTitle(),dbBook.getAuthor(), dbBook.getISBN(),dbBook.getGenre(), dbBook.getDescription());
        insertBookshelf(db, dbBookShelf.getName(),dbBookShelf.getDescription(),dbBookShelf.getUserid());
        insertBookBookshelf(db,dbLinkedBookBookshelf.getbookid(),dbLinkedBookBookshelf.getBookshelfid());
    }

}
