package com.hfad.rookandlochbooks.data.model;

public class DBBookShelf {
    private int bookshelfid;
    private String name, description;
    private int userid;

    public DBBookShelf(int bookshelfid, String name, String description, int userid) {
        this.bookshelfid = bookshelfid;
        this.name = name;
        this.description = description;
        this.userid = userid;
    }

    public int getBookshelfid() {
        return bookshelfid;
    }

    public void setBookshelfid(int bookshelfid) {
        this.bookshelfid = bookshelfid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

}