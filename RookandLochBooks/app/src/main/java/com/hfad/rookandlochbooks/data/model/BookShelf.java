package com.hfad.rookandlochbooks.data.model;

public class BookShelf {
    private int ID;
    private String title, author, ISBN,genre,description;
    public BookShelf(int ID, String title, String author, String ISBN, String genre, String description) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

}