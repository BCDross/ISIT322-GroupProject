package com.hfad.rookandlochbooks.data.model;

public class DBBook {

    private int ID;
    private String title, author, ISBN,genre,description;
    public DBBook(int ID, String title, String author, String ISBN, String genre, String description) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.description = description;
    }
    //for simple instantiations
    public DBBook (){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
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
