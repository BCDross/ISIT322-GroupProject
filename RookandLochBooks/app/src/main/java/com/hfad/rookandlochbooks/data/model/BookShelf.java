package com.hfad.rookandlochbooks.data.model;

public class BookShelf {
    private String ID;
    // BOOK_STATUS_SEEKING = 1;
    //  BOOK_STATUS_OWNED = 0;
    private int status;
    private String title, rating, author, ISBN;

    public BookShelf(String ID, int status, String title, String rating, String author, String ISBN) {
        this.ID = ID;
        this.status = status;
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.ISBN = ISBN;
    }
    public BookShelf(String ID, String title, String rating, String author, String ISBN) {
        this.ID = ID;
        this.status = 2;
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.ISBN = ISBN;
    }




    /*
    *   bookValues.put("Title", title);
        bookValues.put("Author", author);
        bookValues.put("ISBN", isbn);
        bookValues.put("Genre", genre);
        bookValues.put("Description", description);
        db.insert("Book", null, bookValues);
    *
    * */