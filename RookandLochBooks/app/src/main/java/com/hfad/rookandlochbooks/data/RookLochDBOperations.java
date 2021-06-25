package com.hfad.rookandlochbooks.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.util.Log;

import com.hfad.rookandlochbooks.data.model.Book;
import com.hfad.rookandlochbooks.data.model.DBBook;
import com.hfad.rookandlochbooks.data.model.DBBookShelf;
import com.hfad.rookandlochbooks.data.model.DBLinkedBookBookshelf;

import java.util.ArrayList;
import java.util.List;

public class RookLochDBOperations {

    private RookLochDatabaseHelper dbHelper;
    private long result;

    public static void insertBook(SQLiteDatabase db, String title, String author, String isbn, String genre, String description) {
        //Validate whether this record exist within the table based on unique value
        if (!checkIfRecordExist(db,"Book","ISBN",isbn)) {
            ContentValues bookValues = new ContentValues();
            bookValues.put("Title", title);
            bookValues.put("Author", author);
            bookValues.put("ISBN", isbn);
            bookValues.put("Genre", genre);
            bookValues.put("Description", description);
            long result = db.insert("Book", null, bookValues);
        }
    }


    public static void insertUser(SQLiteDatabase db, String firstName, String lastName, String emailAddress, boolean deleted) {
        if (!checkIfRecordExist(db,"User","EmailAddress",emailAddress)) {
            ContentValues userValues = new ContentValues();
            userValues.put("FirstName", firstName);
            userValues.put("LastName", lastName);
            userValues.put("EmailAddress", emailAddress);
            userValues.put("Deleted", deleted);
            db.insert("User", null, userValues);
        }
    }

    public static void insertSecurity(SQLiteDatabase db, String password, int userID) {
        if (!checkIfRecordExist(db,"Security","UserID",userID)) {
            ContentValues userValues = new ContentValues();
            userValues.put("Password", password);
            userValues.put("UserID", userID);
            db.insert("Security", null, userValues);
        }
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

    public static boolean checkIfRecordExist(SQLiteDatabase db, String tableName, String columnName, String columnValue)
    {
        //Quick text to validate whether the table exist or not.
        try
        {
            Cursor cursor=db.rawQuery("SELECT "+columnName+" FROM "+tableName+" WHERE "+columnName+"='"+columnValue+"'",null);
            if (cursor.moveToFirst())
            {

                Log.d("Record  Already Exists", "Table is:"+tableName+" ColumnName:"+columnName);
                return true;//record Exists
            }
                Log.d("New Record  ", "Table is:"+tableName+" ColumnName:"+columnName+" Column Value:"+columnValue);

        }
        catch(Exception errorException)
        {
            Log.d("Exception occured", "Exception occured "+errorException);
            // db.close();
        }
        return false;
    }

    public static List<DBBook> getAllUserData (SQLiteDatabase db){

        Cursor cursor = db.rawQuery("select U.UserID,FirstName,LastName,EmailAddress,Password from User U inner join Security S on S.UserID=U.UserID",null);
        List<DBBook> DBbookList = new ArrayList<DBBook>();
        if (cursor != null & cursor.getCount() > 0) {
               // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        DBbookList.add(new DBBook(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
                    } while (cursor.moveToNext());
                }
        }
        return DBbookList;
    }

    public static List<DBBook> getUserData (SQLiteDatabase db, Integer UserID, String username, String password){
        List<DBBook> DBbookList = new ArrayList<DBBook>();
        final Cursor cursor;
        if (UserID != null) {
            cursor = db.rawQuery("select U.UserID,FirstName,LastName,EmailAddress,Password from User U inner join Security S on S.UserID=U.UserID WHERE "
                            + "EmailAddress =? AND " + "Password =?",
                    new String[]{username.toString(), password.toString()});

            if (cursor != null & cursor.getCount() > 0) {
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        DBbookList.add(new DBBook(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
                    } while (cursor.moveToNext());
                }
            }
        } else
        {
            cursor = db.rawQuery("select U.UserID,FirstName,LastName,EmailAddress,Password from User U inner join Security S on S.UserID=U.UserID WHERE "
                            + "UserID =? ",
                    new String[]{username.toString()});

            if (cursor != null & cursor.getCount() > 0) {
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        DBbookList.add(new DBBook(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
                    } while (cursor.moveToNext());
                }
            }
        }

        return DBbookList;
    }

    public static List<DBBookShelf> getBookData (SQLiteDatabase db){
        return getBookData(db, null);
    }

    public static List<DBBookShelf> getBookData (SQLiteDatabase db, Integer userid){

        Cursor cursor = (userid != null) ? (db.rawQuery("select booksheldid,name, description,userid from bookshelf where UserID = ?",new String[]{userid.toString()})) :(db.rawQuery("select booksheldid,name, description,userid from bookshelf ",null));
        List<DBBookShelf> dbBookShelf = new ArrayList<DBBookShelf>();
        if (cursor != null & cursor.getCount() > 0) {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    dbBookShelf.add(new DBBookShelf(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
                } while (cursor.moveToNext());
            }
        }
        return dbBookShelf;
    }

}

